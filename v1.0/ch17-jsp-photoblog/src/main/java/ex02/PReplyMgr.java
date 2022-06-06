package ex02;

import ex03.*;
import ex04.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class PReplyMgr {

	private DBConnectionMgr pool;
	
	public PReplyMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// PReply Insert
	// 댓글을 입력하는 메소드
	public void insertPReply(PReplyBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// 03_home.jsp 및 05_guest.jsp에서 요청한 댓글을 저장하는 sql문
			sql = "insert into tblPReply (rnum, num, id, rdate, comments) values (tblPReply_SEQ.nextval, ?,?, sysdate, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getId());
			pstmt.setString(3, bean.getComments());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// PReply Delete
	// 댓글을 삭제하는 메소드
	public void deletePReply(int rnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// 03_home.jsp 및 05_guest.jsp에서 요청한 댓글을 삭제하는 sql문
			sql = "delete from tblPReply where rnum=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	// PReply All Delete
	// 포토포스트의 관련된 댓글을 모두 삭제하는 메소드
	public void deleteAllPReply(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// PBlogMgr.java에서 포토포스트 삭제 시 관련된 모든 댓글 삭제하는 sql문
			sql = "delete from tblPReply where num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	// PReply List
	// 댓글 메소드
	public Vector<PReplyBean> listPReply(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PReplyBean> vlist = new Vector<PReplyBean>();
		try {
			con = pool.getConnection();
			
			// 조건에 맞는 댓글 리스트를 가져오는 sql문
			sql = "select * from tblPReply where num=? order by rnum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PReplyBean bean = new PReplyBean();
				bean.setRnum(rs.getInt(1));
				bean.setNum(rs.getInt(2));
				bean.setId(rs.getString(3));
				bean.setRdate(rs.getString(4));
				bean.setComments(rs.getString(5));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
}