package syntaxtree;
// Provided by instructor — infrastructure code

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the expression, 'true'
 * Example: true
 */
public class True extends Exp
{

    /**
     * constructor
     * @param pos file position
     */
    public True(int pos)
    {
        super(pos);
    }

    public String name() {return "True";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof True)) return null;
        return v.visit(this, (True)n);
    }
}
