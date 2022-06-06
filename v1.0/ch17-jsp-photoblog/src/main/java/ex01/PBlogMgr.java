package ex01;

import ex02.*;
import ex03.*;
import ex04.*;
import ex05.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PBlogMgr {

	private DBConnectionMgr pool;
	
	// 파일 업로드 기능에 파일 (포토)가 저장되어있는 서버 폴더 위치
	private static final String  SAVEFOLDER = 
			"G:/GoottAcademy/web_Project_0808/ch06_jsp"
			+ "/eunbin/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ch17/photo";
	
	// 요청되는 파일이 한글 인식에 필요한 인코딩으로 셋팅
	private static final String ENCTYPE = "EUC-KR";
	
	// 업로드 파일 크기를 5mb 제한
	private static int MAXSIZE = 5*1024*1024;

	public PBlogMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PMember Login 
	// 회원 로그인 메소드
	public boolean loginPMember(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			
			// id와 pwd 조건에 맞는 id를 검색하는 sql문
			sql = "select id from tblPMember where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			// 조건에 맞는 id와 pwd이면 true 값이고
			// 일치하지 않으면 false 값 리턴
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	// PMember Get
	// 회원 정보를 가져오는 메소드
	public PMemberBean getPMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		PMemberBean bean = new PMemberBean();
		try {
			con = pool.getConnection();
			
			// 매개변수로 받은 id의 조건에 맞는 이름과 프로필 포토명을 가져온 sql문
			sql = "select name, profile from tblPMember where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setId(id);
				bean.setName(rs.getString(1));
				bean.setProfile(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}

	// Random PMember List - 본인을 제외한 5명 회원 정보 리스트를 가져오는 메소드
	public Vector<PMemberBean> listPMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PMemberBean> vlist = new Vector<PMemberBean>();
		try {
			con = pool.getConnection();
			
			// tblPMember에서 매개변수로 받은 id를 제외하고 5개의 랜덤한 회원정보 검색하는 sql문
			sql = "select * from (select id,name,profile from tblPMember where id !=? order by dbms_random.value) where rownum <= 5";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PMemberBean bean = new PMemberBean();
				bean.setId(rs.getString(1));
				bean.setName(rs.getString(2));
				bean.setProfile(rs.getString(3));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// PBlog Insert
	// 포토포스트를 저장하는 메소드
	public void insertPBlog(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MultipartRequest multi = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// 파일 업로드 기능을 위해 MultipartRequest 객체 생성
			// DefaultFileRenamePolicy 객체는 중복된 파일명이 업로드될 경우에 중복을 피하는 기능을 위한 객체
			multi = new MultipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE,
					new DefaultFileRenamePolicy());
			String photo = null;
			if (multi.getFilesystemName("photo") != null) {
				// 04_post.jsp에서 포토 업로드를 위해 file type의 input 태그의 name 값이 photo
				photo = multi.getFilesystemName("photo");
			}
			
			// 04_post.jsp에서 입력받는 값을 저장하는 sql문
			sql = "insert into tblPBlog (num, message,id,pdate,photo) values (tblPBlog_SEQ.nextval, ?,?,sysdate,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("message"));
			pstmt.setString(2, multi.getParameter("id"));
			pstmt.setString(3, photo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// PBlog Delete
	// 포토포스트를 삭제하는 메소드
	public void deletePBlog(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			String photo = getPhoto(num);
			if (photo!=null) {
				File file = new File(SAVEFOLDER + "/" + photo);
				if (file.exists()) UtilMgr.delete(SAVEFOLDER + "/" + photo);
				// 포토포스트를 삭제할 때 업로드된 파일 (포토)를 삭제
			}
			con = pool.getConnection();
			// 조건에 맞는 포토포스트를 삭제하는 sql문
			sql = "delete from tblPBlog where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate()>0) {
				PReplyMgr pMgr = new PReplyMgr();
				// 삭제한 포토포스트와 관련된 댓글도 모두 삭제하는 메소드 호출
				pMgr.deleteAllPReply(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// PBlog Get Photo
	// 포토포스트의 포토 파일명을 가져오는 메소드
	public String getPhoto(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String photo = null;
		try {
			con = pool.getConnection();
			
			// 조건에 맞는 포토 파일명을 검색하는 sql문
			sql = "select photo from tblPBlog where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				photo = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return photo;
	}
	
	// PBlog List
	// 포토포스트 리스트를 가져오는 메소드
	public Vector<PBlogBean> listPBlog(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PBlogBean> vlist = new Vector<PBlogBean>();
		try {
			con = pool.getConnection();
			
			// 조건에 맞는 포토포스트 리스트를 검색하는 sql문
			sql = "select * from tblPBlog where id=? order by num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PBlogBean bean = new PBlogBean();
				bean.setNum(rs.getInt(1));
				bean.setMessage(rs.getString(2));
				bean.setId(rs.getString(3));
				bean.setPdate(rs.getString(4));
				bean.setPhoto(rs.getString(5));
				bean.setHcnt(rs.getInt(6));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// HCnt Up
	// '좋아요'의 카운트가 증가하는 메소드
	public void upHCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// 조건에 맞는 포토포스트 '좋아요' (hCnt)의 카운트가 1씩 증가하는 sql문
			sql = "update tblPBlog set hCnt=hCnt+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
}
