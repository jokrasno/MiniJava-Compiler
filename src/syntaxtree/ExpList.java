package syntaxtree;
// Provided by instructor — infrastructure code

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of expressions
 * Example: f(1, 2, 3) has three argument expressions
 */
public class ExpList extends AstList<Exp>
{

    public ExpList()
    {
        super();
    }

    public ExpList(List<Exp> lst)
    {
        super(lst);
    }

    public String name() {return "ExpList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstList<Exp> n)
    {
        if(!(n instanceof ExpList)) return null;
        return v.visit(this, (ExpList)n);
    }
}
