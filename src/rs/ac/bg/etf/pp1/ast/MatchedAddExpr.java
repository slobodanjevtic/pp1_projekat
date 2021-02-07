// generated with ast extension for cup
// version 0.8
// 7/1/2021 19:55:3


package rs.ac.bg.etf.pp1.ast;

public class MatchedAddExpr extends Expr {

    private MatchedExpr MatchedExpr;

    public MatchedAddExpr (MatchedExpr MatchedExpr) {
        this.MatchedExpr=MatchedExpr;
        if(MatchedExpr!=null) MatchedExpr.setParent(this);
    }

    public MatchedExpr getMatchedExpr() {
        return MatchedExpr;
    }

    public void setMatchedExpr(MatchedExpr MatchedExpr) {
        this.MatchedExpr=MatchedExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MatchedExpr!=null) MatchedExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedAddExpr(\n");

        if(MatchedExpr!=null)
            buffer.append(MatchedExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedAddExpr]");
        return buffer.toString();
    }
}
