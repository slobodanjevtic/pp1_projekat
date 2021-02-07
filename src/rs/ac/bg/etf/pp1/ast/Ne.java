// generated with ast extension for cup
// version 0.8
// 7/1/2021 19:55:3


package rs.ac.bg.etf.pp1.ast;

public class Ne extends Relop {

    public Ne () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Ne(\n");

        buffer.append(tab);
        buffer.append(") [Ne]");
        return buffer.toString();
    }
}
