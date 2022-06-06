package me.spring.member.dao;

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

import me.spring.member.beans.MemberEntity;

@Repository
public class MemberDao implements IMemberDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(MemberEntity member) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "insert into mp_member values (?, ?, ?, ?, ?, null, sysdate)";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, member.getmId());
				pstmt.setString(2, member.getmPwd());
				pstmt.setString(3, member.getmName());
				pstmt.setString(4, member.getmEmail());
				pstmt.setString(5, member.getmPhone());
			}
		});

	}

	@Override
	public MemberEntity select(String id) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<MemberEntity> members = null;
		String SQL = "select * from mp_member where mId=?";
		members = jdbcTemplate.query(SQL, new RowMapper<MemberEntity>() {

			@Override
			public MemberEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MemberEntity member = new MemberEntity();
				member.setmId(rs.getString("MID"));
				member.setmPwd(rs.getString("MPWD"));
				member.setmName(rs.getString("MNAME"));
				member.setmEmail(rs.getString("MEMAIL"));
				member.setmPhone(rs.getString("MPHONE"));
				member.setmUpdate(rs.getString("MUPDATE"));
				member.setmRegdate(rs.getString("MREGDATE"));

				return member;
			}
		}, id);
		if (members == null || members.isEmpty()) return null;
		return members.get(0);

	}

	@Override
	public int update(MemberEntity member) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "update mp_member set mPwd=?, mName=?, mEmail=?, mPhone=?, mUpdate=sysdate where mId=?";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, member.getmPwd());
				pstmt.setString(2, member.getmName());
				pstmt.setString(3, member.getmEmail());
				pstmt.setString(4, member.getmPhone());
				pstmt.setString(5, member.getmId());
			}
		});
	}

	@Override
	public int delete(String id) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "delete mp_member where mId=?";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, id);
			}
		});
	}

	@Override
	public List<MemberEntity> selectAll() throws SQLException, DataAccessException {
		// TODO Auto-generated method stub

		List<MemberEntity> members = null;
		String SQL = "select * from mp_member";
		members = jdbcTemplate.query(SQL, new RowMapper<MemberEntity>() {

			@Override
			public MemberEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MemberEntity member = new MemberEntity();
				member.setmId(rs.getString("MID"));
				member.setmPwd(rs.getString("MPWD"));
				member.setmName(rs.getString("MNAME"));
				member.setmEmail(rs.getString("MEMAIL"));
				member.setmPhone(rs.getString("MPHONE"));
				member.setmUpdate(rs.getString("MUPDATE"));
				member.setmRegdate(rs.getString("MREGDATE"));
				return member;
			}
		});

		if (members == null || members.isEmpty()) return null;
		return members;
	}

}
