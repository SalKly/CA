public class Memory {

	private int[] mem;
	private int address;
	private int writeData;
	private int readData;
	private int memWriteSignal;
	private int memReadSignal;

	public Memory(int size) {
		this.mem = new int[size];
		this.address = 0;
		this.writeData = 0;
		this.readData = 0;
		this.memWriteSignal = 0;
		this.memReadSignal = 0;
	}

	public int[] getMem() {
		return mem;
	}

	public void setMem(int[] mem) {
		this.mem = mem;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getWriteData() {
		return writeData;
	}

	public void setWriteData(int writeData) {
		this.writeData = writeData;
	}

	public int getReadData() {
		return readData;
	}

	public void setReadData(int readData) {
		this.readData = readData;
	}

	public int getMemWriteSignal() {
		return memWriteSignal;
	}

	public void setMemWriteSignal(int memWriteSignal) {
		this.memWriteSignal = memWriteSignal;
	}

	public int getMemReadSignal() {
		return memReadSignal;
	}

	public void setMemReadSignal(int memReadSignal) {
		this.memReadSignal = memReadSignal;
	}

}