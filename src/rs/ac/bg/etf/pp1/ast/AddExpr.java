// generated with ast extension for cup
// version 0.8
// 19/0/2021 2:2:35


package rs.ac.bg.etf.pp1.ast;

public class AddExpr extends MatchedExpr {

    private MatchedExpr MatchedExpr;
    private Addop Addop;
    private Term Term;

    public AddExpr (MatchedExpr MatchedExpr, Addop Addop, Term Term) {
        this.MatchedExpr=MatchedExpr;
        if(MatchedExpr!=null) MatchedExpr.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public MatchedExpr getMatchedExpr() {
        return MatchedExpr;
    }

    public void setMatchedExpr(MatchedExpr MatchedExpr) {
        this.MatchedExpr=MatchedExpr;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MatchedExpr!=null) MatchedExpr.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MatchedExpr!=null) MatchedExpr.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddExpr(\n");

        if(MatchedExpr!=null)
            buffer.append(MatchedExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddExpr]");
        return buffer.toString();
    }
}
