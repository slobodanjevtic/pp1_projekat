// generated with ast extension for cup
// version 0.8
// 16/0/2021 23:40:26


package rs.ac.bg.etf.pp1.ast;

public class SingleConstVarList extends ConstVarList {

    private SingleConstVarDecl SingleConstVarDecl;

    public SingleConstVarList (SingleConstVarDecl SingleConstVarDecl) {
        this.SingleConstVarDecl=SingleConstVarDecl;
        if(SingleConstVarDecl!=null) SingleConstVarDecl.setParent(this);
    }

    public SingleConstVarDecl getSingleConstVarDecl() {
        return SingleConstVarDecl;
    }

    public void setSingleConstVarDecl(SingleConstVarDecl SingleConstVarDecl) {
        this.SingleConstVarDecl=SingleConstVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleConstVarDecl!=null) SingleConstVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleConstVarDecl!=null) SingleConstVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleConstVarDecl!=null) SingleConstVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstVarList(\n");

        if(SingleConstVarDecl!=null)
            buffer.append(SingleConstVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstVarList]");
        return buffer.toString();
    }
}
