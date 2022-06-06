package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.java.ems.dao.StudentDao;
import me.java.ems.entity.Student;



@Service
public class StudentRegisterService {
	private StudentDao studentDao;


	public StudentRegisterService() {
		super();
		System.out.println("StudentRegisterService()");
	}
	@Autowired
	public StudentRegisterService(@Qualifier("dao") StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentRegisterService(StudentDao studentDao)");
	}
	public int register(Student student) {
		if(studentDao.select(student.getsNum()) == null) {
			return studentDao.insert(student);
		} else {
			System.out.println("[Warning] The Student has already registered.");
			return -1;
		}
	}
}
