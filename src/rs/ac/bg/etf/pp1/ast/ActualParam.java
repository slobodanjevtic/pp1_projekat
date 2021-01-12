// generated with ast extension for cup
// version 0.8
// 12/0/2021 15:50:23


package rs.ac.bg.etf.pp1.ast;

public class ActualParam extends ActualParsList {

    private SingleActualParam SingleActualParam;

    public ActualParam (SingleActualParam SingleActualParam) {
        this.SingleActualParam=SingleActualParam;
        if(SingleActualParam!=null) SingleActualParam.setParent(this);
    }

    public SingleActualParam getSingleActualParam() {
        return SingleActualParam;
    }

    public void setSingleActualParam(SingleActualParam SingleActualParam) {
        this.SingleActualParam=SingleActualParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleActualParam!=null) SingleActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleActualParam!=null) SingleActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleActualParam!=null) SingleActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParam(\n");

        if(SingleActualParam!=null)
            buffer.append(SingleActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParam]");
        return buffer.toString();
    }
}
