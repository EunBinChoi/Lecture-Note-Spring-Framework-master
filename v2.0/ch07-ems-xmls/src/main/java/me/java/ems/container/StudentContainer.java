package me.java.ems.container;

import java.util.Arrays;
import java.util.Map;

import me.java.ems.dao.StudentDao;
import me.java.ems.database.DataBaseConnectionInfo;
import me.java.ems.info.EMSInformation;
import me.java.ems.service.StudentDeleteService;
import me.java.ems.service.StudentModifyService;
import me.java.ems.service.StudentRegisterService;
import me.java.ems.service.StudentSelectAllService;
import me.java.ems.service.StudentSelectService;

// Spring 컨테이너 역할 (applicationContext.xml)
public class StudentContainer {
	private DataBaseConnectionInfo dataBaseConnectionInfoReal;
	private DataBaseConnectionInfo dataBaseConnectionInfoDev;
	private EMSInformation emsInformation;
	
	private StudentDao studentDao;
	private StudentSelectAllService selectAllService;
	private StudentSelectService selectService;
	private StudentRegisterService registerService;
	private StudentModifyService modifyService;
	private StudentDeleteService deleteService;

	public StudentContainer() {
		dataBaseConnectionInfoReal = new DataBaseConnectionInfo("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.0.1:1521:xe", "masterid", "masterpw");
		dataBaseConnectionInfoDev  = new DataBaseConnectionInfo("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		emsInformation             = new EMSInformation();
		emsInformation.setInfo("Education Management System program was developed in 2022.");
		emsInformation.setCopyRight("COPYRIGHT(C) 2022 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		emsInformation.setVer("The version is 1.0");
		emsInformation.setsYear(2022);
		emsInformation.setsMonth(1);
		emsInformation.setsDay(1);
		emsInformation.seteYear(2022);
		emsInformation.seteMonth(2);
		emsInformation.seteDay(28);
		emsInformation.setDevelopers(Arrays.asList("Sally.", "Eloy.", "Jasper", "Dillon.", "Kian."));
		emsInformation.setAdministrators(Map.of(
				"Sally",   "sally@springPjt.org",
				"Jasper",  "jasper@springPjt.org"));
		emsInformation.setDbInfos(Map.of(
				"dev",  dataBaseConnectionInfoDev,
				"real", dataBaseConnectionInfoReal));
		
		////////////////////////////////////////////////////////////////
		
		studentDao       = new StudentDao();
		selectAllService = new StudentSelectAllService(studentDao);
		selectService    = new StudentSelectService(studentDao);
		registerService  = new StudentRegisterService(studentDao);
		modifyService    = new StudentModifyService(studentDao);
		deleteService    = new StudentDeleteService(studentDao);
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public EMSInformation getEmsInformation() {
		return emsInformation;
	}

	public StudentSelectAllService getSelectAllService() {
		return selectAllService;
	}

	public StudentSelectService getSelectService() {
		return selectService;
	}

	public StudentRegisterService getRegisterService() {
		return registerService;
	}

	public StudentModifyService getModifyService() {
		return modifyService;
	}

	public StudentDeleteService getDeleteService() {
		return deleteService;
	}




}
