package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*; 

public class SemanticAnalyzer extends VisitorAdaptor {
	ArrayList<Integer> callFuncArgs = new ArrayList<Integer>();
	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	private ArrayList<Obj> vars = new ArrayList<Obj>();
	
	public void visit(VarDeclType varDeclType) {
		if(varDeclType.getType().getTypeName().equals("void")) {
			report_error("Semanticka greska na liniji " + varDeclType.getLine() + ": promenjlive ne mogu biti tipa void ", null);
		}
		else {
			for (Obj obj : vars) {
				Struct str = varDeclType.getType().struct;
				if(obj.getType() == null) {
					SymTab.insert(obj.getKind(), obj.getName(), str);
					report_info("Deklarisana promenljiva " + obj.getName(), varDeclType);	

				}
				else {
					obj.getType().setElementType(str);
					SymTab.insert(obj.getKind(), obj.getName(), obj.getType());
					report_info("Deklarisan niz " + obj.getName(), varDeclType);
				}
				
			}
		}
		vars.clear();			
	}
	
	private boolean varExistst(String name) {
		for (Obj obj : vars) {
			if(obj.getName().equals(name)) {
				return true;
			}
		}
		if(SymTab.find(name) != SymTab.noObj) {
			return true;
		}
		return false;
	}
	
	
	public void visit(SingleOneVarDecl varDecl){
		
		if(!varExistst(varDecl.getVarName())) {
			varDeclCount++;
			vars.add(new Obj(Obj.Var, varDecl.getVarName(), null));
		}
		else {
			report_error("Promenljiva " + varDecl.getVarName() + " je vec deklarisana ", null);
		}
		
		//report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		//Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct);
	}
	
	public void visit(SingleArrVarDecl varDecl){
		if(!varExistst(varDecl.getVarName())) {
			varDeclCount++;
			vars.add(new Obj(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array)));
		}
		else {
			report_error("Promenljiva " + varDecl.getVarName() + " je vec deklarisana ", null);
		}

