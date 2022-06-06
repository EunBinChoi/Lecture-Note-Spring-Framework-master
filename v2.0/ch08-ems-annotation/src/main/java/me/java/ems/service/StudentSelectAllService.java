package me.java.ems.service;

import java.util.Map;

import me.java.ems.beans.Student;
import me.java.ems.dao.StudentDao;

//@Service
public class StudentSelectAllService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	private StudentDao studentDao;


	public StudentSelectAllService() {
		super();
		System.out.println("StudentSelectAllService()");
	}
	//@Autowired
	public StudentSelectAllService(StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectAllService(StudentDao studentDao)");
	}
	public Map<String, Student> selectAll() {
		return studentDao.selectAll();
	}
}
