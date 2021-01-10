// generated with ast extension for cup
// version 0.8
// 10/0/2021 0:45:20


package rs.ac.bg.etf.pp1.ast;

public class ActualParams extends ActualParsList {

    private ActualParsList ActualParsList;
    private Expr Expr;

    public ActualParams (ActualParsList ActualParsList, Expr Expr) {
        this.ActualParsList=ActualParsList;
        if(ActualParsList!=null) ActualParsList.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActualParsList getActualParsList() {
        return ActualParsList;
    }

    public void setActualParsList(ActualParsList ActualParsList) {
        this.ActualParsList=ActualParsList;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParsList!=null) ActualParsList.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParsList!=null) ActualParsList.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParsList!=null) ActualParsList.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParams(\n");

        if(ActualParsList!=null)
            buffer.append(ActualParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParams]");
        return buffer.toString();
    }
}
