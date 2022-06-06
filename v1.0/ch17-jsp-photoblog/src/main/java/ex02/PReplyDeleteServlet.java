package ex02;

import ex03.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pReplyDelete") // 웹 브라우저에 사용되는 url 값으로 매핑
public class PReplyDeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PReplyMgr rMgr = new PReplyMgr();
		
		// 03_home.jsp or 05_guest.jsp에서 삭제할 댓글의 rnum 값을 받음
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		// rnum 값을 매개변수로 댓글 삭제 메소드를 호출
		rMgr.deletePReply(rnum);
		
		// gid 요청 유무로 03_home.jsp or 05_guest.jsp로 이동할 지 판단
		// gid 값이 null이면 03_home.jsp에서 호출함
		// gid 값이 null이 아니면 05_guest.jsp에서 호출함
		String gid = request.getParameter("gid");
		if(gid == null) response.sendRedirect("03_home.jsp"); // 03_home.jsp 페이지로 이동
		else response.sendRedirect("05_guest.jsp?gid="+gid); 
		// gid 요청이 있다면 05_guest.jsp 페이지로 이동함
		// 05_guest.jsp로 이동할 때 조건 값으로 다시 gid 값을 넘김
	}
}