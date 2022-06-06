package me.java.ems.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import me.java.ems.entity.Student;

@Repository
public class StudentDao implements IStudentDao {
	/*private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;*/

	@Autowired
	JdbcTemplate jdbcTemplate;
	/* jdbcTemplate안에 dataSource (driver, url, id, pwd) 라는 객체를 가지고 있음
	 * JDBC core 패키지에서 제공하는 클래스
	 * DB에 연결할 수 있는 리소스 생성과 해지에서 생길 수 있는 Exception을 처리
	 *
	 * jdbcTemplate
	 * SQLException, ClassNotFoundException .....
	 * => DataAccessException라는 이름으로 위의 예외들을 통합관리
	 * => @Repository는 DataAccessException handling하기 때문에 직접 처리할 필요 없음
	 * */

	public StudentDao() {
		super();
		System.out.println("StudentDao()");
	}

	public StudentDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		System.out.println("StudentDao(JdbcTemplate jdbcTemplate)");
	}

	@Override
	public int insert(Student student) {
		// TODO Auto-generated method stub
		String SQL = "insert into student values (?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, student.getsNum());
				pstmt.setString(2, student.getsId());
				pstmt.setString(3, student.getsPw());
				pstmt.setString(4, student.getsName());
				pstmt.setInt   (5, student.getsAge());
				pstmt.setString(6, student.getsGender());
				pstmt.setString(7, student.getsMajor());
			}
		});
	}

	@Override
	public Student select(String sNum) {
		// TODO Auto-generated method stub
		List<Student> students = null;
		String SQL = "select * from student where sNum=?";
		students = jdbcTemplate.query(SQL, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Student student = new Student();
				student.setsNum   (rs.getString("sNum"));
				student.setsId    (rs.getString("sId"));
				student.setsPw    (rs.getString("sPw"));
				student.setsName  (rs.getString("sName"));
				student.setsAge   (rs.getInt   ("sAge"));
				student.setsGender(rs.getString("sGender"));
				student.setsMajor (rs.getString("sMajor"));
				return student;
			}

		}, sNum);

		if(students.isEmpty()) return null;
		return students.get(0);
	}

	@Override
	public int update(Student student) {
		// TODO Auto-generated method stub
		String SQL = "update student set sId=?, sPw=?, sName=?, sAge=?, sGender=?, sMajor=? where sNum=?";

		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, student.getsId());
				pstmt.setString(2, student.getsPw());
				pstmt.setString(3, student.getsName());
				pstmt.setInt   (4, student.getsAge());
				pstmt.setString(5, student.getsGender());
				pstmt.setString(6, student.getsMajor());
				pstmt.setString(7, student.getsNum());
			}
		});

	}

	@Override
	public int delete(Student student) {
		// TODO Auto-generated method stub
		String SQL = "delete student where sId=? and sPw=?";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, student.getsId());
				pstmt.setString(2, student.getsPw());
			}
		});
	}

	@Override
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		List<Student> students = null;
		String SQL = "select * from student";
		students = jdbcTemplate.query(SQL, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Student student = new Student();
				student.setsNum   (rs.getString("sNum"));
				student.setsId    (rs.getString("sId"));
				student.setsPw    (rs.getString("sPw"));
				student.setsName  (rs.getString("sName"));
				student.setsAge   (rs.getInt   ("sAge"));
				student.setsGender(rs.getString("sGender"));
				student.setsMajor (rs.getString("sMajor"));
				return student;
			}
		});
		if(students.isEmpty()) return null;
		return students;
	}


}
