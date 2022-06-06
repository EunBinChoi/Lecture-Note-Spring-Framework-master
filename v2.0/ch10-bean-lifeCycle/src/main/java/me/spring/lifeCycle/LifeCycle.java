package me.spring.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LifeCycle implements InitializingBean, DisposableBean {

	private ZnotherLifeCycle znotherLifeCycle;

	public LifeCycle() {
		super();
		System.out.println("LifeCycle()");
	}

	@Autowired
	public LifeCycle(ZnotherLifeCycle znotherLifeCycle) {
		super();
		this.znotherLifeCycle = znotherLifeCycle;
		System.out.println("LifeCycle(ZnotherLifeCycle znotherLifeCycle)");
	}

	public void setAnotherLifeCycle(ZnotherLifeCycle znotherLifeCycle) {
		this.znotherLifeCycle = znotherLifeCycle;
	}

	// 빈 생성 메소드
	@PostConstruct
	public void postConstruct() {
		System.out.println(this.getClass().getName() + " postConstruct()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + " afterPropertiesSet()");
	}

	public void initMethod() {
		System.out.println(this.getClass().getName() + " initMethod()");
	}

	//////////////////////////////////////////////
	// 빈 소멸 메소드

	@PreDestroy
	public void preDestory() {
		System.out.println(this.getClass().getName() + " preDestory()");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + " destroy()");
	}

	public void destroyMethod() {
		System.out.println(this.getClass().getName() + " destroyMethod()");
	}


}
