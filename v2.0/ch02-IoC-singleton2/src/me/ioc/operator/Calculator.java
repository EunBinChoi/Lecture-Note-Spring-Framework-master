package me.ioc.operator;

public class Calculator {
	private static Calculator c;
	private String operator;
	protected Calculator() {}

	public static Calculator getInstance() {
		if(c == null) {
			c = new Calculator();
		}
		return c;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int operator(int x, int y) {
		// TODO Auto-generated method stub
		if(operator == null) return -1;
		if(operator.equals("add")) return x + y;
		else if(operator.equals("sub")) return x - y;
		else if(operator.equals("div")) return x / y;
		else return x * y;
	}
}
