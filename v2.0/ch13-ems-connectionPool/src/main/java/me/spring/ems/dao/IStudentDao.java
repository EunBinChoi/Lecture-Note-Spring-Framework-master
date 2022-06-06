package me.spring.ems.dao;

import java.util.List;

import me.spring.ems.entity.Student;

public interface IStudentDao {
	public void insert(Student student);
	public Student select(String sNum);
	public void update(String attribute, String revisedData, String num);
	public void delete(Student student);
	public List<Student> selectAll();
}
