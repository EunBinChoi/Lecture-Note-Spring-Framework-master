package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.java.ems.dao.StudentDao;
import me.java.ems.entity.Student;

@Service
public class StudentDeleteService {
	private StudentDao studentDao;

	public StudentDeleteService() {
		super();
		System.out.println("StudentDeleteService()");
	}
	@Autowired
	public StudentDeleteService(@Qualifier("dao") StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentDeleteService(StudentDao studentDao)");
	}
	public int delete(Student student) {
		if(studentDao.select(student.getsNum()) != null) {
			return studentDao.delete(student);
		} else {
			System.out.println("[Warning] The Student information is not available.");
			return -1;
		}
	}

}
