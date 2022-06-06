package me.java.ems.service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

public class StudentModifyService {
	private StudentDao studentDao;

	public StudentModifyService() {
		super();
		System.out.println("StudentModifyService()");
	}
	public StudentModifyService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentModifyService(StudentDao studentDao)");
	}
	public void modify(Student student) {
		if(studentDao.select(student.getsNum()) != null) {
			studentDao.update(student);
		} else {
			System.out.println("[Warning] The Student information is not available.");
		}
	}
}
