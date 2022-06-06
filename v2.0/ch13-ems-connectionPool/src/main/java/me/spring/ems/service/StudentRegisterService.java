package me.spring.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.spring.ems.dao.StudentDao;
import me.spring.ems.entity.Student;

@Service
public class StudentRegisterService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
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
	public void register(Student student) {
		if(studentDao.select(student.getsNum()) == null) {
			studentDao.insert(student);
		} else {
			System.out.println("[Warning] The Student has already registered.");
		}
	}
}
