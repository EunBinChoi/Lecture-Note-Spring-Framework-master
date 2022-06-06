package me.spring.ajaxse.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import me.spring.ajaxse.beans.UsersEntity;

public interface IUsersDao {
	public List<UsersEntity> select(String keyword) throws SQLException, DataAccessException;
	// select * from users
	// select * from users where userName = ? (특정 키워드를 포함하는 경우)
	public int insert(UsersEntity user) throws SQLException, DataAccessException;
}
