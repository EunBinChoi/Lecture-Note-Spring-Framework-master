package ex05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ex03.DBConnectionMgr;

public class RandomMgr {

	private DBConnectionMgr pool;
	
	// ���÷� ȸ�� id 10���� �迭�� ����
	String id[] = { "a100", "a101", "a102", "a103", "a104", "a105", "a106", "a107", "a108", "a109" };
	
	// ���÷� ����Ǵ� ��� ȸ���� ��й�ȣ ����
	String pwd = "1234";
	
	// ���÷� �ԷµǴ� ȸ�� �̸� 10���� �迭�� ����
	String name[] = { "���缮", "�̱���", "�ϵ���", "������", "���ҹ�", "������", "������", "�缼��", "����ȿ", "�缼��" };

	
	// ������
	public RandomMgr() {
		try {
			// Ŀ�ؼ� Ǯ���� ��ü ����
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ�� 10�� �Է�
	// ������ ����� id, pwd, name ���� 10���� insert�� ������ ���� sample data�� �Է��ϴ� �޼ҵ�
	// ������ ���� ���� profile1.jpg ~ profile10.jpg���� �Էµ�
	public void postPMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert into tblPMember(id,pwd,name,profile)values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			// �迭�� �غ�� id ���� 10���� ���̺� ����Ǳ� ���� for�� ����
			for (int i = 0; i < id.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id[i]);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name[i]);
				
				// ����Ǵ� ȸ�� ������ ������� profile1.jpg �������� �����
				// ȸ�� ������ ���䵵 sample data�� ������
				pstmt.setString(4, "profile" + (i + 1) + ".jpg");
				pstmt.executeUpdate();
			}
			
			// 10�� �Է��� �Ϸ�Ǹ� Console�� ��µǴ� ���� �޽���
			System.out.println("ȸ�� 10�� �Է� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// ȸ�� 1��� 10�� PhotoBlog �Է�
	// �Է��� 10���� id�� �����ͼ� id�� 10���� ��������Ʈ�� �Է��ϴ� �޼ҵ�
	// ������� photo1.jpg ~ photo100.jpg �Էµǰ� sample photo ����� �ҽ��� ���� ����
	public void postPBlog() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			
			// https://zorba91.tistory.com/178
			con = pool.getConnection();
			//sql = "select id from tblPMember limit 0, 10";
			
			// ����� ȸ�� id 10���� �������� sql��
			sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM tblPMember A  WHERE ROWNUM <= 10) WHERE RNUM > 0";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// ��������Ʈ�� �����ϱ� ���� sql��
			sql = "insert into tblPBlog(num,id,message,pdate,photo)values(tblPBlog_SEQ.nextval, ?,?,sysdate,?)";
			int cnt = 1;
			while (rs.next()) {
				String id = rs.getString(1);
				
				// id�� 10���� ��������Ʈ�� �Է��ϱ� ���� for�� ����
				for (int i = 0; i < 10; i++) {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					// message�� �����ϰ� 'a101 1 �޽����Դϴ�'��� ������ �����
					pstmt.setString(2, id + " " + (i + 1) + " �޼����Դϴ�.");
					// ����Ǵ� ������� photo1.jpg���� photo100.jpg�� �����
					// 100���� ����� sample data�� ������
					pstmt.setString(3, "photo" + cnt + ".jpg");
					pstmt.executeUpdate();
					cnt++;
				}
			}
			
			// 100�� ��������Ʈ �Է��� �Ϸ�Ǹ� Console�� ��µǴ� ���� �޽���
			System.out.println("ȸ�� 1��� 10�� PhotoBlog �Է� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// �ڹ� Application ������ ���� main �޼ҵ� ����
	public static void main(String[] args) {
		RandomMgr mgr = new RandomMgr();
		mgr.postPMember(); // 10���� ȸ�� ������ ����Ǵ� �޼ҵ� ȣ��
		mgr.postPBlog(); // 100���� ��������Ʈ�� ����Ǵ� �޼ҵ� ȣ��
	}
}
