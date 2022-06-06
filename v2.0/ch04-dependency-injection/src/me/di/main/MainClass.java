package me.di.main;

import me.di.battery.Battery;
import me.di.battery.NormalBattery;
import me.di.toy.Toy1;
import me.di.toy.Toy2;
import me.di.toy.Toy3;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1번 장난감
		Toy1 toy1 = new Toy1();
		System.out.println(toy1);
		
		// 이후에 배터리를 교체하고 싶을 경우
		// 다시 장난감을 구매해야 함
		// (배터리를 독립적인 객체로 보고 있지 않음)
		toy1 = new Toy1();
		
		
		///////////////////////////////////////////
		// 2번 장난감 (+ NormalBattery)
		// setter
		Toy2 toy2 = new Toy2();
		System.out.println(toy2);
		
		// 2번 장난감은 처음 구매시 배터리가 같이 없기 때문에
		// 직접 사서 작동
		Battery battery2 = new NormalBattery();
		toy2.setBattery(battery2);
		System.out.println(toy2);
		
		// 이후에 배터리 교체
		battery2 = new NormalBattery();
		toy2.setBattery(battery2);
		System.out.println(toy2);
		
		///////////////////////////////////////////
		// 3번 장난감 (+ NormalBattery) (제일 이상적임)
		// 생성자, setter
		
		// 3번 장난감은 처음 구매시 배터리가 같이 있음
		Toy3 toy3 = new Toy3(new NormalBattery());
		System.out.println(toy3);
		
		// 이후에 배터리를 교체
		Battery battery3 = new NormalBattery();
		toy3.setBattery(battery3);
		System.out.println(toy3);
	}

}
