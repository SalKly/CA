public class RegisterFile {

	private Register[] regFile;
	private int regWriteSignal;
	private int readReg1;
	private int readReg2;
	private int writeReg;
	private int readData1;
	private int readData2;
	private int writeData;

	public RegisterFile() {
		this.regFile = new Register[32];
		for (int i = 0; i < 32; i++) {
			regFile[i] = new Register("R" + i);
		}
	}

	public Register[] getRegFile() {
		return regFile;
	}

	public void setRegFile(Register[] regFile) {
		this.regFile = regFile;
	}

	public int getRegWriteSignal() {
		return regWriteSignal;
	}

	public void setRegWriteSignal(int regWriteSignal) {
		this.regWriteSignal = regWriteSignal;
	}

	public int getReadReg1() {
		return readReg1;
	}

	public void setReadReg1(int readReg1) {
		this.readReg1 = readReg1;
		this.setReadData1(this.getRegFile()[readReg1].getData());
	}

	public int getReadReg2() {
		return readReg2;
	}

	public void setReadReg2(int readReg2) {
		this.readReg2 = readReg2;
		this.setReadData2(this.getRegFile()[readReg2].getData());
	}

	public int getWriteReg() {
		return writeReg;
	}

	public void setWriteReg(int writeReg) {
		this.writeReg = writeReg;
	}

	public int getReadData1() {
		return readData1;
	}

	public void setReadData1(int readData1) {
		this.readData1 = readData1;
	}

	public int getReadData2() {
		return readData2;
	}

	public void setReadData2(int readData2) {
		this.readData2 = readData2;
	}

	public int getWriteData() {
		return writeData;
	}

	public void setWriteData(int writeData) {
		if (this.regWriteSignal == 1) {
			this.getRegFile()[this.getWriteReg()].setData(writeData);
		}
	}

}