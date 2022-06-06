package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.java.ems.dao.StudentDao;
import me.java.ems.entity.Student;

@Service
public class StudentModifyService {
	private StudentDao studentDao;

	public StudentModifyService() {
		super();
		System.out.println("StudentModifyService()");
	}
	@Autowired
	public StudentModifyService(@Qualifier("dao") StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentModifyService(StudentDao studentDao)");
	}
	public int modify(Student student) {
		if(studentDao.select(student.getsNum()) != null) {
			return studentDao.update(student);
		} else {
			System.out.println("[Warning] The Student information is not available.");
			return -1;
		}
	}
}
