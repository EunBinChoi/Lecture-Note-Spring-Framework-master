package me.java.ems.service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

public class StudentDeleteService {
	private StudentDao studentDao;

	public StudentDeleteService() {
		super();
		System.out.println("StudentDeleteService()");
	}
	public StudentDeleteService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentDeleteService(StudentDao studentDao)");
	}
	public void delete(Student student) {
		if(studentDao.select(student.getsNum()) != null) {
			studentDao.delete(student.getsNum());
		} else {
			System.out.println("[Warning] The Student information is not available.");
		}
	}

}
