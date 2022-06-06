package me.spring.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.spring.ems.dao.StudentDao;
import me.spring.ems.entity.Student;

@Service
public class StudentSelectAllService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
	private StudentDao studentDao;


	public StudentSelectAllService() {
		super();
		System.out.println("StudentSelectAllService()");
	}
	@Autowired
	public StudentSelectAllService(@Qualifier("dao") StudentDao studentDao) {
		this.studentDao = studentDao;
		System.out.println("StudentSelectAllService(StudentDao studentDao)");
	}
	public List<Student> selectAll() {
		return studentDao.selectAll();
	}
}
