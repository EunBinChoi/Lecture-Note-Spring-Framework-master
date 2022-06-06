/*
 * ��������Ʈ�� �����ƿ䡮�� home.jsp or guest.jsp ���������� ������ ��������Ʈ�� �ִ� [��]�� Ŭ���ϸ� 
 * PBlogHCntServlet �������� �����ͺ��̽��� �����Ͽ� ī��Ʈ�� �ö� 
 *  * �����α׿��� ����� �� �� �ִ� ����� ����
 * 
 * ����� �ް��� �ϴ� ��������Ʈ�� ����� �Է��ϰ� [�Խ�]�� Ŭ���ϸ� PReplyPostServlet �������� �����ͺ��̽��� ���� ó��
 * �Է��� ����� home.jsp or guest.jsp���� ��� ����Ʈ�� �����ְ� 
 * 
 * ���������� ������ �Է��� ����� ��� ���� [X]�� Ŭ���ϸ� PReplyDeleteServlet �������� �����ͺ��̽��� �����Ͽ� ���� ó��
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


@WebServlet("/pReplyPost") // �� ���������� ���Ǵ� url ������ ������
public class PReplyPostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		PReplyMgr rMgr = new PReplyMgr();
		PReplyBean rbean = new PReplyBean();
		rbean.setNum(Integer.parseInt(request.getParameter("num")));
		rbean.setId(request.getParameter("id"));
		rbean.setComments(request.getParameter("comments"));
		rMgr.insertPReply(rbean); // ����� �����ϴ� �޼ҵ� ȣ��
		
		// gid ��û�� ������ 03_home.jsp or 05_guest.jsp�� �̵��� �� �Ǵ���
		// gid ���� null�̸� 03_home.jsp�� ȣ��
		// gid ���� null�� �ƴϸ� 05_guest.jsp�� ȣ��
		String gid = request.getParameter("gid");
		
		// gid ��û�� ���ٸ� 03_home.jsp�� �̵�
		if(gid == null) response.sendRedirect("03_home.jsp");
		
		// gid ��û�� �ִٸ� 05_guest.jsp�� �������� �̵���
		// 05_guest.jsp�� �̵��� �� ���ǰ����� �ٽ� gid ���� �ѱ�
		else response.sendRedirect("05_guest.jsp?gid="+gid);
	}
}