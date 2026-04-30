package visitor;

import syntaxtree.*;

import java.util.*;

import errorMsg.*;
// The purpose of this class is to do type-checking and related
// actions.  These include:
// - evaluate the type for each expression,
//   filling in the 'type' link for each
// - ensure that each expression follows MiniJava's type rules (e.g.,
//   that the arguments to '*' are both integer, the argument to
//   '.length' is an array, etc.)
// - ensure that each method-call follows Java's type rules:
//   - there exists a method for the given class (or a superclass)
//     for the receiver's object type
//   - the method has the correct number of parameters
//   - the types of each actual parameter is compatible with that
//     of its corresponding formal parameter
// - ensure that for each instance variable access (e.g., abc.foo),
//   there is an instance variable defined for the given class (or
//   in a superclass
//   - sets the 'varDec' link in the InstVarAccess to refer to the
//     method
// - ensure that the RHS expression in each assignment statement is
//   type-compatible with its corresponding LHS
//   - also checks that the LHS an lvalue
// - ensure that if a method with a given name is defined in both
//   a subclass and a superclass, that they have the same parameters
//   (with identical types) and the same return type
// - ensure that the declared return-type of a method is compatible
//   with its "return" expression
// - ensuring that the type of the control expression for an if- or
//   while-statement is boolean
public class Sem4Visitor extends Visitor
{
    ClassDecl currentClass;
    IDType currentType;
    IDType superType;
    ErrorMsg errorMsg;

    // Constants for types we'll need
    BoolType Bool;
    IntType Int;
    VoidType Void;
    NullType Null;
    ErrorType Error;
    IDType ObjectType;
    IDType StringType;

    HashMap<String,ClassDecl> classEnv;
    boolean arrayNegativeWarned;
    java.util.Stack<BreakTarget> breakTargetStack = new java.util.Stack<>();
    Stack<Integer> caseValues;
    boolean defaultFound;
    boolean afterBreak;
    boolean firstStatement;

    public Sem4Visitor(HashMap<String,ClassDecl> env, ErrorMsg e)
    {
        errorMsg = e;
        classEnv = env;
        currentClass = null;

        Bool = new BoolType(-1);
        Int  = new IntType(-1);
        Null = new NullType(-1);
        Void = new VoidType(-1);
        Error = new ErrorType(-1);
        StringType = new IDType(-1, "String");
        ObjectType = new IDType(-1, "Object");
        StringType.link = classEnv.get("String");
        ObjectType.link = classEnv.get("Object");
        caseValues = null;
        defaultFound = false;
        afterBreak = true;
        firstStatement = true;
    }

    @Override
    public Object visit(ClassDecl n)
    {
        ClassDecl oldClass = currentClass;
        currentClass = n;
        // Visit all declarations in the class
        if(n.decls != null)
        {
            for(int i = 0; i < n.decls.size(); i++)
            {
                n.decls.get(i).accept(this);
            }
        }
        currentClass = oldClass;
        return null;
    }

    @Override
    public Object visit(IntLit i)
    {
        i.type = Int;
        return Int;
    }

    @Override
    public Object visit(Plus p)
    {
        Type t1 = (Type)p.left.accept(this);
        Type t2 = (Type)p.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(p.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(p.right.pos, CompError.TypeMismatch(t2, Int));
        }
        p.type = Int;
        return Int;
    }

    // Set type on expression and return it
    private Type setAndReturn(Exp e, Type t)
    {
        e.type = t;
        return t;
    }

    // Check if t1 is subtype of t2 (T1 <: T2)
    private boolean subType(Type t1, Type t2)
    {
        if(t1.isError() || t2.isError()) return true;
        if(t1.equals(t2)) return true;
        if(t1.isNull() && (t2.isID() || t2.isArray())) return true;
        if(t1.isArray() && t2.isObject()) return true;
        if(t1.isID() && t2.isID())
        {
            ClassDecl c1 = ((IDType)t1).link;
            ClassDecl c2 = ((IDType)t2).link;
            while(c1 != null)
            {
                if(c1 == c2) return true;
                c1 = c1.superLink;
            }
        }
        return false;
    }

    // Check if types are compatible (T1 ~ T2)
    private boolean compatible(Type t1, Type t2)
    {
        return subType(t1, t2) || subType(t2, t1);
    }

