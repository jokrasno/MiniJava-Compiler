/* 
Author: Joshua Krasnogorov
Assignment 2: CS-358 Compilers
*/

package parse;
import java.util.List;
import errorMsg.*;
import syntaxtree.*;
import wrangLR.runtime.MessageObject;
import wrangLR.runtime.FilePosObject;

public class MJGrammar implements MessageObject, FilePosObject
{

    // constructor
    // @param em error-message object
    public MJGrammar(ErrorMsg em)
    {
        errorMsg = em;
        topObject = null;
    }

    // error message object
    private ErrorMsg errorMsg;

    // object to be returned by the parser
    private Program topObject;

    // These 2 methods are needed by WrangLR
    // DO NOT USE THEM! They will not pass tests
    // We don't need any errors or warnings in this assignment.
    public void warning(int pos, String msg)
    {
        errorMsg.info(pos, msg);
    }

    public void error(int pos, String msg)
    {
        errorMsg.error(pos, msg);
    }

    // method for converting file position to line/char position
    // @param pos the file position
    // @return the string that denotes the file position
    public String filePosString(int pos)
    {
        return errorMsg.lineAndChar(pos);
    }

    // method that registers a newline
    // @param pos the file position of the newline character
    public void registerNewline(int pos)
    {
        errorMsg.newline(pos-1);
    }

    // returns the object produced by the parse
    // @return the top-level object produced by the parser
    public Program parseResult()
    {
        return topObject;
    }

    //===============================================================
    // start symbol
    //===============================================================

    //: <start> ::= ws* <program> =>
    public void topLevel(Program obj)
    {
        topObject = obj;
    }

    //================================================================
    // top-level constructs
    //================================================================

    //: <program> ::= # <class decl>+ =>
    public Program createProgram(int pos, List<ClassDecl> vec)
    {
        return new Program(pos, new ClassDeclList(vec));
    }

    //: <class decl> ::= `class # ID <extends>? `{ <decl in class>* `} =>
    public ClassDecl createClassDecl(int pos, String name, String ext, List<Decl> vec)
    {
        if (ext == null)
        {
            return new ClassDecl(pos, name, "Object", new DeclList(vec));
        }
        else 
        {
            return new ClassDecl(pos, name, ext, new DeclList(vec));
        }
    }

    //: <extends> ::= `extends ID => pass

    //: <decl in class> ::= <method decl> => pass
    //: <decl in class> ::= <field decl> => pass

    //: <field decl> ::= <type> # ID `; =>
    public Decl createFieldDecl(Type t, int pos, String name)
    {
        return new FieldDecl(pos, t, name);
    }
    /* may create public version in the future */


    //: <method decl> ::= `public `void # ID `( <param list>?`) `{ <stmt decl>* `} =>
    public Decl createMethodDeclVoid(int pos, String name, VarDeclList params, List<Stmt> stmts)
    {
        if (params == null)
        {
            params = new VarDeclList();
        }
        StmtList stmtsList = new StmtList(stmts);
        return new MethodDeclVoid(pos, name, params, stmtsList);
    }

    //: <method decl> ::= `public # <type> ID `( <param list>?`) `{ <stmt decl>* `return <expr> `; `} =>
    public Decl createMethodDeclNonVoid(int pos, Type type, String name, VarDeclList params, List<Stmt> stmts, Exp returnExp)
    {
        if (params == null)
        {
            params = new VarDeclList();
        }
        StmtList stmtsList = new StmtList(stmts);
        return new MethodDeclNonVoid(pos, type, name, params, stmtsList, returnExp);
    }

    //: <stmt decl> ::= <stmt> => pass
    //: <stmt decl> ::= <local var decl> => pass

    //: <param list> ::= <param> <param comma>* =>
    public VarDeclList createParamList(ParamDecl first, List<ParamDecl> rest)
    {
        VarDeclList result = new VarDeclList();
        result.add(first);
        if (rest != null)
        {
            result.addAll(rest);
        }
        return result;
    }

