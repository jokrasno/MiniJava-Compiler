package syntaxtree;
// Provided by instructor — infrastructure code

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a default-label within a switch statement
 * Example: default:
 */
public class Default extends Label
{

    /**
     * constructor
     * @param pos file position
     */
    public Default(int pos)
    {
        super(pos);
    }

    /*** remaining methods are visitor- and display-related ***/

    public String name() {return "Default";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Default)) return null;
        return v.visit(this, (Default)n);
    }
}

