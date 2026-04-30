package syntaxtree;
// Provided by instructor — infrastructure code

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a statement (abstract)
 * Example: x = 1; or while (b) { }
 */
public abstract class Stmt extends AstNode
{

    /**
     * constructor
     * @param pos file position
     */
    public Stmt(int pos)
    {
        super(pos);
    }

    public String name() {return "Stmt";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Stmt)) return null;
        return v.visit(this, (Stmt)n);
    }
}
