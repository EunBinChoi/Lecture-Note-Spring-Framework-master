<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/update.js" defer></script>
</head>
<body>
	<% 
		Enumeration<String> attributeNames = session.getAttributeNames();
		while(attributeNames.hasMoreElements()) {
			String key = (String)attributeNames.nextElement();
			String value = (String)session.getAttribute(key);
			out.println(key + " : " + value + "<br/>");
			
			if(key.equals("update")) {
				if(value != null) {
					if(value.equals("update-fail")) {
						out.println("<script>alert('[UPDATE WARN] UPDATE FAIL')</script>");
						session.removeAttribute("update");
					}
				}
				
			}
		}

	%>
	<jsp:include page="header.jsp" />
	<h2>UPDATE</h2>
	<form id="update" name="update" method="post" action="${cp}/doUpdate">

		<label for="id">ID: </label> <input type="text" id="id" name="id"
			value="${ register.id }" autocomplete="off" disabled><br /> <label
			for="curpwd">CURRENT PASSWORD: </label>
		<!-- 비동기 통신으로 id/pwd가 일치하는 게 DB에 있는지 확인 -->
		<input type="password" id="curpwd" name="curpwd" maxlength="20"
			autocomplete="off">
		<button type="button" id="checkIdPwdBtn">MODIFY CHECK</button>
		<div id="checkIdPwdResult" style="display: none"></div>
		<br /> <label for="newpwd">NEW PASSWORD: </label> <input
			type="password" id="newpwd" name="newpwd" maxlength="20"
			autocomplete="off"><br /> <label for="newrepwd">REWRITE
			NEW PASSWORD: </label> <input type="password" id="newrepwd" name="newrepwd"
			maxlength="20" autocomplete="off"><br /> <label for="name">NAME:
		</label> <input type="text" id="name" name="name" maxlength="20"
			autocomplete="off"><br /> <label for="email">EMAIL: </label>
		<input type="email" id="email" name="email" maxlength="20"
			autocomplete="off"><br /> <label for="phone">PHONE: </label>
		<input type="tel" id="phone" name="phone" maxlength="20"
			autocomplete="off"><br /> <input type="submit" value="Modify">
		<input type="reset" value="Reset">
	</form>
	<jsp:include page="footer.jsp" />
</body>
</html>