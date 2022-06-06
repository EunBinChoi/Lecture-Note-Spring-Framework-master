package me.spring.ajaxse.dao;

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

import me.spring.ajaxse.beans.UsersEntity;

@Repository
public class UsersDao implements IUsersDao {
	private final JdbcTemplate jdbcTemplate;


	@Autowired
	public UsersDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<UsersEntity> select(String keyword) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<UsersEntity> users = null;
		String SQL = "select * from users where userName like ?";
		users = jdbcTemplate.query(SQL, new RowMapper<UsersEntity>() {

			@Override
			public UsersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				UsersEntity user = new UsersEntity();
				user.setUserName  (rs.getString("userName"));
				user.setUserAge   (rs.getInt   ("userAge"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail (rs.getString("userEmail"));
				return user;
			}

		}, "%" + keyword + "%"); // wildcard
		if(users.isEmpty()) return null;
		return users;
	}

	@Override
	public int insert(UsersEntity user) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "insert into users values (?, ?, ?, ?)";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserName());
				ps.setInt   (2, user.getUserAge());
				ps.setString(3, user.getUserGender());
				ps.setString(4, user.getUserEmail());

			}

		});
	}

}