    @Override
    public Object visit(MethodDeclNonVoid mdnv)
    {
        // Check for override
        checkOverride(mdnv, mdnv.rtnType);

        // Visit statements
        for(int i = 0; i < mdnv.stmts.size(); i++)
        {
            mdnv.stmts.get(i).accept(this);
        }

        // Check return expression type
        Type rtnType = (Type)mdnv.rtnExp.accept(this);
        if(rtnType != null && !subType(rtnType, mdnv.rtnType))
        {
            errorMsg.error(mdnv.pos, CompError.Subtype(rtnType, mdnv.rtnType));
        }

        return null;
    }

    @Override
    public Object visit(MethodDeclVoid mdv)
    {
        // Check for override (void return type)
        checkOverride(mdv, Void);

        // Visit statements
        for(int i = 0; i < mdv.stmts.size(); i++)
        {
            mdv.stmts.get(i).accept(this);
        }

        return null;
    }

    private void checkOverride(MethodDecl method, Type returnType)
    {
        if(currentClass == null || currentClass.superLink == null)
        {
            return;
        }

        ClassDecl superCls = currentClass.superLink;
        MethodDecl superMethod = null;

        // Search for method in superclass hierarchy
        while(superCls != null && superMethod == null)
        {
            superMethod = superCls.methodEnv.get(method.name);
            superCls = superCls.superLink;
        }

        if(superMethod != null)
        {
            method.superMethod = superMethod;

            // Check return type match
            Type superReturnType;
            if(superMethod instanceof MethodDeclNonVoid)
            {
                superReturnType = ((MethodDeclNonVoid)superMethod).rtnType;
            }
            else
            {
                superReturnType = Void;
            }

            if(!returnType.equals(superReturnType))
            {
                errorMsg.error(method.pos, CompError.ReturnOverride());
            }

            // Check parameter count
            if(method.params.size() != superMethod.params.size())
            {
                errorMsg.error(method.pos, CompError.NumArgsOverride());
            }
            else
            {
                // Check parameter types (only if counts match)
                for(int i = 0; i < method.params.size(); i++)
                {
                    Type paramType = method.params.get(i).type;
                    Type superParamType = superMethod.params.get(i).type;
                    if(!paramType.equals(superParamType))
                    {
                        errorMsg.error(paramType.pos, CompError.ArgTypeOverride());
                    }
                }
            }
        }
    }