    //: <param> ::= # <type> ID =>
    public ParamDecl createParamDecl(int pos, Type t, String name)
    {
        return new ParamDecl(pos, t, name);
    }
    //: <param comma> ::= `, <param> => pass

    //: <base type> ::= # `int =>
    public Type intType(int pos)
    {
        return new IntType(pos);
    }
    //: <base type> ::= # `boolean =>
    public Type booleanType(int pos)
    {
        return new BoolType(pos);
    }
    //: <base type> ::= # ID =>
    public Type idType(int pos, String name)
    {
        return new IDType(pos, name);
    }
    //: <type> ::= # <type> <empty bracket pair> =>
    public Type newArrayType(int pos, Type t, Object dummy)
    {
        return new ArrayType(pos, t);
    }
    //: <type> ::= <base type> => pass

    //: <empty bracket pair> ::= `[ `] => null

    //================================================================
    // statement-level constructs
    //================================================================

    //: <stmt> ::= # `{ <stmt decl>* `} =>
    public Stmt newBlock(int pos, List<Stmt> sl)
    {
        return new Block(pos, new StmtList(sl));
    }
    //: <stmt> ::= # `while `( <expr> `) <stmt> =>
    public Stmt newWhile(int pos, Exp cond, Stmt body)
    {
        return new While(pos, cond, body);
    }
    
    //: <stmt> ::= # `if `( <expr> `) <stmt> !`else =>
    public Stmt newIf(int pos, Exp cond, Stmt trueBranch)
    {
        Stmt falseBranch = new Block(pos, new StmtList());
        return new If(pos, cond, trueBranch, falseBranch);
    }
    //: <stmt> ::= # `if `( <expr> `) <stmt> `else <stmt> =>
    public Stmt newIfElse(int pos, Exp cond, Stmt trueBranch, Stmt falseBranch)
    {
        return new If(pos, cond, trueBranch, falseBranch);
    }

    //: <stmt> ::= # `for `( <for init>? `; <expr>? `; <for update>? `) <stmt> =>
    public Stmt newFor(int pos, Stmt init, Exp cond, Stmt update, Stmt body)
    {
        StmtList blockStmts = new StmtList(); // hold variable init + while loop
        if (init != null)
        {
            blockStmts.add(init);
        } 
        else
        {
            blockStmts.add(new Block(pos, new StmtList()));
        }

        Exp whileCond;
        if (cond != null)
        {
            whileCond = cond;
        } 
        else
        {
            whileCond = new True(pos);
        }

        StmtList whileBodyStmts = new StmtList();
        whileBodyStmts.add(body);
        if (update != null)
        {
            whileBodyStmts.add(update);
        } 
        else 
        {
            whileBodyStmts.add(new Block(pos, new StmtList()));
        }
        Stmt whileBody = new Block(pos, whileBodyStmts);

        Stmt whileStmt = new While(pos, whileCond, whileBody);
        blockStmts.add(whileStmt);

        return new Block(pos, blockStmts); 
    }

    //: <stmt> ::= # `switch `( <expr> `) `{ <switch item>* `} =>
    public Stmt newSwitch(int pos, Exp switchExp, List<Stmt> bodyStmts)
    {
        StmtList switchBody;
        if (bodyStmts == null)
        {
            switchBody = new StmtList();
        }
        else
        {
            switchBody = new StmtList(bodyStmts);
        }
        return new Switch(pos, switchExp, switchBody);
    }

    //: <switch item> ::= <stmt decl> => pass
    //: <switch item> ::= # `case <expr> `: =>
    public Stmt newCaseLabel(int pos, Exp labelExp)
    {
        return new Case(pos, labelExp);
    }

    //: <switch item> ::= # `default `: =>
    public Stmt newDefaultLabel(int pos)
    {
        return new Default(pos);
    }

