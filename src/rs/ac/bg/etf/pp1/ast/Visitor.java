// generated with ast extension for cup
// version 0.8
// 10/0/2021 0:45:20


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(Expr Expr);
    public void visit(FormalParamList FormalParamList);
    public void visit(VarDeclList VarDeclList);
    public void visit(Unmatched Unmatched);
    public void visit(Addop Addop);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(ActualParsList ActualParsList);
    public void visit(Params Params);
    public void visit(Term Term);
    public void visit(StatementList StatementList);
    public void visit(Matched Matched);
    public void visit(ActualPars ActualPars);
    public void visit(Mod Mod);
    public void visit(Div Div);
    public void visit(Mul Mul);
    public void visit(Sub Sub);
    public void visit(Add Add);
    public void visit(Designator Designator);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(FuncCall FuncCall);
    public void visit(Var Var);
    public void visit(Const Const);
    public void visit(Dec Dec);
    public void visit(Inc Inc);
    public void visit(Fact Fact);
    public void visit(TermExpr TermExpr);
    public void visit(MulExpr MulExpr);
    public void visit(AddExpr AddExpr);
    public void visit(ProcCall ProcCall);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(PrintStmt PrintStmt);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(Assignment Assignment);
    public void visit(UnmatchedElse UnmatchedElse);
    public void visit(UnmatchedIf UnmatchedIf);
    public void visit(UnmatchedStmt UnmatchedStmt);
    public void visit(MatchedStmt MatchedStmt);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(MultipleFormalParamDecl MultipleFormalParamDecl);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(SingleMethodDecl SingleMethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDecl MethodDecl);
    public void visit(Type Type);
    public void visit(SingleVarDecl SingleVarDecl);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDecl VarDecl);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
