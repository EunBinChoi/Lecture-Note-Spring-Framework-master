/*
 * 포토포스트의 ‘좋아요‘는 home.jsp or guest.jsp 페이지에서 각각의 포토포스트에 있는 [♡]를 클릭하면 
 * PBlogHCntServlet 서블릿에서 데이터베이스와 연동하여 카운트가 올라감 
 *  * 포토블로그에는 댓글을 달 수 있는 기능이 있음
 * 
 * 댓글을 달고자 하는 포토포스트에 댓글을 입력하고 [게시]을 클릭하면 PReplyPostServlet 서블릿에서 데이터베이스에 저장 처리
 * 입력한 댓글은 home.jsp or guest.jsp에서 댓글 리스트를 보여주고 
 * 
 * 마지막으로 본인이 입력한 댓글은 댓글 끝에 [X]를 클릭하면 PReplyDeleteServlet 서블릿에서 데이터베이스와 연동하여 삭제 처리
 * 
 * */

package ex02;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pReplyPost") // 웹 브라우저에서 사용되는 url 값으로 맵핑함
public class PReplyPostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PReplyMgr rMgr = new PReplyMgr();
		PReplyBean rbean = new PReplyBean();
		rbean.setNum(Integer.parseInt(request.getParameter("num")));
		rbean.setId(request.getParameter("id"));
		rbean.setComments(request.getParameter("comments"));
		rMgr.insertPReply(rbean); // 댓글을 저장하는 메소드 호출
		
		// gid 요청의 유무로 03_home.jsp or 05_guest.jsp로 이동할 지 판단함
		// gid 값이 null이면 03_home.jsp를 호출
		// gid 값이 null이 아니면 05_guest.jsp를 호출
		String gid = request.getParameter("gid");
		
		// gid 요청이 없다면 03_home.jsp로 이동
		if(gid == null) response.sendRedirect("03_home.jsp");
		
		// gid 요청이 있다면 05_guest.jsp로 페이지로 이동함
		// 05_guest.jsp로 이동할 때 조건값으로 다시 gid 값을 넘김
		else response.sendRedirect("05_guest.jsp?gid="+gid);
	}
}