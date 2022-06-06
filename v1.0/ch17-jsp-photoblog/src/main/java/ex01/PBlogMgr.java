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
	
	// ���� ���ε� ��ɿ� ���� (����)�� ����Ǿ��ִ� ���� ���� ��ġ
	private static final String  SAVEFOLDER = 
			"G:/GoottAcademy/web_Project_0808/ch06_jsp"
			+ "/eunbin/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ch17/photo";
	
	// ��û�Ǵ� ������ �ѱ� �νĿ� �ʿ��� ���ڵ����� ����
	private static final String ENCTYPE = "EUC-KR";
	
	// ���ε� ���� ũ�⸦ 5mb ����
	private static int MAXSIZE = 5*1024*1024;

	public PBlogMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PMember Login 
	// ȸ�� �α��� �޼ҵ�
	public boolean loginPMember(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			
			// id�� pwd ���ǿ� �´� id�� �˻��ϴ� sql��
			sql = "select id from tblPMember where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			// ���ǿ� �´� id�� pwd�̸� true ���̰�
			// ��ġ���� ������ false �� ����
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	// PMember Get
	// ȸ�� ������ �������� �޼ҵ�
	public PMemberBean getPMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		PMemberBean bean = new PMemberBean();
		try {
			con = pool.getConnection();
			
			// �Ű������� ���� id�� ���ǿ� �´� �̸��� ������ ������� ������ sql��
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

	// Random PMember List - ������ ������ 5�� ȸ�� ���� ����Ʈ�� �������� �޼ҵ�
	public Vector<PMemberBean> listPMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PMemberBean> vlist = new Vector<PMemberBean>();
		try {
			con = pool.getConnection();
			
			// tblPMember���� �Ű������� ���� id�� �����ϰ� 5���� ������ ȸ������ �˻��ϴ� sql��
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
	// ��������Ʈ�� �����ϴ� �޼ҵ�
	public void insertPBlog(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MultipartRequest multi = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// ���� ���ε� ����� ���� MultipartRequest ��ü ����
			// DefaultFileRenamePolicy ��ü�� �ߺ��� ���ϸ��� ���ε�� ��쿡 �ߺ��� ���ϴ� ����� ���� ��ü
			multi = new MultipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE,
					new DefaultFileRenamePolicy());
			String photo = null;
			if (multi.getFilesystemName("photo") != null) {
				// 04_post.jsp���� ���� ���ε带 ���� file type�� input �±��� name ���� photo
				photo = multi.getFilesystemName("photo");
			}
			
			// 04_post.jsp���� �Է¹޴� ���� �����ϴ� sql��
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
	// ��������Ʈ�� �����ϴ� �޼ҵ�
	public void deletePBlog(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			String photo = getPhoto(num);
			if (photo!=null) {
				File file = new File(SAVEFOLDER + "/" + photo);
				if (file.exists()) UtilMgr.delete(SAVEFOLDER + "/" + photo);
				// ��������Ʈ�� ������ �� ���ε�� ���� (����)�� ����
			}
			con = pool.getConnection();
			// ���ǿ� �´� ��������Ʈ�� �����ϴ� sql��
			sql = "delete from tblPBlog where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate()>0) {
				PReplyMgr pMgr = new PReplyMgr();
				// ������ ��������Ʈ�� ���õ� ��۵� ��� �����ϴ� �޼ҵ� ȣ��
				pMgr.deleteAllPReply(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// PBlog Get Photo
	// ��������Ʈ�� ���� ���ϸ��� �������� �޼ҵ�
	public String getPhoto(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String photo = null;
		try {
			con = pool.getConnection();
			
			// ���ǿ� �´� ���� ���ϸ��� �˻��ϴ� sql��
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
	// ��������Ʈ ����Ʈ�� �������� �޼ҵ�
	public Vector<PBlogBean> listPBlog(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PBlogBean> vlist = new Vector<PBlogBean>();
		try {
			con = pool.getConnection();
			
			// ���ǿ� �´� ��������Ʈ ����Ʈ�� �˻��ϴ� sql��
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
	// '���ƿ�'�� ī��Ʈ�� �����ϴ� �޼ҵ�
	public void upHCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			
			// ���ǿ� �´� ��������Ʈ '���ƿ�' (hCnt)�� ī��Ʈ�� 1�� �����ϴ� sql��
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
