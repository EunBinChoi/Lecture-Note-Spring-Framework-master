package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

@Service
public class StudentModifyService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	@Autowired // 빈 생성자 무조건 필요!!
	private StudentDao studentDao;

	public StudentModifyService() {
		super();
		System.out.println("StudentModifyService()");
	}
//	@Autowired
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