    //: <for init> ::= <type> # ID `= <expr> =>
    public Stmt newForInitType( Type t, int pos, String name, Exp init)
    {
        return new LocalDeclStmt(pos, new LocalVarDecl(pos, t, name, init));
    }
    //: <for init> ::= <assign> => pass
    //: <for init> ::= # <call expr> => 
    public Stmt newForInitCall(int pos, Call call)
    {
        return new CallStmt(pos, call);
    }

    //: <for update> ::= <assign> => pass
    //: <for update> ::= # <call expr> => 
    public Stmt newForUpdateCall(int pos, Call call)
    {
        return new CallStmt(pos, call);
    }

    //: <stmt> ::= # `do <stmt> `while `( <expr> `) `; =>
    public Stmt newDoWhile(int pos, Stmt body, Exp cond)
    {
        StmtList doWhileStmts = new StmtList();
        doWhileStmts.add(body);
        doWhileStmts.add(new While(pos, cond, body));
        return new Block(pos, doWhileStmts);
    }


    //: <stmt> ::= <assign> `; => pass

    //: <stmt> ::= # <call expr> `; =>
    public Stmt newCallStmt(int pos, Call call)
    {
        return new CallStmt(pos, call);
    }

    //: <stmt> ::= # `break `; =>
    public Stmt newBreak(int pos)
    {
        return new Break(pos);
    }
    //: <stmt> ::= # `; =>
    public Stmt newEmptyStmt(int pos)
    {
        return new Block(pos, new StmtList());
    }

    /* ASSIGN SECTION */

    //: <assign> ::= <expr> # `= <expr> =>
    public Stmt assign(Exp lhs, int pos, Exp rhs)
    {
        return new Assign(pos, lhs, rhs);
    }
    //: <assign> ::= #  ID `++ =>
    public Stmt newPostfixIncrement(int pos, String name)
    {
        IDExp var = new IDExp(pos, name);
        return new Assign(pos, var, new Plus(pos, var, new IntLit(pos, 1)));
    }
    //: <assign> ::= #  ID `-- =>
    public Stmt newPostfixDecrement(int pos, String name)
    {
        IDExp var = new IDExp(pos, name);
        return new Assign(pos, var, new Minus(pos, var, new IntLit(pos, 1)));
    }
    //: <assign> ::= # `++ ID =>
    public Stmt newPrefixIncrement(int pos, String name)
    {
        IDExp var = new IDExp(pos, name);
        return new Assign(pos, var, new Plus(pos, var, new IntLit(pos, 1)));
    }
    //: <assign> ::= # `-- ID =>
    public Stmt newPrefixDecrement(int pos, String name)
    {
        IDExp var = new IDExp(pos, name);
        return new Assign(pos, var, new Minus(pos, var, new IntLit(pos, 1)));
    }



    //: <local var decl> ::= <type> # ID `= <expr> `; =>
    public Stmt localVarDecl(Type t, int pos, String name, Exp init)
    {
        return new LocalDeclStmt(pos, new LocalVarDecl(pos, t, name, init));
    }


    //================================================================
    // expressions
    //================================================================

    /* EXPR8 SECTION */

    //: <expr> ::= <expr8> => pass
    //: <expr8> ::= <expr8> # `|| <expr7> =>
    public Exp newOr(Exp e1, int pos, Exp e2)
    {
        return new Or(pos, e1, e2);
    }
    //: <expr8> ::= <expr7> => pass

    /* EXPR7 SECTION */

    //: <expr7> ::= <expr7> # `&& <expr6> =>
    public Exp newAnd(Exp e1, int pos, Exp e2)
    {
        return new And(pos, e1, e2);
    }
    //: <expr7> ::= <expr6> => pass

    /* EXPR6 SECTION */

    //: <expr6> ::= <expr6> # `== <expr5> =>
    public Exp newEquals(Exp e1, int pos, Exp e2)
    {
        return new Equals(pos, e1, e2);
    }
    //: <expr6> ::= <expr6> # `!= <expr5> =>
    public Exp newNotEquals(Exp e1, int pos, Exp e2)
    {
        return new Not(pos, new Equals(pos, e1, e2));
    }
    //: <expr6> ::= <expr5> => pass

