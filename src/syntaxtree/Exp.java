package syntaxtree;
// Provided by instructor — infrastructure code

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an expression (abstract)
 * Example: 1 + 2 or obj.f()
 */
public abstract class Exp extends AstNode
{

    // instance variables filled in by constructor
    public Type type; // the expression's type

    /**
     * constructor
     * @param pos file position
     */
    public Exp(int pos)
    {
        super(pos);
        type=null;
    }

    /*** remaining methods are visitor- and display-related ***/

    public String name() {return "Exp";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Exp)) return null;
        return v.visit(this, (Exp)n);
    }
}
