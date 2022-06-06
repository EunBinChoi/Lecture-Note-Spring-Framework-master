package me.java.ems.config;

import java.util.Map;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import me.java.ems.database.DataBaseConnectionInfo;
import me.java.ems.info.EMSInformation;


/*
 * @ComponentScan: @Component, @Service, @Repository, @Controller 클래스들을
 *  			   자동으로 scan하여 스프링 컨테이너에 Bean으로 등록해주는 어노테이션
 *  			 : .xml, AppConfig에 Bean을 직접 등록할 필요가 없음
 *  			 : 스프링 어플리케이션의 초기 구동이 오래걸릴 수 있음
 * 속성: basePackages, baseClasses, filter (일부 클래스만 포함 (include), 제외 (exclude))
 *
 * */



// me.java.ems 패키지 하위에 있는 Spring Stereotype의 빈이 자동 생성

// @Autowired를 필드/setter: 빈 생성자 호출 (new Service()) (* 의존관계 이후 생각)
// ==> 파일 이름 순서대로 빈 생성자 호출
// ==> service, dao
// ==> service1, dao, service2 ~ 5

// ==> dao, service
// ==> dao, service1 ~ 5

// @Autowired가 생성자: new Service(dao) (**)

// @ComponentScan -> AppConfig 클래스 안에 직접 정의한 @Bean 생성
@Configuration // applicationContext.xml 대체하는 자바 클래스다!
@PropertySource("application.properties")
//@ComponentScan(basePackages= {"me.java.ems.service", "me.java.ems.dao"})
@ComponentScan(basePackages= {"me.java.ems"})
public class AppConfig implements EnvironmentAware { // Environment null 값 방지

	private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    // @Value를 호출할 때 클래스 이름.빈 이름으로 빈을 찾기 때문
    // PropertySourcesPlaceholderConfigurer와 같은 빈 팩토리 후처리기 (BeanFactoryPostProcessor)
    // 의 구현체들은 빈 정보를 설정하기 위해서 호출되는 객체이기 때문에 빈 생성 전에 만들어져야 함
    // @Value(${})
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo dataBaseConnectionInfo = new DataBaseConnectionInfo();
		dataBaseConnectionInfo.init(environment, "dev");
		return dataBaseConnectionInfo;
	}

	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo dataBaseConnectionInfo = new DataBaseConnectionInfo();
		dataBaseConnectionInfo.init(environment, "real");
		return dataBaseConnectionInfo;
	}

	@Bean
	public EMSInformation EMSInformation() {
		EMSInformation emsInformation = new EMSInformation();
		emsInformation.init(environment);

		Map<String, DataBaseConnectionInfo> dbInfo = Map.of(
					"dev",  dataBaseConnectionInfoDev(),
					"real", dataBaseConnectionInfoReal()
				);
		emsInformation.setDbInfos(dbInfo);
		return emsInformation;
	}
	/*
	// 빈의 이름을 지정하지 않으면 함수 이름이 빈의 이름으로 대체됨
	@Bean(name="emsInformation") @Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public EMSInformation emsInformation() {
		EMSInformation emsInformation = new EMSInformation();
		emsInformation.setInfo("Education Management System program was developed in 2022.");
		emsInformation.setCopyRight("COPYRIGHT(C) 2022 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		emsInformation.setVer("The version is 1.0");
		emsInformation.setsYear(2022);
		emsInformation.setsMonth(1);
		emsInformation.setsDay(1);
		emsInformation.seteYear(2022);
		emsInformation.seteMonth(2);
		emsInformation.seteDay(28);
		emsInformation.setDevelopers
		(Arrays.asList("Sally.", "Eloy.", "Jasper.", "Dillon.", "Kian."));
		emsInformation.setAdministrators(Map.of(
				"Sally",   "sally@springPjt.org",
				"Jasper",  "jasper@springPjt.org"));
		emsInformation.setDbInfos(Map.of(
				"dev",    dataBaseConnectionInfoDev(),
				"real",   dataBaseConnectionInfoReal()));
		return emsInformation;
	}
	@Bean(name="dataBaseConnectionInfoDev") @Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo dataBaseConnectionInfoDev = new DataBaseConnectionInfo();
		dataBaseConnectionInfoDev.setDriver("oracle.jdbc.driver.OracleDriver");
		dataBaseConnectionInfoDev.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataBaseConnectionInfoDev.setUserId("scott");
		dataBaseConnectionInfoDev.setUserPw("tiger");
		return dataBaseConnectionInfoDev;
	}
	@Bean(name="dataBaseConnectionInfoReal") @Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo dataBaseConnectionInfoReal = new DataBaseConnectionInfo();
		dataBaseConnectionInfoReal.setDriver("oracle.jdbc.driver.OracleDriver");
		dataBaseConnectionInfoReal.setUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		dataBaseConnectionInfoReal.setUserId("masterid");
		dataBaseConnectionInfoReal.setUserPw("masterpw");
		return dataBaseConnectionInfoReal;
	}
	@Bean(name="studentDao")
	@Scope("singleton")
	@Qualifier("dao")
	//@Named("dao")
	public StudentDao studentDao() {
		return new StudentDao();
	}
	@Bean(name="selectAllService") @Scope("singleton")
	public StudentSelectAllService selectAllService() {
		return new StudentSelectAllService(studentDao());
	}
	@Bean(name="selectService") @Scope("singleton")
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}
	@Bean(name="registerService") @Scope("singleton")
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}
	@Bean(name="modifyService") @Scope("singleton")
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}
	@Bean(name="deleteService") @Scope("singleton")
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}
	*/
}
