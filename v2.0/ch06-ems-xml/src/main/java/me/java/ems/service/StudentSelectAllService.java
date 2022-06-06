package me.java.ems.service;

import java.util.Map;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

public class StudentSelectAllService {
	private StudentDao studentDao;

	public StudentSelectAllService() {
		super();
		System.out.println("StudentSelectAllService()");
	}
	public StudentSelectAllService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectAllService(StudentDao studentDao)");
	}
	public Map<String, Student> selectAll() {
		return studentDao.selectAll();
	}
}
