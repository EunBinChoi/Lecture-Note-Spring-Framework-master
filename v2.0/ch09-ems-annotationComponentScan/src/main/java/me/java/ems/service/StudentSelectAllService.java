package me.java.ems.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

@Service
public class StudentSelectAllService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	@Autowired // 빈 생성자 무조건 필요!!
	private StudentDao studentDao;

	public StudentSelectAllService() {
		super();
		System.out.println("StudentSelectAllService()");
	}
//	@Autowired
	public StudentSelectAllService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectAllService(StudentDao studentDao)");
	}
	public Map<String, Student> selectAll() {
		return studentDao.selectAll();
	}
}
