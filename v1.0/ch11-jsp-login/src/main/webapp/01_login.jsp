<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	  request.setCharacterEncoding("EUC-KR");
	  String id = (String)session.getAttribute("idKey");
%>


<!-- https://dlgkstjq623.tistory.com/108 
	https://aircook.tistory.com/entry/ORACLE%EC%97%90%EC%84%9C-%ED%95%9C%EA%B8%80%EC%9D%84-%EC%A7%80%EC%9B%90%ED%95%98%EB%8A%94-%EC%BA%90%EB%A6%AD%ED%84%B0%EC%85%8B
	
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<div align="center"><br/><br/>
		<%if (id != null) {%>
		<b><%=id%></b>�� ȯ�� �մϴ�.
			<a href="03_logout.jsp">�α׾ƿ�</a>
			<%} else {%>
		<form name="loginFrm" method="post" action="02_loginProc.jsp">
			<table>
				<tr>
					<td align="center" colspan="2"><h4>�α���</h4></td>
				</tr>
				<tr>
					<td>�� �� ��</td>
					<td><input name="id"></td>
				</tr>
				<tr>
					<td>��й�ȣ</td>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							<input type="button" value="�α���" onclick="loginCheck()">&nbsp;
							<input type="button" value="ȸ������" onClick="javascript:location.href='04_member.jsp'">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<%}%>
	</div>
</body>
</html>