    /* EXPR5 SECTION */

    //: <expr5> ::= <expr5> # `< <expr4> =>
    public Exp newLessThan(Exp e1, int pos, Exp e2)
    {
        return new LessThan(pos, e1, e2);
    }
    //: <expr5> ::= <expr5> # `> <expr4> =>
    public Exp newGreaterThan(Exp e1, int pos, Exp e2)
    {
        return new GreaterThan(pos, e1, e2);
    }
    //: <expr5> ::= <expr5> # `<= <expr4> =>
    public Exp newLessThanOrEqual(Exp e1, int pos, Exp e2)
    {
        return new Not(pos, new GreaterThan(pos, e1, e2));
    }
    //: <expr5> ::= <expr5> # `>= <expr4> =>
    public Exp newGreaterThanOrEqual(Exp e1, int pos, Exp e2)
    {
        return new Not(pos, new LessThan(pos, e1, e2));
    }
    //: <expr5> ::= <expr5> # `instanceof <type> =>
    public Exp newInstanceOf(Exp e1, int pos, Type t)
    {
        return new InstanceOf(pos, e1, t);
    }
    //: <expr5> ::= <expr4> => pass

    /* EXPR4 SECTION */

    //: <expr4> ::= <expr4> # `+ <expr3> =>
    public Exp newPlus(Exp e1, int pos, Exp e2)
    {
        return new Plus(pos, e1, e2);
    }
    //: <expr4> ::= <expr4> # `- <expr3> =>
    public Exp newMinus(Exp e1, int pos, Exp e2)
    {
        return new Minus(pos, e1, e2);
    }
    //: <expr4> ::= <expr3> => pass

    /* EXPR3 SECTION */

    //: <expr3> ::= <expr3> # `* <expr2> =>
    public Exp newTimes(Exp e1, int pos, Exp e2)
    {
        return new Times(pos, e1, e2);
    }
    //: <expr3> ::= <expr3> # `/ <expr2> =>
    public Exp newDivide(Exp e1, int pos, Exp e2)
    {
        return new Divide(pos, e1, e2);
    }
    //: <expr3> ::= <expr3> # `% <expr2> =>
    public Exp newModulo(Exp e1, int pos, Exp e2)
    {
        return new Remainder(pos, e1, e2);
    }
    //: <expr3> ::= <expr2> => pass

    /* EXPR2 SECTION */

    //: <expr2> ::= <cast expr> => pass
    //: <expr2> ::= <unary expr> => pass

    //: <unary expr> ::= # `! <unary expr> =>
    public Exp newNot(int pos, Exp e)
    {
        return new Not(pos, e);
    }

    //: <cast expr> ::= # `( <type> `) <cast expr> =>
    public Exp newCast(int pos, Type t, Exp e)
    {
        return new Cast(pos, t, e);
    }
    //: <cast expr> ::= # `( <type> `) <expr1> => Exp newCast(int, Type, Exp)

    //: <unary expr> ::= # `- <unary expr> =>
    public Exp newUnaryMinus(int pos, Exp e)
    {
        return new Minus(pos, new IntLit(pos, 0), e);
    }
    //: <unary expr> ::= # `+ <unary expr> =>
    public Exp newUnaryPlus(int pos, Exp e)
    {
        return new Plus(pos, new IntLit(pos, 0), e);
    }

    //: <unary expr> ::= <expr1> => pass


    /* EXPR1 SECTION */

    //: <expr1> ::= <call expr> => 
    public Exp newCallExp(Call call)
    {
        return call;
    }

