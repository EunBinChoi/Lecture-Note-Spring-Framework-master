package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

@Service
public class StudentDeleteService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	@Autowired // 빈 생성자 무조건 필요!!
	private StudentDao studentDao;

	public StudentDeleteService() {
		super();
		System.out.println("StudentDeleteService()");
	}
//	@Autowired
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
