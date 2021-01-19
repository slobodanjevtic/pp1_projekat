// generated with ast extension for cup
// version 0.8
// 19/0/2021 2:2:35


package rs.ac.bg.etf.pp1.ast;

public class ConditionExpr extends Expr {

    private MatchedExpr MatchedExpr;
    private Quest Quest;
    private MatchedExpr MatchedExpr1;
    private Colon Colon;
    private MatchedExpr MatchedExpr2;

    public ConditionExpr (MatchedExpr MatchedExpr, Quest Quest, MatchedExpr MatchedExpr1, Colon Colon, MatchedExpr MatchedExpr2) {
        this.MatchedExpr=MatchedExpr;
        if(MatchedExpr!=null) MatchedExpr.setParent(this);
        this.Quest=Quest;
        if(Quest!=null) Quest.setParent(this);
        this.MatchedExpr1=MatchedExpr1;
        if(MatchedExpr1!=null) MatchedExpr1.setParent(this);
        this.Colon=Colon;
        if(Colon!=null) Colon.setParent(this);
        this.MatchedExpr2=MatchedExpr2;
        if(MatchedExpr2!=null) MatchedExpr2.setParent(this);
    }

    public MatchedExpr getMatchedExpr() {
        return MatchedExpr;
    }

    public void setMatchedExpr(MatchedExpr MatchedExpr) {
        this.MatchedExpr=MatchedExpr;
    }

    public Quest getQuest() {
        return Quest;
    }

    public void setQuest(Quest Quest) {
        this.Quest=Quest;
    }

    public MatchedExpr getMatchedExpr1() {
        return MatchedExpr1;
    }

    public void setMatchedExpr1(MatchedExpr MatchedExpr1) {
        this.MatchedExpr1=MatchedExpr1;
    }

    public Colon getColon() {
        return Colon;
    }

    public void setColon(Colon Colon) {
        this.Colon=Colon;
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
        if(Quest!=null) Quest.accept(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.accept(visitor);
        if(Colon!=null) Colon.accept(visitor);
        if(MatchedExpr2!=null) MatchedExpr2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MatchedExpr!=null) MatchedExpr.traverseTopDown(visitor);
        if(Quest!=null) Quest.traverseTopDown(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.traverseTopDown(visitor);
        if(Colon!=null) Colon.traverseTopDown(visitor);
        if(MatchedExpr2!=null) MatchedExpr2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.traverseBottomUp(visitor);
        if(Quest!=null) Quest.traverseBottomUp(visitor);
        if(MatchedExpr1!=null) MatchedExpr1.traverseBottomUp(visitor);
        if(Colon!=null) Colon.traverseBottomUp(visitor);
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

        if(Quest!=null)
            buffer.append(Quest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedExpr1!=null)
            buffer.append(MatchedExpr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Colon!=null)
            buffer.append(Colon.toString("  "+tab));
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
