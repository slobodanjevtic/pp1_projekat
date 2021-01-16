// generated with ast extension for cup
// version 0.8
// 16/0/2021 23:40:26


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstVarList extends ConstVarList {

    private ConstVarList ConstVarList;
    private SingleConstVarDecl SingleConstVarDecl;

    public MultipleConstVarList (ConstVarList ConstVarList, SingleConstVarDecl SingleConstVarDecl) {
        this.ConstVarList=ConstVarList;
        if(ConstVarList!=null) ConstVarList.setParent(this);
        this.SingleConstVarDecl=SingleConstVarDecl;
        if(SingleConstVarDecl!=null) SingleConstVarDecl.setParent(this);
    }

    public ConstVarList getConstVarList() {
        return ConstVarList;
    }

    public void setConstVarList(ConstVarList ConstVarList) {
        this.ConstVarList=ConstVarList;
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
        if(ConstVarList!=null) ConstVarList.accept(visitor);
        if(SingleConstVarDecl!=null) SingleConstVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarList!=null) ConstVarList.traverseTopDown(visitor);
        if(SingleConstVarDecl!=null) SingleConstVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarList!=null) ConstVarList.traverseBottomUp(visitor);
        if(SingleConstVarDecl!=null) SingleConstVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstVarList(\n");

        if(ConstVarList!=null)
            buffer.append(ConstVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleConstVarDecl!=null)
            buffer.append(SingleConstVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstVarList]");
        return buffer.toString();
    }
}