		//report_info("Deklarisan niz " + varDecl.getVarName(), varDecl);
		//str.setElementType(varDecl.getType().struct);
		//Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), str);
		
	}
	
	private int constVal;
	
	public void visit(ConstVarDeclType varDeclType) {
		if(varDeclType.getType().getTypeName().equals("void")) {
			report_error("Semanticka greska na liniji " + varDeclType.getLine() + ": promenjlive ne mogu biti tipa void ", null);
		}
	}

	public void visit(SingleConstVarDecl constVarDecl) {
		//vars.add(new Obj(Obj.Con, constVarDecl.getVarName(), constVarDecl.getConst().struct));
		Obj o = SymTab.insert(Obj.Con, constVarDecl.getVarName(), constVarDecl.getConst().struct);
		o.setAdr(constVal);
	}
	
    public void visit(PrintStmt print) {
    	if(print.getExpr().struct != SymTab.intType && print.getExpr().struct != SymTab.charType && 
    			print.getExpr().struct.getElemType() != SymTab.intType && print.getExpr().struct.getElemType() != SymTab.charType) {
    		report_error("Semanticka greska na liniji " + print.getLine() + ": Operand instrunkcije PRINT mora biti char ili int tipa! ", null);
    	}
		printCallCount++;
	}
    
    public void visit(ReadStmt readStmt) {
		Obj var = SymTab.find(readStmt.getDesignator().obj.getName());
	}
    
    public void visit(ProgName progName) {
		progName.obj = SymTab.insert(Obj.Prog, progName.getProgName(), SymTab.noType);
		SymTab.openScope();
	}
    
    public void visit(Program program) {
    	nVars = SymTab.currentScope.getnVars();
		SymTab.chainLocalSymbols(program.getProgName().obj);
		SymTab.closeScope();
	}
    
    public void visit(Type type) {
		
		Obj typeNode = SymTab.find(type.getTypeName());
		if(typeNode == SymTab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", type);
			type.struct = SymTab.noType;
		}
		else {
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			}
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip", type);
				type.struct = SymTab.noType;
			}
		}			

    }
    
    
    public void visit(MethodTypeName methodTypeName) {
		currentMethod = SymTab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		SymTab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

    public void visit(MethodDecl methodDecl) {
    	if(!returnFound && currentMethod.getType() != SymTab.noType) {
    		report_error("Semanticka greska na liniji " + methodDecl.getLine() + " : funkcija " + currentMethod.getName() + " nema return iskaz! ", null);
    	}
		SymTab.chainLocalSymbols(currentMethod);
		SymTab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}
    
    public void visit(FormalParamDecl formalParamDecl) {
		report_info("Deklarisana promenljiva " + formalParamDecl.getParamName(), formalParamDecl);
		Obj varNode = SymTab.insert(Obj.Var, formalParamDecl.getParamName(), formalParamDecl.getType().struct);
		currentMethod.setLevel(currentMethod.getLevel() + 1);
		varNode.setFpPos(currentMethod.getLevel());
	}
    
    public void visit(SingleDesignator designator) {
		Obj obj = SymTab.find(designator.getName());
		if(obj == SymTab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getName() + " nije deklarisano! ", null);
		}
		else if(obj.getLevel() == 0) {
			report_info("Pristupanje globalnoj promenljivoj " + designator.getName() , designator);
		}
		else if(obj.getKind() == Obj.Meth) {
			report_info("Poziv funkcije " + designator.getName() , designator);
		}
		else {
			if(obj.getFpPos() == 0) {
				report_info("Pristupanje lokalnoj promenljivoj " + designator.getName() , designator);				
			}
			else {
				report_info("Pristupanje formalnom paramertru " + designator.getName() , designator);
			}
		}
		designator.obj = obj;
    }
    
    public void visit(ArrayDesignator designator) {
		Obj obj = SymTab.find(designator.getName());
		if(obj == SymTab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getName() + " nije deklarisano! ", null);
		}
		if(obj.getLevel() == 0) {
			report_info("Pristupanje globalnom nizu " + designator.getName() , designator);
		}
		else {
			if(obj.getFpPos() == 0) {
				report_info("Pristupanje lokalnom nizu " + designator.getName() , designator);				
			}
			else {
				report_info("Pristupanje nizu formalnom paramertur " + designator.getName() , designator);
			}
		}
		designator.obj = obj;
    }
    
    public void visit(ArrExpr arrExpr) {
		arrExpr.struct = arrExpr.getType().struct;
	}
    
    boolean isParamArray = false;
    
    public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		
		if(Obj.Meth == func.getKind()) {
			if(func.getLevel() != callFuncArgs.size()) {
				report_error("Semanticka greska " + func.getName() + " pogresan broj argumenata ", funcCall);
				funcCall.struct = func.getType();	
			}
			else if(isParamArray && func.getName().equals("len")) {
				funcCall.struct = func.getType();
			}
			else if(checkArgsType(func.getLocalSymbols())){
				report_error("Semanticka greska " + func.getName() + " pogresan tip argumenta", funcCall);
				funcCall.struct = func.getType();
			}
			else {
				funcCall.struct = func.getType();				
			}
		}
		else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija! ", null);
			funcCall.struct = SymTab.noType;
		}
		callFuncArgs.clear();
		isParamArray = false;
    }
    
    private int inWhile = 0;
    
    public void visit(Do d) {
		inWhile++;
	}
    
    public void visit(BreakExpr breakExpr) {
		if(inWhile == 0) {
    		report_error("Greska na liniji " + breakExpr.getLine() + " : break je dozvoljen samo u petljama! ", breakExpr);
    		errorDetected = true;
		}
	}
    
    public void visit(ContinueExpr coExpr) {
		if(inWhile == 0) {
    		report_error("Greska na liniji " + coExpr.getLine() + " : continue je dozvoljen samo u petljama! ", coExpr);
    		errorDetected = true;
		}
	}
    
    public void visit(MatchedDoWhile doWhile) {
		inWhile--;
	}
    
    public void visit(ProcCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		
		if(Obj.Meth == func.getKind()) {
			if(func.getLevel() != callFuncArgs.size()) {
				report_error("Semanticka greska " + func.getName() + " pogresan broj argumenata ", funcCall);
				
				funcCall.struct = func.getType();	
			}
			else if(isParamArray && func.getName().equals("len")) {
				funcCall.struct = func.getType();
			}
			else if(checkArgsType(func.getLocalSymbols())){
				report_error("Semanticka greska " + func.getName() + " pogresan tip argumenta", funcCall);
				funcCall.struct = func.getType();
			}
			else {
				funcCall.struct = func.getType();				
			}
		}
		else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija! ", null);
			funcCall.struct = SymTab.noType;
		}
		callFuncArgs.clear();
		isParamArray = false;
	}
    
    private boolean checkArgsType(Collection<Obj> actualArgs) {
    	Iterator<Obj> iterActual = actualArgs.iterator();
    	Iterator<Integer> iterCall = callFuncArgs.iterator();
    	
    	while(iterActual.hasNext() && iterCall.hasNext()) {
			if (iterActual.next().getType().getKind() != iterCall.next()) {
				return true;
			}
    	}
    	
    	return false;
    }
    
    public void visit(SingleActualParam singleActualParam) {
    	if(singleActualParam.getExpr().struct.getKind() == Struct.Array) {
    		callFuncArgs.add(singleActualParam.getExpr().struct.getElemType().getKind());
    		isParamArray = true;
    	}
    	else {
        	callFuncArgs.add(singleActualParam.getExpr().struct.getKind());    		
    	}
	}
    
    public void visit(NoActuals actuals) {
    	callFuncArgs.clear();
	}
    
    public void visit(TypeConst cnst) {
    	cnst.struct = cnst.getConst().struct;
	}
    
    public void visit(Fact fact) {
    	fact.struct = fact.getFactor().struct;
	}
    
    public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}
    
    public void visit(MatchedAddExpr matchedAddExpr) {
		matchedAddExpr.struct = matchedAddExpr.getMatchedExpr().struct;
	}
    
    public void visit(ConditionExpr condExpr) {
    	condExpr.struct = condExpr.getMatchedExpr1().struct;
	}
    
    public void visit(MinusTermExpr minusTermExpr) {
		minusTermExpr.struct = minusTermExpr.getTerm().struct;
	}
    
    public void visit(AddExpr addExpr) {
		Struct te = addExpr.getMatchedExpr().struct;
		Struct t = addExpr.getTerm().struct;
		if(t.getKind() == Struct.Array) {
			t = t.getElemType();
		}
		if(te.getKind() == Struct.Array) {
			te = te.getElemType();
		}
		
		if(te.equals(t) && te == SymTab.intType) {
			addExpr.struct = te;
		}
		else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu ", null);
			addExpr.struct = SymTab.noType;
		}
		
	}
    
    public void visit(MulExpr mulExpr) {
		Struct te = mulExpr.getFactor().struct;
		Struct t = mulExpr.getTerm().struct;
		if(t.getKind() == Struct.Array) {
			t = t.getElemType();
		}
		if(te.getKind() == Struct.Array) {
			te = te.getElemType();
		}
		
		if(te.equals(t) && te == SymTab.intType) {
			mulExpr.struct = te;
		}
		else {
			report_error("Greska na liniji " + mulExpr.getLine() + " : nekompatibilni tipovi u izrazu. ", null);
			mulExpr.struct = SymTab.noType;
		}
	}
    
    public void visit(ParenExpr parenExpr) {
    	parenExpr.struct = parenExpr.getExpr().struct;
	}
    
    public void visit(CharConst cnst) {
		cnst.struct = SymTab.charType;
	}
    
    public void visit(NumConst cnst) {
		cnst.struct = SymTab.intType;
		constVal = cnst.getN1();
	}
    
    public void visit(BoolConst boolConst) {
		boolConst.struct = SymTab.boolType;
	}
    
    public void visit(Var var) {
		var.struct = var.getDesignator().obj.getType();
	}
    
    public void visit(ReturnExpr returnExpr) {
		returnFound = true;
		Struct curMethType = currentMethod.getType();
		if(!curMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : tip izraza u return naredbi se ne slaze sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}
    }
    
    public void visit(Assignment assignment) {
    	if(SymTab.find(assignment.getDesignator().obj.getName()) == null) {
    		report_error("Greska na liniji " + assignment.getLine() + " : ime " + assignment.getDesignator().obj.getName() + " nije deklarisano! ", null);
    	}
    	else if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType())) {
			if(assignment.getDesignator().obj.getType().getElemType() == null && assignment.getExpr().struct.getElemType() != null) {
				if(!assignment.getExpr().struct.getElemType().assignableTo(assignment.getDesignator().obj.getType())) {
					report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! ", null);														
				}
			}
			else if(assignment.getExpr().struct != null) {
				if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType().getElemType())) {
					report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! ", null);		
				}
			}

		}
		if(assignment.getDesignator().obj.getKind() == Obj.Con) {
			report_error("Greska na liniji " + assignment.getLine() + " : nije dozvoljno menjanje konstantne vrednosti! ", null);	
		}
	}
    
    public void visit(RelCondFact condFact) {
    	if(condFact.getExpr().struct.getKind() != condFact.getExpr1().struct.getKind()) {
    		if(condFact.getExpr().struct.getKind() == Struct.Array) {
    			if(condFact.getExpr().struct.getElemType().getKind() != condFact.getExpr1().struct.getKind()) {
    	    		report_error("Greska na liniji " + condFact.getLine() + " : nekompatibilni tipovi u poredjenu! ", condFact);
    	    		errorDetected = true;
    			}
    		}
    		else if(condFact.getExpr1().struct.getKind() == Struct.Array) {
    			if(condFact.getExpr1().struct.getElemType().getKind() != condFact.getExpr().struct.getKind()) {
    	    		report_error("Greska na liniji " + condFact.getLine() + " : nekompatibilni tipovi u poredjenu! ", condFact);
    	    		errorDetected = true;
    			}
    		}
    	}
    	else if(condFact.getExpr().struct.getKind() == Struct.Array && condFact.getExpr1().struct.getKind() == Struct.Array) {
    		if(condFact.getExpr().struct.getElemType().getKind() != condFact.getExpr1().struct.getElemType().getKind()) {
        		report_error("Greska na liniji " + condFact.getLine() + " : nekompatibilni tipovi u poredjenu! ", condFact);
        		errorDetected = true;
    		}
    	}
	}
    
    
    public boolean passed() {
		return !errorDetected;
	}
}