package me.di.toy;

import me.di.battery.Battery;

// 2번
// 배터리 분리형 장난감 (+ NormalBattery)
// 배터리가 떨어지면 배터리를 교체하면 됨 (setter)
// 기본적으로 배터리를 넣어주지는 않음 (Battery를 외부에서 넣어주는 생성자가 없음)
public class Toy2 {
	private Battery battery;
	
	public Toy2() {}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	@Override
	public String toString() {
		return "Toy2 [battery=" + battery + "]";
	}
	
}
