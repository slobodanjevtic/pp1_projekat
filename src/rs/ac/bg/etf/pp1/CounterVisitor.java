package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.ActualParam;
import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor {
		
		public void visit(FormalParamDecl formalParamDecl) {
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor {
		
		public void visit(VarDecl varDecl) {
			count++;
		}
	}
	
	public static class ActualParamCounter extends CounterVisitor {
		
		public void visit(ActualParam actualParam) {
			count++;
		}
	}
	
}
