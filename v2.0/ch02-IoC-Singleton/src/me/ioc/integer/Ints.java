package me.ioc.integer;

public class Ints {
	private static Integer[] ints = null;

	protected Ints() {}
	public static Integer[] getInstance() {
		if(ints == null) {
			ints = new Integer[10];
		}
		return ints;
	}
}
