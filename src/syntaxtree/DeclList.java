package syntaxtree;
// Provided by instructor — infrastructure code

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of declarations
 * Example: int x; void m() {} as members of a class
 */
public class DeclList extends AstList<Decl>
{

    public DeclList()
    {
        super();
    }

    public DeclList(List<Decl> lst)
    {
        super(lst);
    }

    public String name() {return "DeclList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstList<Decl> n)
    {
        if(!(n instanceof DeclList)) return null;
        return v.visit(this, (DeclList)n);
    }

}
