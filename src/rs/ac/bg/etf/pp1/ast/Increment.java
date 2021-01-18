// generated with ast extension for cup
// version 0.8
// 18/0/2021 1:20:50


package rs.ac.bg.etf.pp1.ast;

public class Increment extends Matched {

    private Designator Designator;
    private Incop Incop;

    public Increment (Designator Designator, Incop Incop) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Incop=Incop;
        if(Incop!=null) Incop.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Incop getIncop() {
        return Incop;
    }

    public void setIncop(Incop Incop) {
        this.Incop=Incop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Incop!=null) Incop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Incop!=null) Incop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Incop!=null) Incop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Increment(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Incop!=null)
            buffer.append(Incop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Increment]");
        return buffer.toString();
    }
}
