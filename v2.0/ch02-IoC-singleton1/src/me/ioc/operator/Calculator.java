package me.ioc.operator;

public class Calculator {

	protected Calculator() {}

	public static Calculator getInstance(String op) {
		Calculator[] calculators = Calculators.getInstance();
		String[] opers = {"add", "sub", "mul", "div"};

		int i = 0;
		for(i = 0; i < opers.length; i++) {
			if(op != null) {
				if(opers[i].equals(op)) {
					break;
				}
			}
		}
		if(i == opers.length) return null;

		if(calculators[i] == null) {
			if(i == 0) calculators[i] = new CalAdd();
			else if(i == 1) calculators[i] = new CalSub();
			else if(i == 2) calculators[i] = new CalMul();
			else calculators[i] = new CalDiv();
		}
		return calculators[i];
	}

	public int operator(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
}
