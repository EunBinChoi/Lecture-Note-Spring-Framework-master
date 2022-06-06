<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/login.js" defer></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form id="tries" name="tries" method="post" action="${cp}/signup"
		style="display: none">
		<input type="hidden" id="triesId" name="id" value="${ loginFailId }" />
		<input type="hidden" id="triesCurpwd" name="curpwd"
			value="${ loginFailPwd }" />
	</form>

	<%			
		Enumeration<String> attributeNames = session.getAttributeNames(); // attribute names 값만 모여있음
		//out.println(attributeNames.toString());
		while(attributeNames.hasMoreElements()) {
			String key = (String)attributeNames.nextElement();
			String value = (String)session.getAttribute(key);
			out.println(key + " : " + value + "<br/>");
			
			if(key.equals("signup")) {
				if(value != null) {
					if(value.equals("signup-success")) {
						out.println("<script>alert('[SIGNUP INFO] SIGNUP SUCCESS!')</script>");
						session.removeAttribute("signup");
					}					
				}	
			} else if(key.equals("delete")) {
				if(value != null) {
					if(value.equals("delete-success")) {
						out.println("<script>alert('[DELETE WARN] DELETE SUCCESS!')</script>");
						session.removeAttribute("delete");
					}
				}
			} else if(key.equals("login")) {
				if(value != null) {
					if(value.equals("login-fail")) {
						//out.println("<script>const answer = confirm('ID/PW INCORRECT! DO YOU WANT TO SIGNUP? ')</script>"); // 1)
						out.println(
								"<script>" 
							+   "const answer = confirm('ID/PW INCORRECT! DO YOU WANT TO SIGNUP? ');"
							+   "console.log(answer);"
							+  	"if(answer) {" 
							//+		"$('#triesId').val($('#loginId').val());" 
							//+		"$('#triesCurpwd').val($('#loginCurpwd').val());" 
							+       "console.log($('#triesId').val());"
							+       "console.log($('#triesCurpwd').val());"
							+		"$('#tries').submit();" 
							+  "}"
							+   "</script>");						
						session.removeAttribute("login");
					} else if(value.equals("logout")) {
						out.println("<script>alert('[LOGOUT INFO] LOGOUT SUCCESS!')</script>");
						session.removeAttribute("login");
					} else if(value.equals("session-end")) {
						out.println("<script>alert('[SESSION INFO] SESSION DONE!')</script>");
						session.removeAttribute("login");
					} 
				}
			} 
			
		}	
		
	%>
	<h1>Login</h1>
	<form id="login" name="login" method="post" action="${cp}/doLogin">
		<label for="id">ID: </label> <input type="text" id="loginId" name="id"
			maxlength="20" autocomplete="off"><br /> <label for="curpwd">PASSWORD:
		</label> <input type="password" id="loginCurpwd" name="curpwd" maxlength="20"
			autocomplete="off"><br /> <input type="submit" value="login">
	</form>
	<a href="${cp}/signup"><button>signup</button></a>
	<jsp:include page="footer.jsp" />
</body>
</html>