package me.spring.member.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import me.spring.member.beans.MemberEntity;

public interface IMemberDao {
	public int insert(MemberEntity member) throws SQLException, DataAccessException;
	public MemberEntity select(String id) throws SQLException, DataAccessException;
	public int update(MemberEntity member) throws SQLException, DataAccessException;
	public int delete(String id) throws SQLException, DataAccessException;
	public List<MemberEntity> selectAll() throws SQLException, DataAccessException;
}
