package me.spring.ajaxse.service;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import me.spring.ajaxse.beans.UsersDTO;

public interface IUsersService {
	public String select(String keyword) throws SQLException, DataAccessException;
	public int insert(UsersDTO user) throws SQLException, DataAccessException;
}
