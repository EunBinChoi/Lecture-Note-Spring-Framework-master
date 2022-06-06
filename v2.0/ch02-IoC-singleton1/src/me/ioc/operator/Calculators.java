package me.ioc.operator;

public class Calculators {
	private static Calculator[] calculators = null;

	protected Calculators() {}
	public static Calculator[] getInstance() {
		if(calculators == null) {
			calculators = new Calculator[4];
		}
		return calculators;
	}
}
