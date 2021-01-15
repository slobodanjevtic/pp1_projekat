package rs.ac.bg.etf.pp1;

import java.util.HashMap;

import rs.etf.pp1.mj.runtime.Code;

public class JumpInstBackpatch {
	
	public static HashMap<Integer, JumpInstBackpatch> jumps = new HashMap<Integer, JumpInstBackpatch>();
	
	public static int 
		jumpInIf = 0,
		jumpAfterOrOp = 1,
		jumpOnElse = 2,
		jumpOut = 3;
	
	private int jmpAdr;
	private int jmpInstAdr;
	private int jmpOn;
	private int level;
	
	public JumpInstBackpatch(int jmpInstAdr, int level) {
		this.jmpInstAdr = jmpInstAdr;
		this.level = level;
		this.jmpAdr = 0;
		jmpOn = 0;
		jumps.put(jmpInstAdr, this);
	}
	
	public int getJmpAdr() {
		return jmpAdr;
	}
	public void setJmpAdr(int jmpAdr) {
		this.jmpAdr = jmpAdr;
	}
	public int getJmpInstAdr() {
		return jmpInstAdr;
	}
	public void setJmpInstAdr(int jmpInstAdr) {
		this.jmpInstAdr = jmpInstAdr;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getJmpOn() {
		return jmpOn;
	}
	public void setJmpOn(int jmpOn) {
		this.jmpOn = jmpOn;
	}

	public static void setJumpOn(int jmpInstAdr, int jmpOn) {
		jumps.get(jmpInstAdr).jmpOn = jmpOn;
	}
	
	public static void setJumpOnForAllInst(int jmpInstAdr, int jmpOn, int level) {
		for (JumpInstBackpatch jumpInstBackpatch : jumps.values()) {
			
			if(jumpInstBackpatch.jmpAdr == 0 && jumpInstBackpatch.level == level && 
					jumpInstBackpatch.jmpOn != jumpOut && jumpInstBackpatch.jmpInstAdr < jmpInstAdr) {
				jumpInstBackpatch.jmpOn = jmpOn;
			}
		}
	} 
	
	public static void setJmpAdr(int jmpAdr, int jmpOn, int level) {
		for (JumpInstBackpatch jumpInstBackpatch : jumps.values()) {
			
			if(jumpInstBackpatch.jmpAdr == 0 && jumpInstBackpatch.jmpOn == jmpOn && 
					jumpInstBackpatch.level == level && jumpInstBackpatch.jmpInstAdr < jmpAdr) {
				jumpInstBackpatch.jmpAdr = jmpAdr;
				Code.put2(jumpInstBackpatch.getJmpInstAdr() + 1, jmpAdr - jumpInstBackpatch.getJmpInstAdr());
			}
		}
	}

}
