package visitor;

import syntaxtree.*;
import errorMsg.*;
import java.util.*;

// The purpose of this class it detect and report unreachable code,
// according to Java's rules.
public class Sem5Visitor extends Visitor
{
    ErrorMsg errorMsg;

    boolean reachable;

    public Sem5Visitor(ErrorMsg e)
    {
        errorMsg = e;
        reachable = true;
    }

    private void warnIfUnreachable(AstNode n)
    {
        if(!reachable)
        {
            errorMsg.warning(n.pos, CompWarning.UnreachableCode());
        }
    }

    private void visitStmtList(StmtList stmts)
    {
        for(int i = 0; i < stmts.size(); i++)
        {
            Stmt s = stmts.get(i);
            warnIfUnreachable(s);
            s.accept(this);
        }
    }

    private boolean isTrue(Exp e)
    {
        if(e == null)
        {
            return true;
        }
        Integer val = evaluateConstExp(e);
        return val != null && val != 0;
    }

    private boolean isFalse(Exp e)
    {
        if(e == null)
        {
            return false;
        }
        Integer val = evaluateConstExp(e);
        return val != null && val == 0;
    }

    @Override
    public Object visit(MethodDeclVoid m)
    {
        boolean oldReachable = reachable;
        reachable = true;
        m.params.accept(this);
        visitStmtList(m.stmts);
        reachable = oldReachable;
        return null;
    }

    @Override
    public Object visit(MethodDeclNonVoid m)
    {
        boolean oldReachable = reachable;
        reachable = true;
        m.rtnType.accept(this);
        m.params.accept(this);
        visitStmtList(m.stmts);
        warnIfUnreachable(m);
        m.rtnExp.accept(this);
        reachable = oldReachable;
        return null;
    }


    @Override
    public Object visit(Block b)
    {
        visitStmtList(b.stmts);
        return null;
    }


    @Override
    public Object visit(While w)
    {
        boolean oldReachable = reachable;

        // Check if condition is constant false
        if(isFalse(w.exp))
        {
            if(oldReachable)
            {
                errorMsg.warning(w.body.pos, CompWarning.UnreachableCode());
            }
            reachable = true;  // Temporarily make reachable to avoid duplicate warnings
            w.body.accept(this);
            reachable = oldReachable;  // Following statements are still reachable
        }
        else if(isTrue(w.exp))
        {
            // while(true) - check if body contains break
            boolean hasBreak = containsBreak(w.body, w);
            w.body.accept(this);
            if(!hasBreak)
            {
                // Infinite loop without break - following statements unreachable
                reachable = false;
            }
            else
            {
                // Has break - loop can exit, restore old reachable state
                reachable = oldReachable;
            }
        }
        else
        {
            reachable = oldReachable;
            w.body.accept(this);
            reachable = oldReachable;
        }
        return null;
    }


    @Override
    public Object visit(If i)
    {
        boolean oldReachable = reachable;
        boolean thenBlocked = false;
        boolean elseBlocked = false;

        // Check then branch
        reachable = oldReachable;
        i.trueStmt.accept(this);
        thenBlocked = !reachable;

        // Check else branch
        reachable = oldReachable;
        if(i.falseStmt != null)
        {
            i.falseStmt.accept(this);
            elseBlocked = !reachable;
        }

        // If both branches are blocked, mark following as unreachable
        if(thenBlocked && elseBlocked)
        {
            reachable = false;
        }
        else
        {
            reachable = oldReachable;
        }
        return null;
    }


    @Override
    public Object visit(Break b)
    {
        reachable = false;
        return null;
    }


    @Override
    public Object visit(Switch s)
    {
        boolean oldReachable = reachable;

        for(int i = 0; i < s.stmts.size(); i++)
        {
            s.stmts.get(i).accept(this);
        }

        reachable = oldReachable;
        return null;
    }


    private boolean containsBreak(Stmt s, BreakTarget target)
    {
        if(s instanceof Break)
        {
            return ((Break)s).breakLink == target;
        }
        else if(s instanceof Block)
        {
            Block b = (Block)s;
            for(int i = 0; i < b.stmts.size(); i++)
            {
                if(containsBreak(b.stmts.get(i), target))
                {
                    return true;
                }
            }
        }
        else if(s instanceof If)
        {
            If i = (If)s;
            return containsBreak(i.trueStmt, target) || (i.falseStmt != null && containsBreak(i.falseStmt, target));
        }
        else if(s instanceof While)
        {
            While w = (While)s;
            return containsBreak(w.body, target);
        }
        else if(s instanceof Switch)
        {
            Switch sw = (Switch)s;
            for(int i = 0; i < sw.stmts.size(); i++)
            {
                if(containsBreak(sw.stmts.get(i), target))
                {
                    return true;
                }
            }
        }
        return false;
    }


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
            else return null;
        }
        else if(e instanceof Minus)
        {
            Integer left = evaluateConstExp(((Minus)e).left);
            Integer right = evaluateConstExp(((Minus)e).right);
            if(left != null && right != null)
            {
                return left - right;
            }
            else return null;
        }
        else if(e instanceof Times)
        {
            Integer left = evaluateConstExp(((Times)e).left);
            Integer right = evaluateConstExp(((Times)e).right);
            if(left != null && right != null)
            {
                return left * right;
            }
            else return null;
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
        else if(e instanceof True)
        {
            return 1;
        }
        else if(e instanceof False)
        {
            return 0;
        }
        else if(e instanceof And)
        {
            Integer left = evaluateConstExp(((And)e).left);
            Integer right = evaluateConstExp(((And)e).right);
            if(left != null && right != null)
            {
                if(left != 0 && right != 0) return 1;
                else return 0;
            }
            else return null;
        }
        else if(e instanceof Or)
        {
            Integer left = evaluateConstExp(((Or)e).left);
            Integer right = evaluateConstExp(((Or)e).right);
            if(left != null && right != null)
            {
                if(left != 0 || right != 0) return 1;
                else return 0;
            }
            else return null;
        }
        else if(e instanceof LessThan)
        {
            Integer left = evaluateConstExp(((LessThan)e).left);
            Integer right = evaluateConstExp(((LessThan)e).right);
            if(left != null && right != null)
            {
                if(left < right) return 1;
                else return 0;
            }
            else return null;
        }
        else if(e instanceof GreaterThan)
        {
            Integer left = evaluateConstExp(((GreaterThan)e).left);
            Integer right = evaluateConstExp(((GreaterThan)e).right);
            if(left != null && right != null)
            {
                if(left > right) return 1;
                else return 0;
            }
            else return null;
        }
        else if(e instanceof Equals)
        {
            Integer left = evaluateConstExp(((Equals)e).left);
            Integer right = evaluateConstExp(((Equals)e).right);
            if(left != null && right != null)
            {
                if(left.equals(right)) return 1;
                else return 0;
            }
            else return null;
        }
        else if(e instanceof Not)
        {
            Integer val = evaluateConstExp(((Not)e).exp);
            if(val != null)
            {
                if(val == 0) return 1;
                else return 0;
            }
            else return null;
        }
        return null;
    }
}
