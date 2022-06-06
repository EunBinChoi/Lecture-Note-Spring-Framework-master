package me.ioc.mycalculator;

import java.util.Scanner;

import me.ioc.integer.Int;
import me.ioc.operator.Calculator;

// IoC 컨테이너 입장 (객체 제어권 존재)
// 객체 생성 가능 (객체를 싱글톤 패턴으로 생성)
public class MainClass {
	// 객체 생성
	public static Int num1;
	public static Int num2;
	public static Calculator calculator;
	public static MyCalculator myCalculator;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("==================================");
			System.out.println("1. 연산자 선택 (add, sub, div, mul)");
			String opInput = sc.next();

			System.out.println("2. 값 입력    (num1, num2)");
			int numInput1 = sc.nextInt();
			int numInput2 = sc.nextInt();

			num1 = Int.getInstance(0);
			num2 = Int.getInstance(1);
			calculator = Calculator.getInstance();

			num1.setNumber(numInput1);
			num2.setNumber(numInput2);
			calculator.setOperator(opInput);

			new MyCalculator(num1.getNumber(), num2.getNumber(), calculator).result();
			System.out.println("&num1 : " + System.identityHashCode(num1));
			System.out.println("&num2 : " + System.identityHashCode(num2));
			System.out.println("&calculator : " + System.identityHashCode(calculator));
			System.out.println("==================================");
		}

	}

}
