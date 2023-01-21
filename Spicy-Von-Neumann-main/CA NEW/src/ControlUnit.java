public class ControlUnit {

	private int opcode;
	private int ALUop;
	private int ALUSrc;
	private int shift;
	private int regWrite;
	private int memRead;
	private int memWrite;
	private int branch;
	private int memToReg;
	private int jump;
	private int readToReg;

	public ControlUnit() {
		this.opcode = 0;
		this.ALUop = 0;
		this.ALUSrc = 0;
		this.shift = 0;
		this.regWrite = 0;
		this.memRead = 0;
		this.memWrite = 0;
		this.branch = 0;
		this.memToReg = 0;
		this.jump = 0;
		this.readToReg = 0;
	}

	public int getOpcode() {
		return opcode;
	}

	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	public int getALUop() {
		return ALUop;
	}

	public void setALUop(int aLUop) {
		ALUop = aLUop;
	}

	public int getALUSrc() {
		return ALUSrc;
	}

	public void setALUSrc(int aLUSrc) {
		ALUSrc = aLUSrc;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getRegWrite() {
		return regWrite;
	}

	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}

	public int getMemRead() {
		return memRead;
	}

	public void setMemRead(int memRead) {
		this.memRead = memRead;
	}

	public int getMemWrite() {
		return memWrite;
	}

	public void setMemWrite(int memWrite) {
		this.memWrite = memWrite;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getMemToReg() {
		return memToReg;
	}

	public void setMemToReg(int memToReg) {
		this.memToReg = memToReg;
	}

	public int getJump() {
		return jump;
	}

	public void setJump(int jump) {
		this.jump = jump;
	}

	public int getReadToReg() {
		return readToReg;
	}

	public void setReadToReg(int readToReg) {
		this.readToReg = readToReg;
	}

	public void generateSignals(int opcode) {
		this.opcode = opcode;
		switch (opcode) {
			case 0: // ADD
				this.setRegWrite(1);
				this.setALUop(0);
				this.setALUSrc(0);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 1: // SUB
				this.setRegWrite(1);
				this.setALUop(1);
				this.setALUSrc(0);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 2: // MULI
				this.setRegWrite(1);
				this.setALUop(2);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 3: // ADDI
				this.setRegWrite(1);
				this.setALUop(0);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 4: // BNE
				this.setRegWrite(0);
				this.setALUop(1);
				this.setALUSrc(0);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(1);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(1);
				break;
			case 5: // ANDI
				this.setRegWrite(1);
				this.setALUop(3);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 6: // ORI
				this.setRegWrite(1);
				this.setALUop(4);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 7: // J
				this.setRegWrite(0);
				this.setALUop(0);
				this.setALUSrc(0);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(1);
				this.setReadToReg(0);
				break;
			case 8: // SLL
				this.setRegWrite(1);
				this.setALUop(5);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(1);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 9: // SRL
				this.setRegWrite(1);
				this.setALUop(6);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(0);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(1);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 10: // LW
				this.setRegWrite(1);
				this.setALUop(0);
				this.setALUSrc(1);
				this.setMemRead(1);
				this.setMemWrite(0);
				this.setMemToReg(0);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(0);
				break;
			case 11: // SW
				this.setRegWrite(0);
				this.setALUop(0);
				this.setALUSrc(1);
				this.setMemRead(0);
				this.setMemWrite(1);
				this.setMemToReg(1);
				this.setBranch(0);
				this.setShift(0);
				this.setJump(0);
				this.setReadToReg(1);
				break;
		}
	}

}