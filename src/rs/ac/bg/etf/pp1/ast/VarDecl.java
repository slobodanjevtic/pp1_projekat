// generated with ast extension for cup
// version 0.8
// 19/0/2021 2:2:35


package rs.ac.bg.etf.pp1.ast;

public class VarDecl extends VarDeclList {

    private VarDeclList VarDeclList;
    private AllVarDeclType AllVarDeclType;

    public VarDecl (VarDeclList VarDeclList, AllVarDeclType AllVarDeclType) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.AllVarDeclType=AllVarDeclType;
        if(AllVarDeclType!=null) AllVarDeclType.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public AllVarDeclType getAllVarDeclType() {
        return AllVarDeclType;
    }

    public void setAllVarDeclType(AllVarDeclType AllVarDeclType) {
        this.AllVarDeclType=AllVarDeclType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(AllVarDeclType!=null) AllVarDeclType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(AllVarDeclType!=null) AllVarDeclType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(AllVarDeclType!=null) AllVarDeclType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AllVarDeclType!=null)
            buffer.append(AllVarDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
