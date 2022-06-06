package ex02;

import ex01.*;
import ex03.*;
import ex04.*;
import ex05.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogUpHCnt") // 웹 브라우저에서 사용되는 url 값으로 매핑
public class PBlogHCntServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// 03_home.jsp 또는 05_guest.jsp에서 좋아요를 하고 싶은 포토포스트의 num값을 받음
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 매개변수 num으로 '좋아요' 카운트를 증가하는 메소드 호출
		pMgr.upHCnt(num);
		
		// gid 요청을 유무로 03_home.jsp or 05_guest.jsp로 이동할 지 판단함
		String gid = request.getParameter("gid");
		
		// gid 요청이 없다면 03_home.jsp로 페이지를 이동함
		if(gid == null) response.sendRedirect("03_home.jsp");
		// gid 요청이 있다면 05_guest.jsp로 페이지로 이동함
		else response.sendRedirect("05_guest.jsp?gid="+gid);
	}
}