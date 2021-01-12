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
	
	public void visit(SingleVarDecl varDecl){
		varDeclCount++;
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct);
		
	}
	
    public void visit(PrintStmt print) {
    	if(print.getExpr().struct != Tab.intType && print.getExpr().struct != Tab.charType) {
    		report_error("Semanticka greska na liniji " + print.getLine() + ": Operand instrunkcije PRINT mora biti char ili int tipa! ", null);
    	}
		printCallCount++;
	}
    
    public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
    
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
    
    public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
		}
		else {
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			}
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
    }
    
    public void visit(MethodTypeName methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

    public void visit(MethodDecl methodDecl) {
    	if(!returnFound && currentMethod.getType() != Tab.noType) {
    		report_error("Semanticka greska na liniji " + methodDecl.getLine() + " : funkcija " + currentMethod.getName() + " nema return iskaz! ", null);
    	}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}
    
    public void visit(FormalParamDecl formalParamDecl) {
		report_info("Deklarisana promenljiva " + formalParamDecl.getParamName(), formalParamDecl);
		Obj varNode = Tab.insert(Obj.Var, formalParamDecl.getParamName(), formalParamDecl.getType().struct);
		//currentMethod.setLevel(currentMethod.getLevel() + 1);
	}
    
    public void visit(Designator designator) {
		Obj obj = Tab.find(designator.getName());
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getName() + " nije deklarisano! ", null);
		}
		designator.obj = obj;
    }
    
    public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		
		if(Obj.Meth == func.getKind()) {
			if(Tab.noType == func.getType()) {
				report_error("Semanticka greska " + func.getName() + " ne moze se koristiti u izrazima jer nema povratnu vrednost ", funcCall);
			}
			else if(func.getLocalSymbols().size() != callFuncArgs.size()) {
				report_error("Semanticka greska " + func.getName() + " pogresan broj argumenata ", funcCall);
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
			funcCall.struct = Tab.noType;
		}
		callFuncArgs.clear();
    }
    
    public void visit(ProcCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		
		if(Obj.Meth == func.getKind()) {
			if(Tab.noType == func.getType()) {
				report_error("Semanticka greska " + func.getName() + " ne moze se koristiti u izrazima jer nema povratnu vrednost ", funcCall);
			}
			else if(func.getLocalSymbols().size() != callFuncArgs.size()) {
				report_error("Semanticka greska " + func.getName() + " pogresan broj argumenata ", funcCall);
				
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
			funcCall.struct = Tab.noType;
		}
		callFuncArgs.clear();
	}
    
    private boolean checkArgsType(Collection<Obj> actualArgs) {
    	Iterator<Obj> iterActual = actualArgs.iterator();
    	Iterator<Integer> iterCall = callFuncArgs.iterator();
    	
    	while(iterActual.hasNext() && iterCall.hasNext()) {
			if (!(iterActual.next().getKind() == iterCall.next()))
				return true;
    	}
    	
    	return false;
    }
    
    public void visit(SingleActualParam singleActualParam) {
    	callFuncArgs.add(singleActualParam.getExpr().struct.getKind());
	}
    
    public void visit(NoActuals actuals) {
    	callFuncArgs.clear();
	}
    
    public void visit(Fact fact) {
    	fact.struct = fact.getFactor().struct;
	}
    
    public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}
    
    public void visit(MinusTermExpr minusTermExpr) {
		minusTermExpr.struct = minusTermExpr.getTerm().struct;
	}
    
    public void visit(AddExpr addExpr) {
		Struct te = addExpr.getExpr().struct;
		if(te == null) {
			
		}
		Struct t = addExpr.getTerm().struct;
		
		if(te.equals(t) && te == Tab.intType) {
			addExpr.struct = te;
		}
		else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu ", null);
			addExpr.struct = Tab.noType;
		}
		
	}
    
    public void visit(MulExpr mulExpr) {
		Struct te = mulExpr.getFactor().struct;
		Struct t = mulExpr.getTerm().struct;
		
		if(te.equals(t) && te == Tab.intType) {
			mulExpr.struct = te;
		}
		else {
			report_error("Greska na liniji " + mulExpr.getLine() + " : nekompatibilni tipovi u izrazu. ", null);
			mulExpr.struct = Tab.noType;
		}
	}
    
    public void visit(ParenExpr parenExpr) {
    	parenExpr.struct = parenExpr.getExpr().struct;
	}
    
    public void visit(CharConst cnst) {
		cnst.struct = Tab.charType;
	}
    
    public void visit(NumConst cnst) {
		cnst.struct = Tab.intType;
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
		if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType())) {
			report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! ", null);
		}
	}
    
    public void visit(RelCondFact condFact) {
    	if(condFact.getExpr().struct.getKind() != condFact.getExpr1().struct.getKind()) {
    		report_error("Greska na liniji " + condFact.getLine() + " : nekompatibilni tipovi u poredjenu! ", condFact);
    		errorDetected = true;
    	}
	}
    
    public boolean passed() {
		return !errorDetected;
	}
}