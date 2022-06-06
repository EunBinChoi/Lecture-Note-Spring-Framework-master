package me.ioc.mycalculator;

import me.ioc.operator.Calculator;

// 개발자 입장 (객체에 제어권이 없음 ㅜㅜ)
// num1, num2, cal 생성 권한이 없음 (new 불가)
// 외부에서 생성된 객체를 받는 방법? (생성자, setter)
public class MyCalculator {
	private int num1, num2;
	private Calculator cal;

	public MyCalculator(int num1, int num2, Calculator cal) {
		this.num1 = num1;
		this.num2 = num2;
		this.cal = cal;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public Calculator getCal() {
		return cal;
	}
	public void setCal(Calculator cal) {
		this.cal = cal;
	}
	public void result() {
		System.out.println("result : " + cal.operator(num1, num2));
	}
}
