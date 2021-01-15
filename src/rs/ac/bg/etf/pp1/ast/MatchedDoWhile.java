// generated with ast extension for cup
// version 0.8
// 15/0/2021 0:24:18


package rs.ac.bg.etf.pp1.ast;

public class MatchedDoWhile extends Matched {

    private Do Do;
    private Matched Matched;
    private CondExpr CondExpr;
    private RightParen RightParen;

    public MatchedDoWhile (Do Do, Matched Matched, CondExpr CondExpr, RightParen RightParen) {
        this.Do=Do;
        if(Do!=null) Do.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.CondExpr=CondExpr;
        if(CondExpr!=null) CondExpr.setParent(this);
        this.RightParen=RightParen;
        if(RightParen!=null) RightParen.setParent(this);
    }

    public Do getDo() {
        return Do;
    }

    public void setDo(Do Do) {
        this.Do=Do;
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

    public RightParen getRightParen() {
        return RightParen;
    }

    public void setRightParen(RightParen RightParen) {
        this.RightParen=RightParen;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Do!=null) Do.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(CondExpr!=null) CondExpr.accept(visitor);
        if(RightParen!=null) RightParen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Do!=null) Do.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(CondExpr!=null) CondExpr.traverseTopDown(visitor);
        if(RightParen!=null) RightParen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Do!=null) Do.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(CondExpr!=null) CondExpr.traverseBottomUp(visitor);
        if(RightParen!=null) RightParen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedDoWhile(\n");

        if(Do!=null)
            buffer.append(Do.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        if(RightParen!=null)
            buffer.append(RightParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedDoWhile]");
        return buffer.toString();
    }
}