    @Override
    public Object visit(If i)
    {
        Type condType = (Type)i.exp.accept(this);
        if(condType != null && !condType.isBoolean())
        {
            errorMsg.error(i.exp.pos, CompError.TypeMismatch(condType, Bool));
        }
        i.trueStmt.accept(this);
        if(i.falseStmt != null)
        {
            i.falseStmt.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(While w)
    {
        breakTargetStack.push(w);
        Type condType = (Type)w.exp.accept(this);
        if(condType != null && !condType.isBoolean())
        {
            errorMsg.error(w.exp.pos, CompError.TypeMismatch(condType, Bool));
        }
        w.body.accept(this);
        breakTargetStack.pop();
        return null;
    }

    @Override
    public Object visit(Assign a)
    {
        Type lhsType = (Type)a.lhs.accept(this);
        Type rhsType = (Type)a.rhs.accept(this);

        // Check if LHS is assignable (IDExp, FieldAccess, or ArrayLookup)
        if(!(a.lhs instanceof IDExp || a.lhs instanceof FieldAccess || a.lhs instanceof ArrayLookup))
        {
            errorMsg.error(a.pos, CompError.Assignment());
        }
        else if(lhsType != null && rhsType != null && !subType(rhsType, lhsType))
        {
            errorMsg.error(a.pos, CompError.Subtype(rhsType, lhsType));
        }

        return null;
    }

    @Override
    public Object visit(LocalDeclStmt lds)
    {
        return lds.localVarDecl.accept(this);
    }

    @Override
    public Object visit(LocalVarDecl lvd)
    {
        Type initType = (Type)lvd.initExp.accept(this);
        if(initType != null && !subType(initType, lvd.type))
        {
            errorMsg.error(lvd.pos, CompError.Subtype(initType, lvd.type));
        }
        return null;
    }

    @Override
    public Object visit(Call c)
    {
        Type objType = (Type)c.obj.accept(this);

        if(!objType.isID())
        {
            errorMsg.error(c.pos, CompError.UndefinedMethod(c.methName, objType));
            return setAndReturn(c, Error);
        }

        ClassDecl cls = ((IDType)objType).link;
        if(cls == null)
        {
            errorMsg.error(c.pos, CompError.UndefinedMethod(c.methName, objType));
            return setAndReturn(c, Error);
        }

        // Search for method in class hierarchy
        MethodDecl method = null;
        while(cls != null && method == null)
        {
            method = cls.methodEnv.get(c.methName);
            cls = cls.superLink;
        }

        if(method == null)
        {
            errorMsg.error(c.pos, CompError.UndefinedMethod(c.methName, objType));
            return setAndReturn(c, Error);
        }

        c.methodLink = method;

        // Get return type early - we use this even when there are parameter errors
        Type returnType;
        if(method instanceof MethodDeclNonVoid)
        {
            returnType = ((MethodDeclNonVoid)method).rtnType;
        }
        else
        {
            returnType = Void;
        }

        // Check parameter count
        int expectedParams = method.params.size();
        int actualParams = c.args.size();
        if(expectedParams != actualParams)
        {
            errorMsg.error(c.pos, CompError.ParameterMismatch(c.methName, actualParams, expectedParams));
            // Return the method's return type, not Error
            return setAndReturn(c, returnType);
        }

        // Check parameter types
        for(int i = 0; i < expectedParams; i++)
        {
            Type paramType = method.params.get(i).type;
            Type argType = (Type)c.args.get(i).accept(this);
            if(!subType(argType, paramType))
            {
                errorMsg.error(c.args.get(i).pos, CompError.Subtype(argType, paramType));
                // Return the method's return type, not Error
                return setAndReturn(c, returnType);
            }
        }

        return setAndReturn(c, returnType);
    }

    @Override
    public Object visit(NewObject no)
    {
        IDType objType = no.objType;
        if(objType.link == null)
        {
            errorMsg.error(no.pos, CompError.UndefinedClass(objType.name));
        }
        return setAndReturn(no, objType);
    }

    @Override
    public Object visit(FieldAccess fa)
    {
        Type objType = (Type)fa.exp.accept(this);

        if(!objType.isID())
        {
            errorMsg.error(fa.pos, CompError.UndefinedField(fa.varName, objType));
            return setAndReturn(fa, Error);
        }

        ClassDecl cls = ((IDType)objType).link;
        if(cls == null)
        {
            errorMsg.error(fa.pos, CompError.UndefinedField(fa.varName, objType));
            return setAndReturn(fa, Error);
        }

        // Search for field in class hierarchy
        FieldDecl field = null;
        while(cls != null && field == null)
        {
            field = cls.fieldEnv.get(fa.varName);
            cls = cls.superLink;
        }

        if(field == null)
        {
            errorMsg.error(fa.pos, CompError.UndefinedField(fa.varName, objType));
            return setAndReturn(fa, Error);
        }

        fa.varDec = field;
        return setAndReturn(fa, field.type);
    }

    @Override
    public Object visit(IDExp id)
    {
        if(id.link == null)
        {
            errorMsg.error(id.pos, CompError.UndefinedVariable(id.name));
            return setAndReturn(id, Error);
        }
        return setAndReturn(id, id.link.type);
    }

    @Override
    public Object visit(This t)
    {
        if(currentClass == null)
        {
            errorMsg.error(t.pos, CompError.UndefinedClass("this"));
            return setAndReturn(t, Error);
        }
        IDType thisType = new IDType(t.pos, currentClass.name);
        thisType.link = currentClass;
        return setAndReturn(t, thisType);
    }

    @Override
    public Object visit(NewArray na)
    {
        Type sizeType = (Type)na.sizeExp.accept(this);
        if(!sizeType.isInt())
        {
            errorMsg.error(na.sizeExp.pos, CompError.TypeMismatch(sizeType, Int));
        }
        // Warning for negative array size
        Integer sizeValue = evaluateConstExp(na.sizeExp);
        if(sizeValue != null && sizeValue < 0)
        {
            errorMsg.warning(na.sizeExp.pos, CompWarning.NegativeLength());
        }
        ArrayType arrayType = new ArrayType(na.pos, na.objType);
        return setAndReturn(na, arrayType);
    }

    @Override
    public Object visit(ArrayLookup al)
    {
        // Reset flag if this is the outermost array lookup (not nested)
        if(!(al.arrExp instanceof ArrayLookup))
        {
            arrayNegativeWarned = false;
        }

        Type arrType = (Type)al.arrExp.accept(this);
        Type idxType = (Type)al.idxExp.accept(this);

        boolean arrayError = false;
        if(!arrType.isArray())
        {
            errorMsg.error(al.arrExp.pos, CompError.ArrayType());
            arrayError = true;
        }

        if(!idxType.isInt())
        {
            errorMsg.error(al.idxExp.pos, CompError.TypeMismatch(idxType, Int));
        }

        // warning for negative array index (only first in chain)
        Integer idxValue = evaluateConstExp(al.idxExp);
        if(idxValue != null && idxValue < 0 && !arrayNegativeWarned)
        {
            errorMsg.warning(al.idxExp.pos, CompWarning.NegativeIndex());
            arrayNegativeWarned = true;
        }

        if(arrayError)
        {
            return setAndReturn(al, Error);
        }
        Type elemType = ((ArrayType)arrType).baseType;
        return setAndReturn(al, elemType);
    }

    @Override
    public Object visit(ArrayLength al)
    {
        Type arrType = (Type)al.exp.accept(this);

        if(!arrType.isArray())
        {
            errorMsg.error(al.exp.pos, CompError.ArrayType());
            return setAndReturn(al, Int);
        }

        return setAndReturn(al, Int);
    }

    @Override
    public Object visit(Equals e)
    {
        Type t1 = (Type)e.left.accept(this);
        Type t2 = (Type)e.right.accept(this);
        if(t1.isVoid() || t2.isVoid())
        {
            errorMsg.error(e.pos, CompError.IncompatibleType(t1, t2));
            return setAndReturn(e, Bool);
        }
        if(!compatible(t1, t2))
        {
            errorMsg.error(e.pos, CompError.IncompatibleType(t1, t2));
        }
        return setAndReturn(e, Bool);
    }

    @Override
    public Object visit(And a)
    {
        Type t1 = (Type)a.left.accept(this);
        Type t2 = (Type)a.right.accept(this);
        if(!t1.isBoolean())
        {
            errorMsg.error(a.left.pos, CompError.TypeMismatch(t1, Bool));
        }
        if(!t2.isBoolean())
        {
            errorMsg.error(a.right.pos, CompError.TypeMismatch(t2, Bool));
        }
        return setAndReturn(a, Bool);
    }

    @Override
    public Object visit(Or o)
    {
        Type t1 = (Type)o.left.accept(this);
        Type t2 = (Type)o.right.accept(this);
        if(!t1.isBoolean())
        {
            errorMsg.error(o.left.pos, CompError.TypeMismatch(t1, Bool));
        }
        if(!t2.isBoolean())
        {
            errorMsg.error(o.right.pos, CompError.TypeMismatch(t2, Bool));
        }
        return setAndReturn(o, Bool);
    }

    @Override
    public Object visit(LessThan lt)
    {
        Type t1 = (Type)lt.left.accept(this);
        Type t2 = (Type)lt.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(lt.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(lt.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(lt, Bool);
    }

    @Override
    public Object visit(GreaterThan gt)
    {
        Type t1 = (Type)gt.left.accept(this);
        Type t2 = (Type)gt.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(gt.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(gt.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(gt, Bool);
    }

    @Override
    public Object visit(Minus m)
    {
        Type t1 = (Type)m.left.accept(this);
        Type t2 = (Type)m.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(m.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(m.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(m, Int);
    }

    @Override
    public Object visit(Times t)
    {
        Type t1 = (Type)t.left.accept(this);
        Type t2 = (Type)t.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(t.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(t.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(t, Int);
    }

    @Override
    public Object visit(Divide d)
    {
        Type t1 = (Type)d.left.accept(this);
        Type t2 = (Type)d.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(d.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(d.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(d, Int);
    }

    @Override
    public Object visit(Remainder r)
    {
        Type t1 = (Type)r.left.accept(this);
        Type t2 = (Type)r.right.accept(this);
        if(!t1.isInt())
        {
            errorMsg.error(r.left.pos, CompError.TypeMismatch(t1, Int));
        }
        if(!t2.isInt())
        {
            errorMsg.error(r.right.pos, CompError.TypeMismatch(t2, Int));
        }
        return setAndReturn(r, Int);
    }

    @Override
    public Object visit(True t)
    {
        return setAndReturn(t, Bool);
    }

    @Override
    public Object visit(False f)
    {
        return setAndReturn(f, Bool);
    }

    @Override
    public Object visit(Null n)
    {
        return setAndReturn(n, Null);
    }

    @Override
    public Object visit(Not n)
    {
        Type t = (Type)n.exp.accept(this);
        if(!t.isBoolean())
        {
            errorMsg.error(n.exp.pos, CompError.TypeMismatch(t, Bool));
        }
        return setAndReturn(n, Bool);
    }

    @Override
    public Object visit(Super s)
    {
        if(currentClass == null)
        {
            errorMsg.error(s.pos, CompError.UndefinedClass("super"));
            return setAndReturn(s, Error);
        }

        // Super refers to the superclass of currentClass
        if(currentClass.superLink == null)
        {
            errorMsg.error(s.pos, CompError.UndefinedClass("super"));
            return setAndReturn(s, Error);
        }

        IDType superType = new IDType(s.pos, currentClass.superLink.name);
        superType.link = currentClass.superLink;
        return setAndReturn(s, superType);
    }

    @Override
    public Object visit(StringLit s)
    {
        return setAndReturn(s, StringType);
    }

    @Override
    public Object visit(Cast c)
    {
        Type expType = (Type)c.exp.accept(this);
        Type targetType = c.castType;

        // Check if cast is valid: types must be related via inheritance or expType is null
        if(expType != null && !expType.isError() && !targetType.isError())
        {
            // Cast is valid if:
            // 1. expType is null (null can be cast to any reference type)
            // 2. expType and targetType have an inheritance relationship
            boolean typesAreRelated = false;

            if(expType.isNull())
            {
                typesAreRelated = true;  // null can be cast to anything
            }
            else if(expType.isID() && targetType.isID())
            {
                // Check if there's an inheritance relationship
                ClassDecl expCls = ((IDType)expType).link;
                ClassDecl targetCls = ((IDType)targetType).link;
                if(expCls != null && targetCls != null)
                {
                    // Check if expType is subtype of targetType
                    ClassDecl temp = expCls;
                    while(temp != null)
                    {
                        if(temp == targetCls)
                        {
                            typesAreRelated = true;
                            break;
                        }
                        temp = temp.superLink;
                    }
                    // Check if targetType is subtype of expType
                    if(!typesAreRelated)
                    {
                        temp = targetCls;
                        while(temp != null)
                        {
                            if(temp == expCls)
                            {
                                typesAreRelated = true;
                                break;
                            }
                            temp = temp.superLink;
                        }
                    }
                }
            }
            else if(expType.isArray() && targetType.isID())
            {
                // Arrays can be cast to Object
                IDType idTarget = (IDType)targetType;
                if(idTarget.link != null && idTarget.link.name.equals("Object"))
                {
                    typesAreRelated = true;
                }
            }

            if(!typesAreRelated)
            {
                errorMsg.error(c.pos, CompError.IncompatibleType(targetType, expType));
            }
        }

        return setAndReturn(c, targetType);
    }

    @Override
    public Object visit(InstanceOf i)
    {
        Type expType = (Type)i.exp.accept(this);
        Type checkType = i.checkType;

        // The check type must be a reference type (IDType, ArrayType)
        if(!checkType.isID() && !checkType.isArray())
        {
            errorMsg.error(i.pos, CompError.TypeMismatch(checkType, ObjectType));
        }
        else if(expType != null && !expType.isError() && !expType.isNull())
        {
            // Check if types are related via inheritance
            boolean typesAreRelated = false;

            if(expType.isID() && checkType.isID())
            {
                ClassDecl expCls = ((IDType)expType).link;
                ClassDecl checkCls = ((IDType)checkType).link;
                if(expCls != null && checkCls != null)
                {
                    // Check if expType is subtype of checkType
                    ClassDecl temp = expCls;
                    while(temp != null)
                    {
                        if(temp == checkCls)
                        {
                            typesAreRelated = true;
                            break;
                        }
                        temp = temp.superLink;
                    }
                    // Check if checkType is subtype of expType (reverse check for instanceof)
                    if(!typesAreRelated)
                    {
                        temp = checkCls;
                        while(temp != null)
                        {
                            if(temp == expCls)
                            {
                                typesAreRelated = true;
                                break;
                            }
                            temp = temp.superLink;
                        }
                    }
                }
            }
            else if(expType.isArray() && checkType.isID())
            {
                // arrays can be instanceof Object
                IDType idCheck = (IDType)checkType;
                if(idCheck.link != null && idCheck.link.name.equals("Object"))
                {
                    typesAreRelated = true;
                }
            }

            if(!typesAreRelated)
            {
                errorMsg.error(i.pos, CompError.IncompatibleType(checkType, expType));
            }
        }

        return setAndReturn(i, Bool);
    }

    @Override
    public Object visit(Switch s)
    {
        Stack<Integer> oldCaseValues = caseValues;
        boolean oldDefaultFound = defaultFound;
        boolean oldAfterBreak = afterBreak;
        boolean oldFirstStatement = firstStatement;

        caseValues = new Stack<>();
        defaultFound = false;
        afterBreak = true;
        firstStatement = true;

        breakTargetStack.push(s);
        Type switchType = (Type)s.exp.accept(this);
        if(switchType != null && !switchType.isInt())
        {
            errorMsg.error(s.exp.pos, CompError.TypeMismatch(switchType, Int));
        }

        if(s.stmts.size() > 0)
        {
            Stmt first = s.stmts.get(0);
            if(!(first instanceof Case) && !(first instanceof Default))
            {
                errorMsg.error(first.pos, CompError.FirstLabelSwitch());
            }
        }

        for(int i = 0; i < s.stmts.size(); i++)
        {
            Stmt stmt = s.stmts.get(i);
            stmt.accept(this);
            firstStatement = false;
            afterBreak = stmt instanceof Break || stmt instanceof Case || stmt instanceof Default;
        }

        if(s.stmts.size() > 0)
        {
            Stmt last = s.stmts.get(s.stmts.size() - 1);
            if(!(last instanceof Break))
            {
                errorMsg.error(s.pos, CompError.EndBreakSwitch());
            }
        }

        breakTargetStack.pop();
        caseValues = oldCaseValues;
        defaultFound = oldDefaultFound;
        afterBreak = oldAfterBreak;
        firstStatement = oldFirstStatement;
        return null;
    }

    @Override
    public Object visit(Case c)
    {
        if(!firstStatement && !afterBreak)
        {
            errorMsg.error(c.pos, CompError.LabelAfterBreakSwitch());
        }

        Type caseType = (Type)c.exp.accept(this);
        if(caseType != null && !caseType.isInt())
        {
            errorMsg.error(c.exp.pos, CompError.TypeMismatch(caseType, Int));
        }

        // Check if case label is constant
        Integer constValue = evaluateConstExp(c.exp);
        if(constValue == null && !caseType.isError() && !caseType.isInt())
        {
            // Not an int type, error already reported above
        }
        else if(constValue == null && !caseType.isError())
        {
            errorMsg.error(c.pos, CompError.NonConstantCase());
        }
        else if(constValue != null)
        {
            if(caseValues.contains(constValue))
            {
                errorMsg.error(c.pos, CompError.DuplicateKeySwitch());
            }
            else
            {
                caseValues.push(constValue);
            }
        }
        return null;
    }

    @Override
    public Object visit(Default d)
    {
        if(!firstStatement && !afterBreak)
        {
            errorMsg.error(d.pos, CompError.LabelAfterBreakSwitch());
        }
        if(defaultFound)
        {
            errorMsg.error(d.pos, CompError.DuplicateDefaultSwitch());
        }
        defaultFound = true;
        return null;
    }

    @Override
    public Object visit(Break b)
    {
        if(!breakTargetStack.isEmpty())
        {
            b.breakLink = breakTargetStack.peek();
        }
        else
        {
            errorMsg.error(b.pos, CompError.TopLevelBreak());
        }
        return null;
    }

    // helper for array index checks
    public Integer evaluateConstExp(Exp e)
    {
        if(e instanceof IntLit)
        {
            return ((IntLit)e).val;
        }
        else if(e instanceof Plus)
        {
            Integer left = evaluateConstExp(((Plus)e).left);
            Integer right = evaluateConstExp(((Plus)e).right);
            if(left != null && right != null)
            {
                return left + right;
            }
            return null;
        }
        else if(e instanceof Minus)
        {
            Integer left = evaluateConstExp(((Minus)e).left);
            Integer right = evaluateConstExp(((Minus)e).right);
            if(left != null && right != null)
            {
                return left - right;
            }
            return null;
        }
        else if(e instanceof Times)
        {
            Integer left = evaluateConstExp(((Times)e).left);
            Integer right = evaluateConstExp(((Times)e).right);
            if(left != null && right != null)
            {
                return left * right;
            }
            return null;
        }
        else if(e instanceof Divide)
        {
            Integer left = evaluateConstExp(((Divide)e).left);
            Integer right = evaluateConstExp(((Divide)e).right);
            if(left != null && right != null && right != 0)
            {
                return left / right;
            }
            return null;
        }
        else if(e instanceof Remainder)
        {
            Integer left = evaluateConstExp(((Remainder)e).left);
            Integer right = evaluateConstExp(((Remainder)e).right);
            if(left != null && right != null && right != 0)
            {
                return left % right;
            }
            return null;
        }
        return null;
    }

}
