package me.java.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.java.ems.dao.StudentDao;

@Service
public class StudentModifyService {
	//@Autowired @Qualifier("dao")
	//@Resource @Qualifier("dao")
	//@Inject @Named("dao")
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
	public void modify(String attribute, String revisedData, String sNum) {
		if(studentDao.select(sNum) != null) {
			studentDao.update(attribute, revisedData, sNum);
		} else {
			System.out.println("[Warning] The Student information is not available.");
		}
	}
}
