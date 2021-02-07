// generated with ast extension for cup
// version 0.8
// 7/1/2021 19:55:3


package rs.ac.bg.etf.pp1.ast;

public class MatchedIfError extends Matched {

    private RightParen RightParen;

    public MatchedIfError (RightParen RightParen) {
        this.RightParen=RightParen;
        if(RightParen!=null) RightParen.setParent(this);
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
        if(RightParen!=null) RightParen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RightParen!=null) RightParen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RightParen!=null) RightParen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedIfError(\n");

        if(RightParen!=null)
            buffer.append(RightParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedIfError]");
        return buffer.toString();
    }
}
