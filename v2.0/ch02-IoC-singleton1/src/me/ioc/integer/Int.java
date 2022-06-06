package me.ioc.integer;

public class Int {
	protected Int() {}

	public static Integer getInstance(int pos) { // 0 ~ 9
		Integer[] ints = Ints.getInstance();

		if(ints[pos] == null) {
			ints[pos] = Integer.valueOf(pos);
		}
		return ints[pos];
	}
}
