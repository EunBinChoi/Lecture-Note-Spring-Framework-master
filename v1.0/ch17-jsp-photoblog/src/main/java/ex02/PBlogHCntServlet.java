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

@WebServlet("/pBlogUpHCnt") // �� ���������� ���Ǵ� url ������ ����
public class PBlogHCntServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// 03_home.jsp �Ǵ� 05_guest.jsp���� ���ƿ並 �ϰ� ���� ��������Ʈ�� num���� ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		// �Ű����� num���� '���ƿ�' ī��Ʈ�� �����ϴ� �޼ҵ� ȣ��
		pMgr.upHCnt(num);
		
		// gid ��û�� ������ 03_home.jsp or 05_guest.jsp�� �̵��� �� �Ǵ���
		String gid = request.getParameter("gid");
		
		// gid ��û�� ���ٸ� 03_home.jsp�� �������� �̵���
		if(gid == null) response.sendRedirect("03_home.jsp");
		// gid ��û�� �ִٸ� 05_guest.jsp�� �������� �̵���
		else response.sendRedirect("05_guest.jsp?gid="+gid);
	}
}