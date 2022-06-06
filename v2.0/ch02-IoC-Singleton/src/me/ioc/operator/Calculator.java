package me.ioc.operator;

public class Calculator {

	protected Calculator() {}

	public static Calculator getInstance(String op) {
		Calculator[] calculators = Calculators.getInstance();

		if(op.equals("add")) {
			if(calculators[0] == null) {
				calculators[0] = new CalAdd();
			}
			return calculators[0];
		}
		else if(op.equals("sub")) {
			if(calculators[1] == null) {
				calculators[1] = new CalSub();
			}
			return calculators[1];
		}
		else if(op.equals("mul")) {
			if(calculators[2] == null) {
				calculators[2] = new CalMul();
			}
			return calculators[2];
		}
		else {
			if(calculators[3] == null) {
				calculators[3] = new CalDiv();
			}
			return calculators[3];
		}
	}

	public int operator(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
}