    //: <expr1> ::= # ID =>
    public Exp newIDExp(int pos, String name)
    {
        return new IDExp(pos, name);
    }
    //: <expr1> ::= # INTLIT =>
    public Exp newIntLit(int pos, int n)
    {
        return new IntLit(pos, n);
    }
    //: <expr1> ::= # STRINGLIT =>
    public Exp newStringLit(int pos, String s)
    {
        return new StringLit(pos, s);
    }
    //: <expr1> ::= # CHARLIT =>
    public Exp newCharLit(int pos, int c)
    {
        return new IntLit(pos, c);
    }
    //: <expr1> ::= # `false =>
    public Exp newFalse(int pos)
    {
        return new False(pos);
    }
    //: <expr1> ::= # `true =>
    public Exp newTrue(int pos)
    {
        return new True(pos);
    }
    //: <expr1> ::= # `null =>
    public Exp newNull(int pos)
    {
        return new Null(pos);
    }
    //: <expr1> ::= # `this =>
    public Exp newThis(int pos)
    {
        return new This(pos);
    }
    //: <expr1> ::= <expr1> !<empty bracket pair> # `[ <expr> `] =>
    public Exp newArrayLookup(Exp e1, int pos, Exp e2)
    {
        return new ArrayLookup(pos, e1, e2);
    }
    //: <expr1> ::= <expr1> `. # ID !`( =>
    public Exp newFieldAccess(Exp e1, int pos, String name)
    {
        if (name.equals("length")) {
            return new ArrayLength(pos, e1);
        }
        return new FieldAccess(pos, e1, name);
    }
    //: <expr1> ::= !<cast expr> `( <expr> `) =>
    public Exp newParenthesized(Exp e)
    {
        return e;
    }
    //: <expr1> ::= `new # ID `( `) =>
    public Exp newNewObject(int pos, String name)
    {
        return new NewObject(pos, new IDType(pos, name));
    }
    //: <expr1> ::= `new # <base type> `[ <expr> `] <empty bracket pair>* =>
    public Exp newNewArray(int pos, Type t, Exp size, List<Object> emptyBrackets)
    {
        Type arrayType = t;
        for (Object dummy : emptyBrackets)
        {
            arrayType = new ArrayType(pos, arrayType);
        }
        return new NewArray(pos, arrayType, size);
    }



    /* CALL EXP SECTION */

    //: <call expr> ::= # ID `( <expr list>? `) =>
    public Call newCallExpNoObj(int pos, String name, ExpList args)
    {
        This thisObj = new This(pos);
        if (args == null)
        {
            args = new ExpList();   
        }
        return new Call(pos, thisObj, name, args);
    }

    //: <call expr> ::= <expr1> `. # ID `( <expr list>? `) =>
    public Call newCallExpWithObj(Exp obj, int pos, String name, ExpList args)
    {
        if (args == null)
        {
            args = new ExpList();
        }
        return new Call(pos, obj, name, args);
    }

    //: <call expr> ::= `super `. # ID `( <expr list>? `,? `) =>
    public Call newCallExpSuper(int pos, String name, ExpList args)
    {
        if (args == null)
        {
            args = new ExpList();
        }
        return new Call(pos, new Super(pos), name, args);
    }

    //: <expr list> ::= <expr> <expr comma>* =>
    public ExpList createExprList(Exp first, List<Exp> rest)
    {
        ExpList result = new ExpList();
        result.add(first);
        if (rest != null)
        {
            result.addAll(rest);
        }
        return result;
    }

    //: <expr comma> ::= `, <expr> => pass

    //================================================================
    // Lexical grammar for filtered language begins here: DO NOT
    // MODIFY ANYTHING BELOW THIS, UNLESS YOU REPLACE IT WITH YOUR
    // ENTIRE LEXICAL GRAMMAR, and set the constant FILTER_GRAMMAR
    // (defined near the top of this file) to false.
    //================================================================

