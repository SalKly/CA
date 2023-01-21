public class Alu {

	private int inputA;
	private int inputB;
	private int output;
	private int control;
	private int zero;

	public Alu() {
		this.inputA = 0;
		this.inputB = 0;
		this.output = 0;
		this.control = 0;
		this.zero = 0;
	}

	public int getInputA() {
		return inputA;
	}

	public void setInputA(int inputA) {
		this.inputA = inputA;
	}

	public int getInputB() {
		return inputB;
	}

	public void setInputB(int inputB) {
		this.inputB = inputB;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		switch (this.control) {
			case 0:
				this.output = this.inputA + this.inputB;
				break;
			case 1:
				this.output = this.inputA - this.inputB;
				break;
			case 2:
				this.output = this.inputA * this.inputB;
				break;
			case 3:
				this.output = this.inputA & this.inputB;
				break;
			case 4:
				this.output = this.inputA | this.inputB;
				break;
			case 5:
				this.output = this.inputA << this.inputB;
				break;
			case 6:
				this.output = this.inputA >>> this.inputB;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + control);
		}
		if (this.output == 0) {
			this.zero = 1;
		} else {
			this.zero = 0;
		}
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public int getZero() {
		return zero;
	}

}
