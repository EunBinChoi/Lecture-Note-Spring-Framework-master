package ex05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ex03.DBConnectionMgr;

public class RandomMgr {

	private DBConnectionMgr pool;
	
	// 샘플로 회원 id 10개를 배열로 선언
	String id[] = { "a100", "a101", "a102", "a103", "a104", "a105", "a106", "a107", "a108", "a109" };
	
	// 샘플로 저장되는 모든 회원의 비밀번호 선언
	String pwd = "1234";
	
	// 샘플로 입력되는 회원 이름 10개를 배열로 선언
	String name[] = { "유재석", "이광수", "하동훈", "김종국", "전소민", "지석진", "강개리", "양세찬", "송지효", "양세형" };

	
	// 생성자
	public RandomMgr() {
		try {
			// 커넥션 풀에서 객체 얻어옴
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 10명 입력
	// 위에서 선언된 id, pwd, name 값을 10번의 insert문 실행을 통해 sample data를 입력하는 메소드
	// 프로필 포토 명은 profile1.jpg ~ profile10.jpg으로 입력됨
	public void postPMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert into tblPMember(id,pwd,name,profile)values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			// 배열로 준비된 id 값을 10개가 테이블에 저장되기 위해 for문 선언
			for (int i = 0; i < id.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id[i]);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name[i]);
				
				// 저장되는 회원 프로필 포토명이 profile1.jpg 형식으로 저장됨
				// 회원 프로필 포토도 sample data로 제공됨
				pstmt.setString(4, "profile" + (i + 1) + ".jpg");
				pstmt.executeUpdate();
			}
			
			// 10개 입력이 완료되면 Console에 출력되는 성공 메시지
			System.out.println("회원 10개 입력 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// 회원 1명당 10개 PhotoBlog 입력
	// 입력한 10개의 id를 가져와서 id당 10개의 포토포스트를 입력하는 메소드
	// 포토명은 photo1.jpg ~ photo100.jpg 입력되고 sample photo 포토는 소스랑 같이 제공
	public void postPBlog() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			
			// https://zorba91.tistory.com/178
			con = pool.getConnection();
			//sql = "select id from tblPMember limit 0, 10";
			
			// 저장된 회원 id 10개를 가져오는 sql문
			sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM tblPMember A  WHERE ROWNUM <= 10) WHERE RNUM > 0";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 포토포스트를 저장하기 위한 sql문
			sql = "insert into tblPBlog(num,id,message,pdate,photo)values(tblPBlog_SEQ.nextval, ?,?,sysdate,?)";
			int cnt = 1;
			while (rs.next()) {
				String id = rs.getString(1);
				
				// id당 10개의 포토포스트를 입력하기 위해 for문 선언
				for (int i = 0; i < 10; i++) {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					// message는 간단하게 'a101 1 메시지입니다'라는 식으로 저장됨
					pstmt.setString(2, id + " " + (i + 1) + " 메세지입니다.");
					// 저장되는 포토명은 photo1.jpg에서 photo100.jpg로 저장됨
					// 100개의 포토는 sample data로 제공됨
					pstmt.setString(3, "photo" + cnt + ".jpg");
					pstmt.executeUpdate();
					cnt++;
				}
			}
			
			// 100개 포토포스트 입력이 완료되면 Console에 출력되는 성공 메시지
			System.out.println("회원 1명당 10개 PhotoBlog 입력 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 자바 Application 실행을 위한 main 메소드 선언
	public static void main(String[] args) {
		RandomMgr mgr = new RandomMgr();
		mgr.postPMember(); // 10명의 회원 정보가 저장되는 메소드 호출
		mgr.postPBlog(); // 100개의 포토포스트가 저장되는 메소드 호출
	}
}
