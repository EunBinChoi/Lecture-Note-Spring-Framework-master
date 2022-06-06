package me.spring.file.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import me.spring.file.beans.FileDTO;

public interface IFileService {
	public boolean insert(FileDTO file) throws SQLException, DataAccessException ;
	public FileDTO select(String fno) throws SQLException, DataAccessException;
	public boolean update(FileDTO file) throws SQLException, DataAccessException;
	public boolean delete(String fno) throws SQLException, DataAccessException;
	public List<FileDTO> selectAll() throws SQLException, DataAccessException;

}
