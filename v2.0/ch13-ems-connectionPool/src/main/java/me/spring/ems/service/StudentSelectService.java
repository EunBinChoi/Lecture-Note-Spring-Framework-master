package me.spring.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.spring.ems.dao.StudentDao;
import me.spring.ems.entity.Student;

@Service
public class StudentSelectService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	private StudentDao studentDao;

	public StudentSelectService() {
		super();
		System.out.println("StudentSelectService()");
	}
	@Autowired
	public StudentSelectService(@Qualifier("dao") StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectService(StudentDao studentDao)");
	}
	public Student select(String sNum) {
		if(studentDao.select(sNum) != null) {
			return studentDao.select(sNum);
		} else {
			System.out.println("[Warning] The Student information is not available.");
		}
		return null;
	}
}
