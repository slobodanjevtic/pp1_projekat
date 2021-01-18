// generated with ast extension for cup
// version 0.8
// 18/0/2021 15:55:47


package rs.ac.bg.etf.pp1.ast;

public class ConditionExpr extends Expr {

    private MatchedExpr MatchedExpr;
    private MatchedExpr MatchedExpr1;
    private MatchedExpr MatchedExpr2;

    public ConditionExpr (MatchedExpr MatchedExpr, MatchedExpr MatchedExpr1, MatchedExpr MatchedExpr2) {
        this.MatchedExpr=MatchedExpr;
        if(MatchedExpr!=null) MatchedExpr.setParent(this);
        this.MatchedExpr1=MatchedExpr1;
        if(MatchedExpr1!=null) MatchedExpr1.setParent(this);
        this.MatchedExpr2=MatchedExpr2;
        if(MatchedExpr2!=null) MatchedExpr2.setParent(this);
    }

    public MatchedExpr getMatchedExpr() {
        return MatchedExpr;
    }

    public void setMatchedExpr(MatchedExpr MatchedExpr) {
        this.MatchedExpr=MatchedExpr;
    }

    public MatchedExpr getMatchedExpr1() {
        return MatchedExpr1;
    }

    public void setMatchedExpr1(MatchedExpr MatchedExpr1) {
        this.MatchedExpr1=MatchedExpr1;
    }

    public MatchedExpr getMatchedExpr2() {
        return MatchedExpr2;
    }

    public void setMatchedExpr2(MatchedExpr MatchedExpr2) {
        this.MatchedExpr2=MatchedExpr2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.accept(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.accept(visitor);
        if(MatchedExpr2!=null) MatchedExpr2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MatchedExpr!=null) MatchedExpr.traverseTopDown(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.traverseTopDown(visitor);
        if(MatchedExpr2!=null) MatchedExpr2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.traverseBottomUp(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.traverseBottomUp(visitor);
        if(MatchedExpr2!=null) MatchedExpr2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionExpr(\n");

        if(MatchedExpr!=null)
            buffer.append(MatchedExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedExpr1!=null)
            buffer.append(MatchedExpr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedExpr2!=null)
            buffer.append(MatchedExpr2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionExpr]");
        return buffer.toString();
    }
}
