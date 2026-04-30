package syntaxtree;
// Provided by instructor — infrastructure code

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of statements
 * Example: { int x; x = 1; }
 */
public class StmtList extends AstList<Stmt>
{

    public StmtList()
    {
        super();
    }

    public StmtList(List<Stmt> lst)
    {
        super(lst);
    }

    public String name() {return "StmtList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstList<Stmt> n)
    {
        if(!(n instanceof StmtList)) return null;
        return v.visit(this, (StmtList)n);
    }
}
