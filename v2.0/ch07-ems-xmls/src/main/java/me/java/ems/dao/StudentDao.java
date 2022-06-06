package me.java.ems.dao;

import java.util.HashMap;
import java.util.Map;

import me.java.ems.beans.Student;

public class StudentDao implements IStudentDao {
	private Map<String, Student> studentDB = new HashMap<>();
	
	public StudentDao() {
		super();
		System.out.println("StudentDao()");
	}

	public StudentDao(Map<String, Student> studentDB) {
		super();
		this.studentDB = studentDB;
		System.out.println("StudentDao(Map<String, Student> studentDB)");
	}

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		studentDB.put(student.getsNum(), student);

	}

	@Override
	public Student select(String sNum) {
		// TODO Auto-generated method stub
		return studentDB.get(sNum);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		studentDB.put(student.getsNum(), student);

	}

	@Override
	public void delete(String sNum) {
		// TODO Auto-generated method stub
		studentDB.remove(sNum);
	}

	@Override
	public Map<String, Student> selectAll() {
		// TODO Auto-generated method stub
		return studentDB;
	}

}
