// generated with ast extension for cup
// version 0.8
// 18/0/2021 1:20:50


package rs.ac.bg.etf.pp1.ast;

public class ActualParams extends ActualParsList {

    private ActualParsList ActualParsList;
    private SingleActualParam SingleActualParam;

    public ActualParams (ActualParsList ActualParsList, SingleActualParam SingleActualParam) {
        this.ActualParsList=ActualParsList;
        if(ActualParsList!=null) ActualParsList.setParent(this);
        this.SingleActualParam=SingleActualParam;
        if(SingleActualParam!=null) SingleActualParam.setParent(this);
    }

    public ActualParsList getActualParsList() {
        return ActualParsList;
    }

    public void setActualParsList(ActualParsList ActualParsList) {
        this.ActualParsList=ActualParsList;
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
        if(ActualParsList!=null) ActualParsList.accept(visitor);
        if(SingleActualParam!=null) SingleActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParsList!=null) ActualParsList.traverseTopDown(visitor);
        if(SingleActualParam!=null) SingleActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParsList!=null) ActualParsList.traverseBottomUp(visitor);
        if(SingleActualParam!=null) SingleActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParams(\n");

        if(ActualParsList!=null)
            buffer.append(ActualParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleActualParam!=null)
            buffer.append(SingleActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParams]");
        return buffer.toString();
    }
}
