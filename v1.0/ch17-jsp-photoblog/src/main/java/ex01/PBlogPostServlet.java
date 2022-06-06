package ex01;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogPost") // ���� ���� �̸� ����
public class PBlogPostServlet extends HttpServlet { // ���� ��� Ŭ����

	// 04_post.jsp���� form�� method�� post�̱� ������
	// ������ doPost ȣ��
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 04_post.jsp���� �Է¹޴� ���� ���� ���ڵ� ����
		request.setCharacterEncoding("EUC-KR");
		PBlogMgr pMgr = new PBlogMgr();
		
		// �� ���� (Tomcat)�� ���� ���嵵 �ϰ� ���̺� �����ϱ� ���� �޼ҵ�
		pMgr.insertPBlog(request);
		
		// ��������Ʈ ���� �Ŀ� 03_home.jsp �������� �̵�
		response.sendRedirect("03_home.jsp");
	}
}