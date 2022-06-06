package me.ioc.integer;

public class Ints {
	private static Int[] ints = null;

	protected Ints() {}
	public static Int[] getInstance() {
		if(ints == null) {
			ints = new Int[2];
		}
		return ints;
	}
}
