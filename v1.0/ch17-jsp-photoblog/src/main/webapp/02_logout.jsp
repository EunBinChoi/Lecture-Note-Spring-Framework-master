<%@ page contentType="text/html; charset=EUC-KR"%>
<%
		session.invalidate(); // ���� ���� ��ȿȭ (����)
		response.sendRedirect("01_login.jsp"); // ���� ������ �������� ���Ű� �ǰ� �ٽ� 01_login.jsp �̵�
%>