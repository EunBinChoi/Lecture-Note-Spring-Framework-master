package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

@Service
public class StudentSelectService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	@Autowired // 빈 생성자 무조건 필요!!
	private StudentDao studentDao;

	public StudentSelectService() {
		super();
		System.out.println("StudentSelectService()");
	}
//	@Autowired
	public StudentSelectService(StudentDao studentDao) {
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
