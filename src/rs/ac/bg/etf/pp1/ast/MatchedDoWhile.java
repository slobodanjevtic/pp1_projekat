// generated with ast extension for cup
// version 0.8
// 12/0/2021 15:50:23


package rs.ac.bg.etf.pp1.ast;

public class MatchedDoWhile extends Matched {

    private Matched Matched;
    private CondExpr CondExpr;

    public MatchedDoWhile (Matched Matched, CondExpr CondExpr) {
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.CondExpr=CondExpr;
        if(CondExpr!=null) CondExpr.setParent(this);
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public CondExpr getCondExpr() {
        return CondExpr;
    }

    public void setCondExpr(CondExpr CondExpr) {
        this.CondExpr=CondExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Matched!=null) Matched.accept(visitor);
        if(CondExpr!=null) CondExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(CondExpr!=null) CondExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(CondExpr!=null) CondExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedDoWhile(\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondExpr!=null)
            buffer.append(CondExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedDoWhile]");
        return buffer.toString();
    }
}
