
package ex01;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogLogin") // 서블릿 맵핑 이름 선언
public class PBlogLoginServlet extends HttpServlet {
	
	// 04_post.jsp에서 form의 method가 post이기 떄문에 서블릿에서 doPost 메소드 선언
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR"); // 01_login.jsp에서 요청받은 문자열의 인코딩 한글로 선언
		PBlogMgr pMgr = new PBlogMgr();
		
		// 01_login.jsp에서 요청한 id와 pwd 값을 받음
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 로그인 실패 시 이동하게 되는 페이지
		String url = "01_login.jsp";
		
		// loginPMember 메소드는 일치하는 id와 pwd이면 true이고
		// 일치하지 않으면 false으로 리턴하는 메소드
		if(pMgr.loginPMember(id, pwd)) {
			request.getSession().setAttribute("idKey", id);
			// request 객체에서 session 객체를 리턴받고 session에 idKey라는 name이라고 로그인에서 입력한 id값 저장
			url = "03_home.jsp";
			// 로그인 성공시 이동하게 되는 페이지
		}else {
			request.getSession().setAttribute("msg", "아이디와 비밀번호가 일치하지 않습니다.");
			// request 객체에서 session 객체를 리턴받고 session에 msg라는 name 값으로 로그인 실패 메시지 저장
		}
		response.sendRedirect(url);
		// 로그인 결과에 따라 url 변수에 적용된 페이지로 이동
	}
}