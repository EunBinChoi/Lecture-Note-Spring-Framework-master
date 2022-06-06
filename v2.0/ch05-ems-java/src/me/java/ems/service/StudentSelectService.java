package me.java.ems.service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

public class StudentSelectService {
	private StudentDao studentDao;

	public StudentSelectService() {
		super();
		System.out.println("StudentSelectService()");
	}
	public StudentSelectService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectService(StudentDao studentDao)");
	}
	public Student select(String sNum) {
		if(studentDao.select(sNum) != null) {
			return studentDao.select(sNum);
		} else {
			System.out.println("[Warning] The Student information is not available.");
			return null;
		}
	}
}
