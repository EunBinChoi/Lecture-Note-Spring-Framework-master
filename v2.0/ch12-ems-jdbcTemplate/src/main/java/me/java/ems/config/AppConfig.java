package me.java.ems.config;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.util.DriverDataSource;

//import com.mchange.v2.c3p0.DriverManagerDataSource;

import me.java.ems.dao.StudentDao;
import me.java.ems.database.DataBaseConnectionInfo;
import me.java.ems.info.EMSInformation;
import me.java.ems.service.StudentDeleteService;
import me.java.ems.service.StudentModifyService;
import me.java.ems.service.StudentRegisterService;
import me.java.ems.service.StudentSelectAllService;
import me.java.ems.service.StudentSelectService;

@Configuration // applicationContext.xml 대체하는 자바 클래스다!
@PropertySource("datasource.properties")
public class AppConfig implements EnvironmentAware {
	//@Autowired // 아직 객체가 생기기 전일 경우에는 null이 들어갈 수도 있음 ....
	Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.environment = environment;
	}

	// 빈의 이름을 지정하지 않으면 함수 이름이 빈의 이름으로 대체됨
	@Bean(name = "emsInformation")
	@Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public EMSInformation emsInformation() {
		EMSInformation emsInformation = new EMSInformation();
		emsInformation.setInfo("Education Management System program was developed in 2022.");
		emsInformation.setCopyRight(
				"COPYRIGHT(C) 2022 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		emsInformation.setVer("The version is 1.0");
		emsInformation.setsYear(2022);
		emsInformation.setsMonth(1);
		emsInformation.setsDay(1);
		emsInformation.seteYear(2022);
		emsInformation.seteMonth(2);
		emsInformation.seteDay(28);
		emsInformation.setDevelopers(Arrays.asList("Sally.", "Eloy.", "Jasper.", "Dillon.", "Kian."));
		emsInformation.setAdministrators(Map.of("Sally", "sally@springPjt.org", "Jasper", "jasper@springPjt.org"));
		emsInformation.setDbInfos(Map.of("dev", dataBaseConnectionInfoDev(), "real", dataBaseConnectionInfoReal()));
		return emsInformation;
	}

	@Bean(name = "dataBaseConnectionInfoDev")
	@Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo dataBaseConnectionInfoDev = new DataBaseConnectionInfo();
		dataBaseConnectionInfoDev.setDriver("oracle.jdbc.OracleDriver");
		dataBaseConnectionInfoDev.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataBaseConnectionInfoDev.setUserId("scott");
		dataBaseConnectionInfoDev.setUserPw("tiger");
		return dataBaseConnectionInfoDev;
	}

	@Bean(name = "dataBaseConnectionInfoReal")
	@Scope("singleton") // vs "prototype": 객체를 새로 만듦
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo dataBaseConnectionInfoReal = new DataBaseConnectionInfo();
		dataBaseConnectionInfoReal.setDriver("oracle.jdbc.OracleDriver");
		dataBaseConnectionInfoReal.setUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		dataBaseConnectionInfoReal.setUserId("masterid");
		dataBaseConnectionInfoReal.setUserPw("masterpw");
		return dataBaseConnectionInfoReal;
	}

	@Bean(name = "studentDao")
	@Scope("singleton")
	@Qualifier("dao")
	// @Named("dao")
	public StudentDao studentDao() {
		return new StudentDao();
	}

	@Bean(name = "dataSource")
	public DriverDataSource dataSource() {
		// 1) com.mchange.c3p0.DriverManagerDataSource (**)
		// 2) org.springframework.jdbc.datasource.DriverManagerDataSource (*)
		// 3) com.zaxxer.hikari.util.DriverDataSource (***)

		// 1) com.mchange.c3p0.DriverManagerDataSource
		// com.mchange.c3p0.DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// dataSource.setDriverClass(dataBaseConnectionInfoDev().getDriver());
		// dataSource.setJdbcUrl(dataBaseConnectionInfoDev().getUrl());
		// dataSource.setUser(dataBaseConnectionInfoDev().getUserId());
		// dataSource.setPassword(dataBaseConnectionInfoDev().getUserPw());

		// 2) org.springframework.jdbc.datasource.DriverManagerDataSource
//		org.springframework.jdbc.datasource.DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(dataBaseConnectionInfoDev().getDriver());
//		dataSource.setUrl(dataBaseConnectionInfoDev().getUrl());
//		dataSource.setUsername(dataBaseConnectionInfoDev().getUserId());
//		dataSource.setPassword(dataBaseConnectionInfoDev().getUserPw());

		// 3) com.zaxxer.hikari.util.DriverDataSource
		Properties properties = new Properties();
		//properties.setProperty("idleTimeout", "30000");
		com.zaxxer.hikari.util.DriverDataSource dataSource
		= new DriverDataSource(dataBaseConnectionInfoDev().getUrl(),
				dataBaseConnectionInfoDev().getDriver(),
				properties,
				dataBaseConnectionInfoDev().getUserId(),
				dataBaseConnectionInfoDev().getUserPw());

		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	///////////////////////////////////////////////////////////////////////////////
	@Bean(name = "selectAllService")
	@Scope("singleton")
	public StudentSelectAllService selectAllService() {
		return new StudentSelectAllService(studentDao());
	}

	@Bean(name = "selectService")
	@Scope("singleton")
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}

	@Bean(name = "registerService")
	@Scope("singleton")
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}

	@Bean(name = "modifyService")
	@Scope("singleton")
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}

	@Bean(name = "deleteService")
	@Scope("singleton")
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}

}
