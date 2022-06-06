package me.spring.lifeCycle;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"me.spring.lifeCycle"})
// @Bean 등록시 사용한 initMethod, destroyMethod 등록 불가
public class AppConfig {
	// 의존관계가 없을 때 빈 로딩되는 순서는 빈의 클래스 파일 이름 순서와 동일함
	// anotherLifeCycle -> lifeCycle

	// 의존관계가 있을 때 A <- B
	// 1) 생성자 @Autowired
	// B를 먼저 생성하고 A를 생성

	// 2) Setter/필드 @AUtowired
	// 의존관계 무시하고 클래스 파일 이름 순서와 동일하게 빈 생성
	// 이후에 의존관계 생각!

	//////////////////////////////////////////////////////////////

//	@Bean(name="lifeCycle", initMethod="initMethod", destroyMethod="destroyMethod")
//	public LifeCycle lifeCycle() {
//		return new LifeCycle(anotherLifeCycle());
//	}
//
//	@Bean(name="anotherLifeCycle", initMethod="anotherInitMethod", destroyMethod="anotherDestroyMethod")
//	public AnotherLifeCycle anotherLifeCycle() {
//		return new AnotherLifeCycle();
//	}
}
