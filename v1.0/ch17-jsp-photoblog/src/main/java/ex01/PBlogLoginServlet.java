
package ex01;

import ex03.*;
import ex04.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pBlogLogin") // ���� ���� �̸� ����
public class PBlogLoginServlet extends HttpServlet {
	
	// 04_post.jsp���� form�� method�� post�̱� ������ �������� doPost �޼ҵ� ����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR"); // 01_login.jsp���� ��û���� ���ڿ��� ���ڵ� �ѱ۷� ����
		PBlogMgr pMgr = new PBlogMgr();
		
		// 01_login.jsp���� ��û�� id�� pwd ���� ����
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// �α��� ���� �� �̵��ϰ� �Ǵ� ������
		String url = "01_login.jsp";
		
		// loginPMember �޼ҵ�� ��ġ�ϴ� id�� pwd�̸� true�̰�
		// ��ġ���� ������ false���� �����ϴ� �޼ҵ�
		if(pMgr.loginPMember(id, pwd)) {
			request.getSession().setAttribute("idKey", id);
			// request ��ü���� session ��ü�� ���Ϲް� session�� idKey��� name�̶�� �α��ο��� �Է��� id�� ����
			url = "03_home.jsp";
			// �α��� ������ �̵��ϰ� �Ǵ� ������
		}else {
			request.getSession().setAttribute("msg", "���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			// request ��ü���� session ��ü�� ���Ϲް� session�� msg��� name ������ �α��� ���� �޽��� ����
		}
		response.sendRedirect(url);
		// �α��� ����� ���� url ������ ����� �������� �̵�
	}
}