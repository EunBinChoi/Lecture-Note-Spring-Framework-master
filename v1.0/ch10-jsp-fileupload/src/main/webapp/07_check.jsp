<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%	request.setCharacterEncoding("EUC-KR"); // ���ڵ� Ÿ�� ���� %>
<%  // upload.jsp(hidden)�� ���� �Ѿ�� ������ ����
	String id = request.getParameter("id");
	String subject = request.getParameter("subject");
	String fileName1 = request.getParameter("fileName1");
	String fileName2 = request.getParameter("fileName2");
	String orgfileName1 = request.getParameter("orgfileName1");
	String orgfileName2 = request.getParameter("orgfileName2");
%>
<body>
	���̵� : <%=id %><br>
	���� : <%=subject %><br>
	÷������(Ŭ���� �ٿ�ε�)<br>
	<!-- download.jsp ���Ϸ� ����� ������ �̸��� �Ѱ��� -->
	- ����1 : <a href="08_download.jsp?fileName=<%=fileName1%>"><%=orgfileName1 %></a><br>
	- ����2 : <a href="08_download.jsp?fileName=<%=fileName2%>"><%=orgfileName2 %></a><br>
</body>
</html>