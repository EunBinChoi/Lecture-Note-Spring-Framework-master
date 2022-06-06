<%@page import="me.spring.login.beans.RegisterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/signup.js" defer></script>
</head>
<body>
	<%	
	Enumeration<String> attributeNames = session.getAttributeNames();
	while(attributeNames.hasMoreElements()) {
		String key = (String)attributeNames.nextElement();
		String value = (String)session.getAttribute(key);
		out.println(key + " : " + value + "<br/>");
		
		if(key.equals("signup")) {
			if(value != null) {
				if(value.equals("signup-fail")) {
					out.println("<script>alert('[SIGNUP WARN] ID IS ALREADY USED')</script>");
					session.removeAttribute("signup");
				}
			}
			
		}
	}
	
	RegisterVO register = null;
	String loginFailId = ""; 
	String loginFailPwd = ""; 
	if(request.getAttribute("register") != null) {
		register = (RegisterVO)request.getAttribute("register");
		if(register.getId() != null && !register.getId().equals("")) {
			loginFailId = register.getId();
		}
		if(register.getCurpwd() != null && !register.getCurpwd().equals("")) {
			loginFailPwd = register.getCurpwd();
		}
	}	
%>
	<jsp:include page="header.jsp" />
	<h2>SIGN UP</h2>
	<form id="signup" name="signup" method="post" action="${cp}/doSignup">

		<!-- 비동기 통신으로 동일한 id가 DB에 있는지 확인 -->
		<label for="id">ID: </label> <input type="text" id="id" name="id"
			value="<%= loginFailId %>" maxlength="20" autocomplete="off">
		<button type="button" id="duplicateCheckBtn">DUPLICATE CHECK</button>
		<div id="checkDuplicateResult" style="display: none"></div>
		<br /> <label for="curpwd">PASSWORD: </label> <input type="password"
			id="curpwd" name="curpwd" value="<%= loginFailPwd %>" maxlength="20"
			autocomplete="off"><br /> <label for="currepwd">REWRITE
			PASSWORD: </label> <input type="password" id="currepwd" name="currepwd"
			maxlength="20" autocomplete="off"><br /> <label for="name">NAME:
		</label> <input type="text" id="name" name="name" maxlength="20"
			autocomplete="off"><br /> <label for="email">EMAIL: </label>
		<input type="email" id="email" name="email" maxlength="20"
			autocomplete="off"><br /> <label for="phone">PHONE: </label>
		<input type="tel" id="phone" name="phone" maxlength="20"
			autocomplete="off"><br /> <input type="submit" value="Signup">
		<input type="reset" value="Reset" onclick="$('#reset').submit();">
	</form>

	<!-- reset 클릭했을 때 강제적으로 폼을 한번 전송시켜서 저장된 request.getAttribute("register")를 없애기 -->
	<form id="reset" name="reset" method="get" action="${cp}/signup"></form>
	<jsp:include page="footer.jsp" />
</body>
</html>