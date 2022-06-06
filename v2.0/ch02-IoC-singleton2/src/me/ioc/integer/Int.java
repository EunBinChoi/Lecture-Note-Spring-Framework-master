package me.ioc.integer;

public class Int {
	private int number;
	protected Int() {}

	public static Int getInstance(int pos) { // 0 ~ 9
		Int[] ints = Ints.getInstance();

		if(ints[pos] == null) {
			ints[pos] = new Int();
		}
		return ints[pos];
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

}
