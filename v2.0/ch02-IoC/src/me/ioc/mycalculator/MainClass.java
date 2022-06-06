package me.ioc.mycalculator;

import java.util.Scanner;

import me.ioc.operator.CalAdd;
import me.ioc.operator.CalDiv;
import me.ioc.operator.CalMul;
import me.ioc.operator.CalSub;

// IoC 컨테이너 입장 (객체 제어권 존재)
// 객체 생성 가능
public class MainClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		// 생성자를 이용한 객체 주입 방법
		new MyCalculator(3, 5, new CalAdd()).result();
		new MyCalculator(3, 5, new CalSub()).result();
		new MyCalculator(3, 5, new CalMul()).result();
		new MyCalculator(3, 5, new CalDiv()).result();

		// setter를 이용한 객체 주입 방법
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.setNum1(3);
		myCalculator.setNum2(5);
		myCalculator.setCal(new CalAdd());

		myCalculator.setNum1(3);
		myCalculator.setNum2(5);
		myCalculator.setCal(new CalSub());

		myCalculator.setNum1(3);
		myCalculator.setNum2(5);
		myCalculator.setCal(new CalMul());

		myCalculator.setNum1(3);
		myCalculator.setNum2(5);
		myCalculator.setCal(new CalDiv());
		
		sc.close();
	}

}
