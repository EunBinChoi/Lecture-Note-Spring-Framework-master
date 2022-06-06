package me.spring.login.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import me.spring.login.beans.RegisterEntity;

public interface IRegisterDao {
	public int insert(RegisterEntity register) throws SQLException, DataAccessException;
	public RegisterEntity select(String id) throws SQLException, DataAccessException;
	public int update(RegisterEntity register) throws SQLException, DataAccessException;
	public int delete(String id) throws SQLException, DataAccessException;
	public List<RegisterEntity> selectAll() throws SQLException, DataAccessException;
}
