package ex01;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogPost") // 서블릿 맵핑 이름 선언
public class PBlogPostServlet extends HttpServlet { // 서블릿 상속 클래스

	// 04_post.jsp에서 form의 method가 post이기 때문에
	// 서블릿의 doPost 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 04_post.jsp에서 입력받는 값에 대한 인코딩 설정
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// 웹 서버 (Tomcat)에 포토 저장도 하고 테이블 저장하기 위한 메소드
		pMgr.insertPBlog(request);
		
		// 포토포스트 저장 후에 03_home.jsp 페이지로 이동
		response.sendRedirect("03_home.jsp");
	}
}