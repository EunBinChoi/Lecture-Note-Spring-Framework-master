package ex02;

import ex03.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pReplyDelete") // �� �������� ���Ǵ� url ������ ����
public class PReplyDeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PReplyMgr rMgr = new PReplyMgr();
		
		// 03_home.jsp or 05_guest.jsp���� ������ ����� rnum ���� ����
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		// rnum ���� �Ű������� ��� ���� �޼ҵ带 ȣ��
		rMgr.deletePReply(rnum);
		
		// gid ��û ������ 03_home.jsp or 05_guest.jsp�� �̵��� �� �Ǵ�
		// gid ���� null�̸� 03_home.jsp���� ȣ����
		// gid ���� null�� �ƴϸ� 05_guest.jsp���� ȣ����
		String gid = request.getParameter("gid");
		if(gid == null) response.sendRedirect("03_home.jsp"); // 03_home.jsp �������� �̵�
		else response.sendRedirect("05_guest.jsp?gid="+gid); 
		// gid ��û�� �ִٸ� 05_guest.jsp �������� �̵���
		// 05_guest.jsp�� �̵��� �� ���� ������ �ٽ� gid ���� �ѱ�
	}
}