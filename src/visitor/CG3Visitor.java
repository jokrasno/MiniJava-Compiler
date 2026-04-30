package visitor;

import syntaxtree.*;
import errorMsg.*;
import java.io.*;

public class CG3Visitor extends Visitor
{
    // The purpose here is to generate assembly for each Node
    // in the AST.

    // IO stream to which we will emit code
    CodeStream code;

    // current stack height
    int stack;

    private ClassDecl currentClass;

    public CG3Visitor(ErrorMsg e, PrintStream out)
    {
        code = new CodeStream(out, e);
        code.setVisitor3(this);
        stack = 0;
        currentClass = null;
    }

    private boolean isPrimitive(Type t)
    {
        return t != null && (t.isInt() || t.isBoolean());
    }

    private int slotSize(Type t)
    {
        return isPrimitive(t) ? 8 : 4;
    }

    private void emitPushInt(String reg)
    {
        code.emit("  subu $sp,$sp,8");
        code.emit("  sw $s5,4($sp)");
        code.emit("  sw " + reg + ",($sp)");
        stack += 8;
    }

    private void emitPushObj(String reg)
    {
        code.emit("  subu $sp,$sp,4");
        code.emit("  sw " + reg + ",($sp)");
        stack += 4;
    }

    private void emitPopInt(String reg)
    {
        code.emit("  lw " + reg + ",($sp)");
        code.emit("  addu $sp,$sp,8");
        stack -= 8;
    }

    private void emitPopObj(String reg)
    {
        code.emit("  lw " + reg + ",($sp)");
        code.emit("  addu $sp,$sp,4");
        stack -= 4;
    }

    private void emitDiscard(int bytes)
    {
        if(bytes <= 0)
        {
            return;
        }
        code.emit("  addu $sp,$sp," + bytes);
        stack -= bytes;
    }

    private String methodLabel(MethodDecl m)
    {
        ClassDecl owner = m.classDecl != null ? m.classDecl : currentClass;
        return "mth_" + m.name + "_" + owner.name;
    }

    private void emitMethodExit()
    {
        int localBytes = stack - 4;
        if(localBytes > 0)
        {
            code.emit("  addu $sp,$sp," + localBytes);
            stack -= localBytes;
        }
        code.emit("  lw $ra,($sp)");
        code.emit("  addu $sp,$sp,4");
        stack -= 4;
        code.emit("  jr $ra");
    }

    private void arrLoad(String r0, String r1, String r2)
    {
        code.emit("sll " + r0 + ", " + r2 + ", 2");
        code.emit("addu " + r0 + ", " + r0 + ", " + r1);
    }

    private void swap(String r, String mem)
    {
        code.emit("lw $t0, " + mem);
        code.emit("sw " + r + ", " + mem);
        code.emit("move " + r + ", $t0");
    }

    private void nullPtrCheck(String r)
    {
        code.emit("beq " + r + ", $zero, nullPtrException");
    }

    private void oobCheck(String r1, String r2)
    {
        nullPtrCheck(r1);
        code.emit("lw $t3, -4(" + r1 + ")");
        code.emit("bgeu " + r2 + ", $t3, arrayIndexOutOfBounds");
    }

    @Override
    public Object visit(Program n)
    {
        code.emit(".text");
        code.emit(".globl main");
        code.emit("main:");
        code.emit("  jal vm_init");

        n.mainStmt.accept(this);

        //exit the program
        code.emit("  li $v0, 10");
        code.emit("  syscall");

        // emit code for all the methods in all the class declarations
        n.classDecls.accept(this);

        // flush the output and return
        code.flush();
        return null;
    }

    @Override
    public Object visit(ClassDecl n)
    {
        ClassDecl saved = currentClass;
        currentClass = n;
        n.decls.accept(this);
        currentClass = saved;
        return null;
    }

    @Override
    public Object visit(MethodDeclVoid n)
    {
        stack = 0;
        code.emit(methodLabel(n) + ":");
        code.emit("  subu $sp,$sp,4");
        code.emit("  sw $ra,($sp)");
        stack += 4;
        n.stmts.accept(this);
        emitMethodExit();
        return null;
    }

    @Override
    public Object visit(MethodDeclNonVoid n)
    {
        stack = 0;
        code.emit(methodLabel(n) + ":");
        code.emit("  subu $sp,$sp,4");
        code.emit("  sw $ra,($sp)");
        stack += 4;
        n.stmts.accept(this);
        n.rtnExp.accept(this);
        if(isPrimitive(n.rtnType))
        {
            emitPopInt("$t0");
        }
        else
        {
            emitPopObj("$t0");
        }
        emitMethodExit();
        return null;
    }

