package me.java.ems.dao;

import java.util.Map;

import me.java.ems.beans.Student;

public interface IStudentDao {
	public void insert(Student student);
	public Student select(String sNum);
	public void update(Student student);
	public void delete(String sNum);
	public Map<String, Student> selectAll();
}