    //: letter ::= {"a".."z" "A".."Z"} => pass
    //: letter128 ::= {225..250 193..218} =>
    public char sub128(char orig)
    {
        return (char)(orig-128);
    }
    //: digit ::= {"0".."9"} => pass
    //: digit128 ::= {176..185} => char sub128(char)
    //: any ::= {0..127} => pass
    //: any128 ::= {128..255} => char sub128(char)
    //: ws ::= " "
    //: ws ::= {10} registerNewline
    //: registerNewline ::= # => void registerNewline(int)
    //: `boolean ::= "#bo" ws*
    //: `class ::= "#cl" ws*
    //: `extends ::= "#ex" ws*
    //: `void ::= "#vo" ws*
    //: `int ::= "#it" ws*
    //: `while ::= "#wh" ws*
    //: `if ::= '#+' ws*
    //: `else ::= "#el" ws*
    //: `for ::= "#fo" ws*
    //: `break ::= "#br" ws*
    //: `this ::= "#th" ws*
    //: `false ::= '#fa' ws*
    //: `true ::= "#tr" ws*
    //: `super ::= "#su" ws*
    //: `null ::= "#nu" ws*
    //: `return ::= "#re" ws*
    //: `instanceof ::= "#in" ws*
    //: `new ::= "#ne" ws*
    //: `case ::= "#ce" ws*
    //: `default ::= "#de" ws*
    //: `do ::= "#-" ws*
    //: `public ::= "#pu" ws*
    //: `switch ::= "#sw" ws*

    //: `! ::=  "!" ws* => void
    //: `!= ::=  "@!" ws* => void
    //: `% ::= "%" ws* => void
    //: `&& ::= "@&" ws* => void
    //: `* ::= "*" ws* => void
    //: `( ::= "(" ws* => void
    //: `) ::= ")" ws* => void
    //: `{ ::= "{" ws* => void
    //: `} ::= "}" ws* => void
    //: `- ::= "-" ws* => void
    //: `+ ::= "+" ws* => void
    //: `= ::= "=" ws* => void
    //: `== ::= "@=" ws* => void
    //: `[ ::= "[" ws* => void
    //: `] ::= "]" ws* => void
    //: `|| ::= "@|" ws* => void
    //: `< ::= "<" ws* => void
    //: `<= ::= "@<" ws* => void
    //: `, ::= "," ws* => void
    //: `> ::= ">"  !'=' ws* => void
    //: `>= ::= "@>" ws* => void
    //: `: ::= ":" ws* => void
    //: `. ::= "." ws* => void
    //: `; ::= ";" ws* => void
    //: `++ ::= "@+" ws* => void
    //: `-- ::= "@-" ws* => void
    //: `/ ::= "/" ws* => void


    //: ID ::= letter128 ws* => text
    //: ID ::= letter idChar* idChar128 ws* => text

    //: INTLIT ::= {"1".."9"} digit* digit128 ws* =>
    public int convertToInt(char c, List<Character> mid, char last)
    {
        return Integer.parseInt(""+c+mid+last);
    }
    //: INTLIT ::= digit128 ws* =>
    public int convertToInt(char c)
    {
        return Integer.parseInt(""+c);
    }
    //: INTLIT ::= "0" hexDigit* hexDigit128 ws* =>
    public int convert16ToInt(char c, List<Character> mid, char last)
    {
        return Integer.parseInt(""+c+mid+last, 16);
    }
    //: STRINGLIT ::= '@"' ws* =>
    public String emptyString(char x, char xx)
    {
        return "";
    }
    //: STRINGLIT ::= '"' any* any128 ws* =>
    public String string(char x, List<Character> mid, char last)
    {
        return ""+mid+last;
    }
    //: CHARLIT ::= "'" any ws* =>
    public int charVal(char x, char val)
    {
        return val;
    }

    //: idChar ::= letter => pass
    //: idChar ::= digit => pass
    //: idChar ::= "_" => pass
    //: idChar128 ::= letter128 => pass
    //: idChar128 ::= digit128 => pass
    //: idChar128 ::= {223} =>
    public char underscore(char x)
    {
        return '_';
    }
    //: hexDigit ::= {"0".."9" "A".."Z" "a".."z"} => pass
    //: hexDigit128 ::= {176..185 225..230 193..198} => char sub128(char)

}