    @Override
    public Object visit(CallStmt n)
    {
        int before = stack;
        n.callExp.accept(this);
        emitDiscard(stack - before);
        return null;
    }

    @Override
    public Object visit(LocalDeclStmt n)
    {
        n.localVarDecl.accept(this);
        return null;
    }

    @Override
    public Object visit(LocalVarDecl n)
    {
        if(n.initExp != null)
        {
            n.initExp.accept(this);
        }
        else
        {
            code.emit("  move $t0,$zero");
            push(n.type, "$t0");
        }
        n.offset = stack;
        return null;
    }

    @Override
    public Object visit(Assign n)
    {
        if (n.lhs instanceof IDExp id)
        {
            n.rhs.accept(this);
            pop(id.link.type, "$t0");
            storeVar(id.link, "$t0");
        }
        else if(n.lhs instanceof FieldAccess fa)
        {
            fa.exp.accept(this);
            n.rhs.accept(this);
            pop(fa.varDec.type, "$t0");
            emitPopObj("$t1");
            nullPtrCheck("$t1");
            code.emit("  sw $t0, " + fa.varDec.offset + "($t1)");
        }
        else if(n.lhs instanceof ArrayLookup al)
        {
            al.arrExp.accept(this);
            al.idxExp.accept(this);
            n.rhs.accept(this);
            pop(al.type, "$t0");
            emitPopInt("$t1");
            emitPopObj("$t2");
            oobCheck("$t2", "$t1");
            arrLoad("$t1", "$t2", "$t1");
            code.emit("  sw $t0, ($t1)");
        }
        return null;
    }

    @Override
    public Object visit(If n)
    {
        int start = stack;
        n.exp.accept(this);
        emitPopInt("$t0");
        code.emit("  beq $t0, $zero, if_else_" + n.uniqueId);
        n.trueStmt.accept(this);
        stack = start;
        code.emit("  j if_done_" + n.uniqueId);
        code.emit("if_else_" + n.uniqueId + ":");
        if(n.falseStmt != null)
        {
            n.falseStmt.accept(this);
        }
        stack = start;
        code.emit("if_done_" + n.uniqueId + ":");
        return null;
    }

    @Override
    public Object visit(While n)
    {
        int start = stack;
        n.stackHeight = stack;
        code.emit("while_cond_" + n.uniqueId + ":");
        n.exp.accept(this);
        pop(n.exp.type, "$t0");
        code.emit("  beq $t0, $zero, break_target_" + n.uniqueId);
        n.body.accept(this);
        stack = start;
        code.emit("  j while_cond_" + n.uniqueId);
        code.emit("break_target_" + n.uniqueId + ":");
        return null;
    }

    @Override
    public Object visit(Break n)
    {
        int saved = stack;
        emitDiscard(stack - n.breakLink.stackHeight);
        code.emit("  j break_target_" + n.breakLink.uniqueId);
        stack = saved;
        return null;
    }

    @Override
    public Object visit(Call n)
    {
        n.obj.accept(this);
        for(Exp arg : n.args)
        {
            arg.accept(this);
        }

        int paramSize = 0;
        for(Exp arg : n.args)
        {
            paramSize += slotSize(arg.type);
        }

        code.emit("  lw $t0," + paramSize + "($sp)");
        code.emit("  sw $s2," + paramSize + "($sp)");
        code.emit("  move $s2,$t0");
        nullPtrCheck("$s2");

        if(n.obj instanceof Super)
        {
            code.emit("  jal " + methodLabel(n.methodLink));
        }
        else
        {
            code.emit("  lw $t0,-12($s2)");
            code.emit("  lw $t0," + (n.methodLink.vtableOffset * 4) + "($t0)");
            code.emit("  jalr $t0");
        }

        if(paramSize > 0)
        {
            code.emit("  addu $sp,$sp," + paramSize);
            stack -= paramSize;
        }

        emitPopObj("$s2");

        if(n.type != null && n.type.isVoid())
        {
            code.emit("  move $t0,$zero");
            emitPushObj("$t0");
        }
        else if(isPrimitive(n.type))
        {
            emitPushInt("$t0");
        }
        else
        {
            emitPushObj("$t0");
        }
        return null;
    }

    @Override
    public Object visit(Block n)
    {
        int start = stack;
        n.stmts.accept(this);
        emitDiscard(stack - start);
        return null;
    }

