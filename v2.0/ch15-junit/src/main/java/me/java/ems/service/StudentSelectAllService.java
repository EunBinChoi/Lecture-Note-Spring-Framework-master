package me.java.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.java.ems.dao.StudentDao;
import me.java.ems.entity.Student;


@Service
public class StudentSelectAllService {
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
		List<Student> selectAll = studentDao.selectAll();
		return selectAll;
	}
}
