package me.spring.lifeCycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext ctx
			= new AnnotationConfigApplicationContext(AppConfig.class);


		// 빈 생성
		// @PostContruct -> afterPropertiesSet() -> initMethod()

		// 빈 소멸
		// @PreDestroy -> destroy() -> destroyMethod()

		ctx.close();
	}

}
