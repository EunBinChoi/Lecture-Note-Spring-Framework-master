package me.spring.ioc;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 스프링 컨테이너 생성
		GenericXmlApplicationContext ctx  = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 빈 등록
		// 빈 의존관계 트리 생성
		// 빈 생성
		
		// -Aware 인터페이스의 setXXX() 메소드 호출 (EnvironmentAware, ApplicationContextAware ...)
		// "Environment, ApplicationContext... 객체가 호출된 후에 setXXX()를 호출해줘!"
		// 현재 클래스 내에 특정 환경과 관련된 객체가 필요한 경우
		// dataSource.setDriver(), dataSource.setUrl(), dataSource.setUserId(), dataSource.setUserPw()
		// Environment -> Property -> dataSource.properties 파일 읽음

		
		
		// 빈 등록 콜백 메서드
		// : 객체 밑에 있는 함수를 호출할 때는 무조건 객체가 생성된 후에 호출
		// : 만약 studentRegisterService 객체가 없으면 NullPointerException
		// : ex) studentRegisterService.init()
		// @PostContruct -> InitializingBean 인터페이스 afterPropertiesSet() -> @Bean (name ="", initMethod = "")
		
		
		Transportation transportation1 = ctx.getBean("transportation",  me.spring.ioc.Transportation.class);
		
		transportation1.move();
		System.out.println(transportation1);
		System.out.println(ctx.isSingleton("transportation"));
		System.out.println();
		
		
		Transportation transportation2 = ctx.getBean("transportation",  me.spring.ioc.Transportation.class);
		
		transportation2.move();
		System.out.println(transportation2);
		System.out.println(ctx.isSingleton("transportation"));
		
		System.out.println(transportation1 == transportation2);
		
		// 빈 소멸 콜백 메서드
		// @PreDestroy -> DisposableBean 인터페이스의 destroy() ->  @Bean (name ="", destroyMethod = "")
		// ex) 특정 객체의 close() 함수 호출시킬 때
		
		// 스프링 컨테이너 소멸
		ctx.close();
		
	}

}
