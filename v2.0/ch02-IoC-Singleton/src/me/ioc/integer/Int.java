package me.ioc.integer;


public class Int {
	protected Int() {}

	public static Integer getInstance(int value) { // 0 ~ 9
		Integer[] ints = Ints.getInstance();

		if(ints[value] == null) {
			ints[value] = Integer.valueOf(value);
		}
		return ints[value];
	}
}
