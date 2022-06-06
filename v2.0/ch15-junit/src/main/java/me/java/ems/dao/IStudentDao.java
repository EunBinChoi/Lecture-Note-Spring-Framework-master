package me.java.ems.dao;

import java.util.List;

import me.java.ems.entity.Student;



public interface IStudentDao {
	public int insert(Student student);
	public Student select(String sNum);
	public int update(Student student);
	public int delete(Student student);
	public List<Student> selectAll();
}
