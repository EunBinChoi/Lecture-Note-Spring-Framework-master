package me.spring.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;

import me.spring.member.beans.MemberDTO;
import me.spring.member.beans.MemberEntity;
import me.spring.member.beans.MemberParser;

public interface IMemberService {
	public boolean signup(MemberDTO member) throws SQLException, DataAccessException;
	public MemberDTO select(String id) throws SQLException, DataAccessException;
	public boolean update(MemberDTO member) throws SQLException, DataAccessException;
	public boolean delete(String id) throws SQLException, DataAccessException;
	public List<MemberDTO> selectAll() throws SQLException, DataAccessException;
}
