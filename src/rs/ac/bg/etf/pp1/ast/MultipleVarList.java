// generated with ast extension for cup
// version 0.8
// 18/0/2021 1:20:50


package rs.ac.bg.etf.pp1.ast;

public class MultipleVarList extends VarList {

    private VarList VarList;
    private SingleVarDecl SingleVarDecl;

    public MultipleVarList (VarList VarList, SingleVarDecl SingleVarDecl) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.SingleVarDecl=SingleVarDecl;
        if(SingleVarDecl!=null) SingleVarDecl.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
    }

    public SingleVarDecl getSingleVarDecl() {
        return SingleVarDecl;
    }

    public void setSingleVarDecl(SingleVarDecl SingleVarDecl) {
        this.SingleVarDecl=SingleVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarList!=null) VarList.accept(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVarList(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleVarDecl!=null)
            buffer.append(SingleVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVarList]");
        return buffer.toString();
    }
}
