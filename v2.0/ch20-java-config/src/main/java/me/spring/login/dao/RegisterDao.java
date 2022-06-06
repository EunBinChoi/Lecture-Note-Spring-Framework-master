package me.spring.login.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import me.spring.login.beans.RegisterEntity;

@Repository
public class RegisterDao implements IRegisterDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RegisterDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(RegisterEntity register) throws SQLException, DataAccessException {
		//try {
			// TODO Auto-generated method stub
			String SQL = "insert into register values (?, ?, ?, ?, ?)";
			return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					pstmt.setString(1, register.getId());
					pstmt.setString(2, register.getPwd());
					pstmt.setString(3, register.getName());
					pstmt.setString(4, register.getEmail());
					pstmt.setString(5, register.getPhone());
				}
			});
		//} catch (Exception e) {
		///	return -1;
		//}

	}

	@Override
	public RegisterEntity select(String id) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<RegisterEntity> registers = null;
		//try {
			String SQL = "select * from register where id=?";
			registers = jdbcTemplate.query(SQL, new RowMapper<RegisterEntity>() {

				@Override
				public RegisterEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					RegisterEntity register = new RegisterEntity();
					register.setId   (rs.getString("Id"));
					register.setPwd  (rs.getString("Pwd"));
					register.setName (rs.getString("Name"));
					register.setEmail(rs.getString("Email"));
					register.setPhone(rs.getString("Phone"));

					return register;
				}
			}, id);
		//} catch (Exception e) {
		//	return null;
		//}
		if (registers == null || registers.isEmpty()) return null;
		return registers.get(0);

	}

	@Override
	public int update(RegisterEntity register) throws SQLException, DataAccessException {
		//try {
			// TODO Auto-generated method stub
			String SQL = "update register set pwd=?, name=?, email=?, phone=? where id=?";
			return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					pstmt.setString(1, register.getPwd());
					pstmt.setString(2, register.getName());
					pstmt.setString(3, register.getEmail());
					pstmt.setString(4, register.getPhone());
					pstmt.setString(5, register.getId());
				}
			});
		//} catch (Exception e) {
		//	return -1;
		//}
	}

	@Override
	public int delete(String id) throws SQLException, DataAccessException {
		//try {
			// TODO Auto-generated method stub
			String SQL = "delete register where id=?";
			return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					pstmt.setString(1, id);
				}
			});
		//} catch (Exception e) {
		//	return -1;
		//}

	}

	@Override
	public List<RegisterEntity> selectAll() throws SQLException, DataAccessException {
		// TODO Auto-generated method stub

		List<RegisterEntity> registers = null;
		//try {
			String SQL = "select * from register";
			registers = jdbcTemplate.query(SQL, new RowMapper<RegisterEntity>() {

				@Override
				public RegisterEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					RegisterEntity register = new RegisterEntity();
					register.setId   (rs.getString("Id"));
					register.setPwd  (rs.getString("Pwd"));
					register.setName (rs.getString("Name"));
					register.setEmail(rs.getString("Email"));
					register.setPhone(rs.getString("Phone"));
					return register;
				}
			});
		//} catch (Exception e) {
		//	return null;
		//}
		if (registers == null || registers.isEmpty()) return null;
		return registers;
	}

}
