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
	
	// 03_home.jsp���� form�� method�� post�̱� ������ �������� doPost() �޼ҵ� ����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// 03_home.jsp���� ������ ��������Ʈ�� num �� ����
		int num = Integer.parseInt(request.getParameter("num"));
		// ��û���� num ������ ��������Ʈ ����
		pMgr.deletePBlog(num);
		// ��������Ʈ ���� �Ŀ� 03_home.jsp �������� �̵�
		response.sendRedirect("03_home.jsp");
	}
}