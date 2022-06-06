package me.java.ems.service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

public class StudentRegisterService {
	private StudentDao studentDao;

	public StudentRegisterService() {
		super();
		System.out.println("StudentRegisterService()");
	}
	public StudentRegisterService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentRegisterService(StudentDao studentDao)");
	}
	public void register(Student student) {
		if(studentDao.select(student.getsNum()) == null) {
			studentDao.insert(student);
		} else {
			System.out.println("[Warning] The Student has already registered.");
		}
	}
}
