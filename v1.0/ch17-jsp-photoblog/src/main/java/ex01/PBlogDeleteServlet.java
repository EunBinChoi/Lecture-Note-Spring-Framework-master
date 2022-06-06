package ex01;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogDelete")
public class PBlogDeleteServlet extends HttpServlet {
	
	// 03_home.jsp에서 form의 method가 post이기 때문에 서블릿에서 doPost() 메소드 선언
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// 03_home.jsp에서 삭제할 포토포스트의 num 값 받음
		int num = Integer.parseInt(request.getParameter("num"));
		// 요청받은 num 값으로 포토포스트 삭제
		pMgr.deletePBlog(num);
		// 포토포스트 삭제 후에 03_home.jsp 페이지로 이동
		response.sendRedirect("03_home.jsp");
	}
}