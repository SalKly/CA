public class Register {

	private String name;
	private int data;

	public Register(String name) {
		this.name = name;
		this.data = 0;
	}

	public String getName() {
		return name;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		if (this.name.equals("R0")) {
			System.out.println("Cannot write to R0");
		} else {
			this.data = data;
			if (!this.name.equals("pc")) {
				System.out.println("Wrote " + data + " to " + this.name);
			}
		}
	}

}
