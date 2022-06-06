<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/member/update.js" defer></script>
</head>
<body>
	<jsp:include page="../header.jsp"/> 
	<jsp:include page="../locale.jsp" />
	<hr/>

	<% 
		Enumeration<String> attributeNames = session.getAttributeNames();
		while(attributeNames.hasMoreElements()) {
			String key = (String)attributeNames.nextElement();
			Object value = session.getAttribute(key);
			out.println(key + " : " + value + "<br/>");
			
			if(key.equals("update")) {
				if(value != null) {
					if(value.toString().equals("update-fail")) {
						out.println("<script>alert('[UPDATE WARN] UPDATE FAIL')</script>");
						session.removeAttribute("update");
					}
				}
				
			}
		}

	%>
	
	<h2>UPDATE</h2>
	<form id="update" name="update" method="post" action="${cp}/member/doUpdate">

		<label for="id">ID: </label> 
		<input type="text" id="id" name="mId" value="${ member.mId }" autocomplete="off" disabled><br/> 
		<label for="curpwd">CURRENT	PASSWORD: </label> 
		<!-- 비동기 통신으로 id/pwd가 일치하는 게 DB에 있는지 확인 -->
		<input type="password" id="curpwd" name="mCurpwd" maxlength="20" autocomplete="off">
		<button type="button" id="checkIdPwdBtn">MODIFY CHECK</button>
		<div id="checkIdPwdResult" style="display: none"></div><br/>
		
		<label for="newpwd">NEW PASSWORD: </label> 
		<input type="password" id="newpwd" name="mNewpwd" maxlength="20" autocomplete="off"><br/> 
		<label for="newrepwd">REWRITE NEW PASSWORD: </label> 
		<input type="password" id="newrepwd" name="mNewrepwd" maxlength="20" autocomplete="off"><br/>
		<label for="name">NAME: </label> 
		<input type="text" id="name" name="mName" maxlength="20" autocomplete="off"><br/> 
		<label for="email">EMAIL: </label> 
		<input type="email" id="email" name="mEmail" maxlength="20" autocomplete="off"><br/> 
		<label for="phone">PHONE: </label> 
		<input type="tel" id="phone" name="mPhone" maxlength="20" autocomplete="off"><br/>
		<input type="submit" value="Modify"> 
		<input type="reset" value="Reset">
	</form>
	<jsp:include page="../footer.jsp" />
</body>
</html>