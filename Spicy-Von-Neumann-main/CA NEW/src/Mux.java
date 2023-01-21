public class Mux {

	private int input1;
	private int input2;
	private int output;

	public Mux() {
		this.input1 = 0;
		this.input2 = 0;
		this.output = 0;
	}

	public int getInput1() {
		return input1;
	}

	public void setInput1(int input1) {
		this.input1 = input1;
	}

	public int getInput2() {
		return input2;
	}

	public void setInput2(int input2) {
		this.input2 = input2;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int control) {
		if (control == 0) {
			this.output = this.input1;
		} else {
			this.output = this.input2;
		}
	}

}
