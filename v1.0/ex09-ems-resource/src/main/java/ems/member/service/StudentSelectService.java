package ems.member.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ems.member.Student;
import ems.member.dao.StudentDao;

public class StudentSelectService {
	@Resource
	@Qualifier("dao")
	private StudentDao studentDao;
	
	
	/*@Resource는 생성자에 올 수가 없음
	 * 디폴트 생성자 필요 (**)
	 * why? 먼저 빈 생성자를 통해 객체를 생성하고
	 * 이후에 의존 주입*/
	public StudentSelectService() {}
	
	public StudentSelectService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	
	public Student select(String sNum) {
		if(verify(sNum)) {
			return studentDao.select(sNum);
		} else {
			System.out.println("Student information is not available.");
		}
		
		return null;
	}

	public boolean verify(String sNum){
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
}
