package me.spring.file.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import me.spring.file.beans.FileEntity;

public interface IFileDao {
	public int insert(FileEntity file) throws SQLException, DataAccessException ;
	public FileEntity select(String fno) throws SQLException, DataAccessException;
	public int update(FileEntity file) throws SQLException, DataAccessException;
	public int delete(String fno) throws SQLException, DataAccessException;
	public List<FileEntity> selectAll() throws SQLException, DataAccessException;
}
