<%@ page contentType="text/html; charset=EUC-KR"%>
<%
		session.invalidate(); // 현재 세션 무효화 (제거)
		response.sendRedirect("01_login.jsp"); // 현재 세션이 서버에게 제거가 되고 다시 01_login.jsp 이동
%>