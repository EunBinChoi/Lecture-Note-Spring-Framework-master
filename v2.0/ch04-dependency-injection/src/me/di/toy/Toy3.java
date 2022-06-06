package me.di.toy;

import me.di.battery.Battery;

// 3번
// 배터리 분리형 장난감 (+ NormalBattery)
// 배터리가 떨어지면 배터리만 교체하면 됨 (setter)
// 기본적으로 배터리를 하나 넣어줌 (Battery를 외부에서 넣어주는 생성자가 있음)
public class Toy3 {
	private Battery battery;
	
	public Toy3(Battery battery) {
		this.battery = battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	@Override
	public String toString() {
		return "Toy3 [battery=" + battery + "]";
	}
	
}
