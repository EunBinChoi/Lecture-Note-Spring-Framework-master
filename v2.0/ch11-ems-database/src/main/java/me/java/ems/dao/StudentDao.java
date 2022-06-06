package me.java.ems.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import me.java.ems.beans.Student;

@Repository
public class StudentDao implements IStudentDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;


	public StudentDao() {
		super();
		System.out.println("StudentDao()");
	}

	public StudentDao(String driver, String url, String userid, String userpw, Connection conn, PreparedStatement pstmt,
			ResultSet rs) {
		super();
		this.driver = driver;
		this.url = url;
		this.userid = userid;
		this.userpw = userpw;
		this.conn = conn;
		this.pstmt = pstmt;
		this.rs = rs;
		System.out.println("StudentDao(String driver, String url, String userid, String userpw, Connection conn, PreparedStatement pstmt, ResultSet rs)");
	}

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String SQL = "insert into student values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, student.getsNum());
			pstmt.setString(2, student.getsId());
			pstmt.setString(3, student.getsPw());
			pstmt.setString(4, student.getsName());
			pstmt.setInt   (5, student.getsAge());
			pstmt.setString(6, student.getsGender());
			pstmt.setString(7, student.getsMajor());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Student select(String sNum) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String SQL = "select * from student where sNum=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				student.setsNum   (rs.getString("sNum"));
				student.setsId    (rs.getString("sId"));
				student.setsPw    (rs.getString("sPw"));
				student.setsName  (rs.getString("sName"));
				student.setsAge   (rs.getInt("sAge"));
				student.setsGender(rs.getString("sGender"));
				student.setsMajor (rs.getString("sMajor"));
				return student;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void update(String attribute, String revisedData, String sNum) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String SQL = "update student set " + attribute + "=? where sNum=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, revisedData);
			pstmt.setString(2, sNum);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
			String SQL = "delete student where sId=? and sPw=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, student.getsId());
			pstmt.setString(2, student.getsPw());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);

			String SQL = "select * from student";
			List<Student> students = new Vector<>();

			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				student.setsNum   (rs.getString("sNum"));
				student.setsId    (rs.getString("sId"));
				student.setsPw    (rs.getString("sPw"));
				student.setsName  (rs.getString("sName"));
				student.setsAge   (rs.getInt("sAge"));
				student.setsGender(rs.getString("sGender"));
				student.setsMajor (rs.getString("sMajor"));
				students.add(student);
			}
			return students;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
