<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, me.spring.login.beans.RegisterVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<form name="locale" method="get" action="${cp}/">
		<select id="lang" name="lang">
			<option value="ko">Korean</option>
			<option value="en">English</option>
			<option value="ja">Japanese</option>
			<option value="" selected>No Selected</option>
		</select>
		<input type="submit" value="OK">
	</form>
	
	<h2>${messageSource.heading}</h2>
	<h3>${messageSource.loginMessage}</h3>
	<h3>${serverTime}</h3>

	<a href="#">${messageSource.reviseMember}</a>
	<br />
	<a href="#">${messageSource.deleteMember}</a>
	<br />
	<a href="#" id="logout">${messageSource.logout}</a>
	<jsp:include page="footer.jsp" />
</body>
</html>