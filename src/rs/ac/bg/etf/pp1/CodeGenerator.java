package rs.ac.bg.etf.pp1;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStmt printStmt) {
		if(printStmt.getExpr().struct.getElemType() == SymTab.intType || printStmt.getExpr().struct.getElemType() == SymTab.charType) {
			//Code.put(Code.aload);
		}
		if(printStmt.getExpr().struct == SymTab.intType || printStmt.getExpr().struct.getElemType() == SymTab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintStmtNum printStmtNum) {
		if( printStmtNum.getExpr().struct.getElemType() == SymTab.intType || printStmtNum.getExpr().struct.getElemType() == SymTab.charType) {
			//Code.put(Code.aload);
		}
		Code.loadConst(printStmtNum.getN2());
		if(printStmtNum.getExpr().struct == SymTab.intType || printStmtNum.getExpr().struct.getElemType() == SymTab.intType) {
			
			Code.put(Code.print);
		}
		else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(ReadStmt readStmt) {

		//Code.put(Code.pop);
		Code.put(Code.read);
		
		if(readStmt.getDesignator().obj.getType().getElemType() == SymTab.intType || 
				readStmt.getDesignator().obj.getType().getElemType() == SymTab.charType) {
			Code.put(Code.astore);

			//arrAccess = false;
		}
		else {
			Code.store(readStmt.getDesignator().obj);
			Code.put(Code.pop);
		}

	}
	
	public void visit(NumConst cnst) {
		Obj con = SymTab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		
		Code.load(con);
	}
	
	public void visit(SingleConstVarDecl constVarDecl) {
		Obj o = SymTab.find(constVarDecl.getVarName());
		Code.store(o);
	}
	
	public void visit(BoolConst cnst) {
		Obj con = SymTab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		if(cnst.getB1()) {
			con.setAdr(1);
		}
		else {
			con.setAdr(0);			
		}

		
		Code.load(con);
	}
	
	public void visit(CharConst cnst) {
		Obj con = SymTab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getC1());
		
		Code.load(con);
	}
	
	public void visit(MethodTypeName methodTypeName) {
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private boolean arrInit = false;
	
	public void visit(Assignment assignment) {
		boolean b = true;
		if(assignment.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(arrInit) {
				int kind = assignment.getDesignator().obj.getKind();
				Code.put(Code.newarray);
				switch (kind) {
				case Struct.Int:
					Code.put(1);
					break;
				case Struct.Char:
				case Struct.Bool:
					Code.put(0);				
					break;

				default:
					break;
				}
				arrInit = false;				
			}
			else {
				Code.put(Code.astore);
				//arrAccess = false;
				b = false;
			}

		}
		if(assignment.getDesignator().obj.getKind() != Obj.Con && b) {
			Code.store(assignment.getDesignator().obj);		
		}
	}

	public void visit(SingleDesignator designator) {
		SyntaxNode parent = designator.getParent();
		if(Assignment.class != parent.getClass() && FuncCall.class != parent.getClass() && ProcCall.class != parent.getClass()) {
			//Obj o = SymTab.find(designator.getName());
			Code.load(designator.obj);
		}
	}
	
	private boolean arrAccess = false;
	
	public void visit(ArrayDesignator designator) {
		Code.load(designator.obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		SyntaxNode parent = designator.getParent();
		if(Assignment.class != parent.getClass() && FuncCall.class != parent.getClass() && ProcCall.class != parent.getClass()) {
			if(designator.getParent().getClass() == Increment.class) {
				Code.put(Code.dup2);				
			}

			if(designator.getParent().getClass() != ReadStmt.class) {
				Code.put(Code.aload);				
			}

			//arrAccess = false;
		}
		//arrAccess = true;
		//Code.load(tempObj);
	}
	
	public void visit(ArrExpr arrExpr) {
		arrInit = true;
		//Code.loadConst(arrExpr.getExpr().struct.getKind());
	}
	
	private boolean standardMethods(Obj functionObj) {
		if(functionObj.getName().equals("len")) {
			Code.put(Code.arraylength);
			return false;
		}
		else if(functionObj.getName().equals("chr")) {
			return false;
		}
		else if(functionObj.getName().equals("ord")) {
			return false;
		}
		return true;
	}
	
	public void visit(FuncCall funcCall) {
		Obj functionObj = funcCall.getDesignator().obj;
		
		if(standardMethods(functionObj)) {
			int offset = functionObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}		
	}
	
	public void visit(ProcCall procCall) {
		Obj functionObj = procCall.getDesignator().obj;
		
		if(standardMethods(functionObj)) {
			int offset = functionObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);			
		}

		
		if(procCall.getDesignator().obj.getType() != SymTab.noType) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(ReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnNoExpr returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(AddExpr addExpr) {
		if(addExpr.getAddop().getClass() == Add.class) {
			Code.put(Code.add);			
		}
		else {
			Code.put(Code.sub);
		}

	}
	
    public void visit(MinusTermExpr minusTermExpr) {
		Code.put(Code.neg);
	}
    
	
	public void visit(MulExpr mulExpr) {
		if(mulExpr.getMulop().getClass() == Mul.class) {
			Code.put(Code.mul);			
		}
		else if(mulExpr.getMulop().getClass() == Div.class) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}

	}
	
	public void visit(Increment inc) {
		//Code.load(inc.getDesignator().obj);
		if(inc.getIncop().getClass() == Inc.class) {
			Code.put(Code.const_1);
		}
		else {
			Code.put(Code.const_m1);
		}
		Code.put(Code.add);

		if(inc.getDesignator().obj.getType().getKind() == Struct.Array) {
			Code.put(Code.astore);
		}
		else {
			Code.store(inc.getDesignator().obj);
		}
		
	}

	private int jmpInstPc_1 = 0;
	private int jmpInstPc_2 = 0;
	private int jmpAdr = 0;
	private int jmpAdrTemp = 0;
	private int curJmpCode = 0;
	private int ifLevel = 0;
	private boolean noOrInIf = true;
	private Stack<Integer> ifJmpAdrStack = new Stack<Integer>();
	
	public void visit(Quest quest) {
		Code.loadConst(0);
		setJmpInstPc();
		Code.putFalseJump(Code.ne, 0);
		jmpAdr = jmpInstPc_2;
	}
	
	public void visit(Colon colon) {
		JumpInstBackpatch.setJumpOn(jmpInstPc_2, JumpInstBackpatch.jumpOnElse);
		new JumpInstBackpatch(Code.pc, ifLevel).setJmpOn(JumpInstBackpatch.jumpOut);;
		Code.putJump(0);
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOnElse, ifLevel);
	}
	
	public void visit(ConditionExpr expr) {
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOut, ifLevel);
	}
	
	public void visit(RightParen paren) {
		inverseJmpInst(jmpInstPc_2);
		//jmpAdrs.add(Code.pc);
		JumpInstBackpatch.setJumpOn(jmpInstPc_2, JumpInstBackpatch.jumpOnElse);
		if(noOrInIf) {
			JumpInstBackpatch.setJumpOnForAllInst(jmpInstPc_2, JumpInstBackpatch.jumpOnElse, ifLevel);
		}
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpInIf, ifLevel);
		noOrInIf = true;
		ifLevel++;
	}
	
	public void visit(OrCondExpr condExpr) {
		JumpInstBackpatch.setJmpAdr(jmpAdrTemp, JumpInstBackpatch.jumpAfterOrOp, ifLevel);
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpAfterOrOp, ifLevel);
		noOrInIf = false;
	}
	
	public void visit(AndCondExpr condExpr) {
		inverseJmpInst(jmpInstPc_1);
	}
	
	private void setJmpInstPc() {
		jmpInstPc_1 = jmpInstPc_2;
		jmpInstPc_2 = Code.pc;
		new JumpInstBackpatch(Code.pc, ifLevel);
	}
	
	private void inverseJmpInst(int jmpInstPc) {
		Code.buf[jmpInstPc] = (byte)(Code.jcc + Code.inverse[Code.buf[jmpInstPc] - Code.jcc]);
		JumpInstBackpatch.setJumpOn(jmpInstPc, JumpInstBackpatch.jumpAfterOrOp);
	}
	
	public void visit(Eq eq) {
		curJmpCode = Code.inverse[Code.eq];
	}
	
	public void visit(Ne ne) {
		curJmpCode = Code.inverse[Code.ne];
	}
	
	public void visit(Lt lt) {
		curJmpCode = Code.inverse[Code.lt];
	}
	
	public void visit(Le le) {
		curJmpCode = Code.inverse[Code.le];
	}
	
	public void visit(Gt gt) {
		curJmpCode = Code.inverse[Code.gt];
	}
	
	public void visit(Ge ge) {
		curJmpCode = Code.inverse[Code.ge];
	}
	
	public void visit(SingleRelCondFact relFact) {
		Code.loadConst(0);
		setJmpInstPc();
		Code.putFalseJump(Code.eq, 0);
		jmpAdr = jmpInstPc_2;
	}
	
	public void visit(RelCondFact relFact) {
		setJmpInstPc();
		Code.putFalseJump(curJmpCode, 0);
		jmpAdr = jmpInstPc_2;
	}
	
	public void visit(SingleAndCondExpr andExpr) {
		//jmpAdrs.add(jmpAdr);
		if(!noOrInIf) {
			JumpInstBackpatch.setJmpAdr(jmpAdr, JumpInstBackpatch.jumpAfterOrOp, ifLevel);			
		}

	}
	
	public void visit(SingleOrCondExpr orExpr) {
		jmpAdrTemp = Code.pc;
		//JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpAfterOrOp, ifLevel);
	}
	
	public void visit(Else els) {
		jmpAdr = Code.pc;
		new JumpInstBackpatch(Code.pc, ifLevel).setJmpOn(JumpInstBackpatch.jumpOut);;
		Code.putJump(0);
		ifJmpAdrStack.push(Code.pc);
	}
	
	public void visit(UnmatchedIf unmatchedIf) {
		ifLevel--;
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOnElse, ifLevel);
	}
	
	public void visit(UnmatchedIfElse unmatchedIfElse) {
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOut, ifLevel);
		ifLevel--;
		JumpInstBackpatch.setJmpAdr(ifJmpAdrStack.pop(), JumpInstBackpatch.jumpOnElse, ifLevel);
	}
	
	public void visit(MatchedIfElse matchedIfElse) {
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOut, ifLevel);
		ifLevel--;
		JumpInstBackpatch.setJmpAdr(ifJmpAdrStack.pop(), JumpInstBackpatch.jumpOnElse, ifLevel);
	}
	
	private Stack<Integer> whileJmpAdrStack = new Stack<Integer>();
	
	public void visit(Do d) {
		whileJmpAdrStack.push(Code.pc);
	}
	
	public void visit(MatchedDoWhile doWhile) {
		Code.putJump(whileJmpAdrStack.pop());
		ifLevel--;
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOnElse, ifLevel);
		JumpInstBackpatch.setJmpAdr(Code.pc, JumpInstBackpatch.jumpOnElse, ifLevel+1);
	}
	
	public void visit(BreakExpr breakExpr) {
		new JumpInstBackpatch(Code.pc, ifLevel).setJmpOn(JumpInstBackpatch.jumpOnElse);;
		Code.putJump(0);
	}
	
	public void visit(ContinueExpr conExpr) {
		Code.putJump(whileJmpAdrStack.peek());
	}
}