    @Override
    public Object visit(This n)
    {
        emitPushObj("$s2");
        return null;
    }

    @Override
    public Object visit(Super n)
    {
        emitPushObj("$s2");
        return null;
    }

    @Override
    public Object visit(IDExp n)
    {
        loadVar(n.link, "$t0");
        push(n.link.type, "$t0");
        return null;
    }

    @Override
    public Object visit(IntLit n)
    {
        code.emit("  li $t0," + n.val);
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(StringLit n)
    {
        StringLit rep = n.uniqueCgRep != null ? n.uniqueCgRep : n;
        code.emit("  la $t0,strLit_" + rep.uniqueId);
        emitPushObj("$t0");
        return null;
    }

    @Override
    public Object visit(True n)
    {
        code.emit("  li $t0, 1");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(False n)
    {
        code.emit("  move $t0, $zero");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(Null n)
    {
        code.emit("  move $t0, $zero");
        emitPushObj("$t0");
        return null;
    }

    @Override
    public Object visit(NewObject n)
    {
        IDType objType = (IDType)n.objType;
        ClassDecl cls = objType.link;
        int dataWords = cls == null ? 1 : cls.numDataFields + 1;
        int objWords = cls == null ? 0 : cls.numObjFields;
        String className = cls == null ? objType.name : cls.name;

        code.emit("  li $s6," + dataWords);
        code.emit("  li $s7," + objWords);
        code.emit("  jal newObject");
        stack += 4;
        code.emit("  la $t0,CLASS_" + className);
        code.emit("  sw $t0,-12($s7)");
        return null;
    }

    @Override
    public Object visit(NewArray n)
    {
        n.sizeExp.accept(this);
        code.emit("  li $s6, 1");
        emitPopInt("$s7");
        code.emit("  jal newObject");
        stack += 4;
        code.emit("  la $t0, CLASS_ARRAY_" + n.objType.vtableName());
        code.emit("  sw $t0,-12($s7)");
        return null;
    }

    @Override
    public Object visit(Plus n)
    {
        n.left.accept(this);
        n.right.accept(this);
        emitPopInt("$t2");
        emitPopInt("$t1");
        code.emit("  addu $t0,$t1,$t2");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(Minus n)
    {
        n.left.accept(this);
        n.right.accept(this);
        emitPopInt("$t2");
        emitPopInt("$t1");
        code.emit("  subu $t0,$t1,$t2");
        emitPushInt("$t0");
        return null;
    }
    
    @Override
    public Object visit(Times n)
    {
        n.left.accept(this);
        n.right.accept(this);
        emitPopInt("$t2");
        emitPopInt("$t1");
        code.emit("  mul $t0,$t1,$t2");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(Divide n)
    {
        n.left.accept(this);
        n.right.accept(this);
        code.emit("  jal divide");
        stack -= 8;
        return null;
    }

    
    @Override
    public Object visit(Remainder n)
    {
        n.left.accept(this);
        n.right.accept(this);
        code.emit("  jal remainder");
        stack -= 8;
        return null;
    }

    @Override
    public Object visit(Equals n)
    {
        n.left.accept(this);
        n.right.accept(this);
        pop(n.left.type, "$t2");
        pop(n.right.type, "$t1");
        code.emit("  seq $t0, $t1, $t2");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(LessThan n)
    {
        n.left.accept(this);
        n.right.accept(this);
        emitPopInt("$t2");
        emitPopInt("$t1");
        code.emit("  slt $t0, $t1, $t2");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(GreaterThan n)
    {
        n.left.accept(this);
        n.right.accept(this);
        emitPopInt("$t2");
        emitPopInt("$t1");
        code.emit("  sgt $t0, $t1, $t2");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(And n)
    {
        n.left.accept(this);
        code.emit("  lw $t0, ($sp)" );
        code.emit("  beq $t0, $zero, skip_" + n.uniqueId);
        emitPopInt("$t0");
        n.right.accept(this);
        code.emit("skip_" + n.uniqueId + ":");
        return null;
    }

    @Override
    public Object visit(Or n)
    {
        n.left.accept(this);
        code.emit("  lw $t0, ($sp)" );
        code.emit("  bne $t0, $zero, skip_" + n.uniqueId);
        emitPopInt("$t0");
        n.right.accept(this);
        code.emit("skip_" + n.uniqueId + ":");
        return null;
    }

    @Override
    public Object visit(ArrayLookup n)
    {
        n.arrExp.accept(this);
        n.idxExp.accept(this);

        emitPopInt("$t1");
        emitPopObj("$t2");
        oobCheck("$t2", "$t1");
        arrLoad("$t1", "$t2", "$t1");
        code.emit("  lw $t0, ($t1)");
        push(n.type, "$t0");
        return null;
    }

    @Override
    public Object visit(ArrayLength n)
    {
        n.exp.accept(this);
        pop(n.exp.type, "$t0");
        nullPtrCheck("$t0");
        code.emit("  lw $t0, -4($t0)");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(FieldAccess n)
    {
        n.exp.accept(this);
        pop(n.exp.type, "$t0");
        nullPtrCheck("$t0");
        code.emit("  lw $t0, " + n.varDec.offset + "($t0)");
        push(n.varDec.type, "$t0");
        return null;
    }

    @Override 
    public Object visit(Cast n)
    {
        n.exp.accept(this);
        code.emit("  la $t0, CLASS_" + n.castType.vtableName());
        code.emit("  la $t1, END_CLASS_" + n.castType.vtableName());
        code.emit("  jal checkCast");
        return null;
    }

    @Override
    public Object visit(InstanceOf n)
    {
        n.exp.accept(this);
        code.emit("  la $t0, CLASS_" + n.checkType.vtableName());
        code.emit("  la $t1, END_CLASS_" + n.checkType.vtableName());
        code.emit("  jal instanceOf");
        emitPopObj("$t0");
        emitPushInt("$t0");
        return null;
    }

    @Override
    public Object visit(Not n)
    {
        n.exp.accept(this);
        code.emit("  lw $t0, ($sp)");
        code.emit("  xor $t0, $t0, 1");
        code.emit("  sw $t0, ($sp)");
        return null;
    }

    @Override
    public Object visit(Switch n)
    {
        int start = stack;
        n.stackHeight = stack;

        n.exp.accept(this);
        emitPopInt("$t0");

        String defaultLabel = "break_target_" + n.uniqueId;
        for (Stmt stmt : n.stmts)
        {
            if (stmt instanceof Default d)
            {
                defaultLabel = "switch_case_" + d.uniqueId;
                break;
            }
        }

        for (Stmt stmt : n.stmts)
        {
            if (stmt instanceof Case c)
            {
                Object value = c.labelValue().accept(new ConstEvalVisitor());
                int v = (value instanceof Boolean b) ? (b ? 1 : 0) : ((Integer) value).intValue();
                code.emit("  li $t1," + v);
                code.emit("  beq $t0,$t1,switch_case_" + c.uniqueId);
            }
        }

        code.emit("  j " + defaultLabel);
        n.stmts.accept(this);
        emitDiscard(stack - start);
        code.emit("break_target_" + n.uniqueId + ":");
        stack = start;
        return null;
    }

    @Override
    public Object visit(Case n)
    {
        stack = n.enclosingSwitch != null ? n.enclosingSwitch.stackHeight : stack;
        code.emit("switch_case_" + n.uniqueId + ":");
        return null;
    }

    @Override
    public Object visit(Default n)
    {
        stack = n.enclosingSwitch != null ? n.enclosingSwitch.stackHeight : stack;
        code.emit("switch_case_" + n.uniqueId + ":");
        return null;
    }

    /* helper methods */

    public void pop(Type t, String r)
    {
        if (isPrimitive(t))
        {
            emitPopInt(r);
        }
        else
        {
            emitPopObj(r);
        }
    }

    public void push(Type t, String r)
    {
        if (isPrimitive(t))
            {
                emitPushInt(r);
            }
            else
            {
                emitPushObj(r);
            }
    }

    public void loadVar(VarDecl v, String r)
    {
        if(v instanceof FieldDecl)
        {
            code.emit("  lw " + r + "," + v.offset + "($s2)");
        }
        else if(v instanceof LocalVarDecl l)
        {
            code.emit("  lw " + r + "," + (stack - l.offset) + "($sp)");
        }
        else if(v instanceof ParamDecl p)
        {
            code.emit("  lw " + r + "," + ((stack - 4) + p.offset) + "($sp)");
        }
    }

    public void storeVar(VarDecl v, String r)
    {
        if(v instanceof FieldDecl)
        {
            code.emit("  sw " + r + "," + v.offset + "($s2)");
        }
        else if(v instanceof LocalVarDecl l)
        {
            code.emit("  sw " + r + "," + (stack - l.offset) + "($sp)");
        }
        else if(v instanceof ParamDecl p)
        {
            code.emit("  sw " + r + "," + ((stack - 4) + p.offset) + "($sp)");
        }
    }
}
