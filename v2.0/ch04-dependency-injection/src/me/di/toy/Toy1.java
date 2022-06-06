package me.di.toy;

import me.di.battery.Battery;
import me.di.battery.Toy1Battery;

// 1번
// 배터리 일체형 장난감 (+ Toy1Battery)
// 배터리가 떨어지면 장난감을 다시 사야함
// 배터리가 노멀 배터리가 아니기 때문에 다른 배터리랑 호환 불가능
// 다른 장난감에서는 사용 불가능한 배터리
public class Toy1 {
	private Battery battery;
	
	public Toy1() {
		battery = new Toy1Battery();
	}

	@Override
	public String toString() {
		return "Toy1 [battery=" + battery + "]";
	}
	
}
