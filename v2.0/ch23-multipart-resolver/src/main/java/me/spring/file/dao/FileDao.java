package me.spring.file.dao;

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

import me.spring.file.beans.FileEntity;
import me.spring.member.beans.MemberEntity;

@Repository
public class FileDao implements IFileDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public FileDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(FileEntity file) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "insert into mp_file values (seq_mp_fno.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, file.getUserName());
				pstmt.setString(2, file.getTitle());
				pstmt.setString(3, file.getContent());
				pstmt.setString(4, file.getOriFileNames());
				pstmt.setString(5, file.getStoredFileNames());
				pstmt.setString(6, file.getfSeperator());
				pstmt.setString(7, file.getfCount());
				pstmt.setString(8, file.getfType());
				pstmt.setString(9, file.getfSize());
			}
		});

	}

	@Override
	public FileEntity select(String fno) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<FileEntity> files = null;
		String SQL = "select * from mp_file where fno=?";
		files = jdbcTemplate.query(SQL, new RowMapper<FileEntity>() {

			@Override
			public FileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				FileEntity file = new FileEntity();
				file.setFno     (rs.getString("FNO"));
				file.setUserName(rs.getString("USERNAME"));
				file.setTitle   (rs.getString("TITLE"));
				file.setContent (rs.getString("CONTENT"));
				file.setOriFileNames   (rs.getString("ORIFILENAMES"));
				file.setStoredFileNames(rs.getString("STOREDFILENAMES"));
				file.setfSeperator     (rs.getString("FSEPERATOR"));
				file.setfCount  (rs.getString("FCOUNT"));
				file.setfType   (rs.getString("FTYPE"));
				file.setfSize   (rs.getString("FSIZE"));

				return file;
			}
		}, fno);

		if (files == null || files.isEmpty()) return null;
		return files.get(0);
	}

	@Override
	public int update(FileEntity file) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub

		String SQL = "update mp_file set userName=?, title=?, content=?, oriFileNames=?, storedFileNames=?, fSeperator=?, fCount=?, fType=?, fSize=? where fno=?";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1,  file.getUserName());
				pstmt.setString(2,  file.getTitle());
				pstmt.setString(3,  file.getContent());
				pstmt.setString(4,  file.getOriFileNames());
				pstmt.setString(5,  file.getStoredFileNames());
				pstmt.setString(6,  file.getfSeperator());
				pstmt.setString(7,  file.getfCount());
				pstmt.setString(8,  file.getfType());
				pstmt.setString(9,  file.getfSize());
				pstmt.setString(10, file.getFno());
			}
		});

	}

	@Override
	public int delete(String fno) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		String SQL = "delete mp_file where fno=?";
		return jdbcTemplate.update(SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, fno);
			}
		});

	}

	@Override
	public List<FileEntity> selectAll() throws SQLException, DataAccessException {
		// TODO Auto-generated method stub

		List<FileEntity> files = null;

		String SQL = "select * from mp_file";
		files = jdbcTemplate.query(SQL, new RowMapper<FileEntity>() {

			@Override
			public FileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				FileEntity file = new FileEntity();
				file.setFno     (rs.getString("FNO"));
				file.setUserName(rs.getString("USERNAME"));
				file.setTitle   (rs.getString("TITLE"));
				file.setContent (rs.getString("CONTENT"));
				file.setOriFileNames   (rs.getString("ORIFILENAMES"));
				file.setStoredFileNames(rs.getString("STOREDFILENAMES"));
				file.setfSeperator     (rs.getString("FSEPERATOR"));
				file.setfCount  (rs.getString("FCOUNT"));
				file.setfType   (rs.getString("FTYPE"));
				file.setfSize   (rs.getString("FSIZE"));

				return file;
			}
		});

		if (files == null || files.isEmpty()) return null;
		return files;
	}

}
