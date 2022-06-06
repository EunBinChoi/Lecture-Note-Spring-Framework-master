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
	<%			
		Enumeration<String> attributeNames = session.getAttributeNames(); // attribute names 값만 모여있음
		//out.println(attributeNames.toString());
		while(attributeNames.hasMoreElements()) {
			String key = (String)attributeNames.nextElement();
			String value = (String)session.getAttribute(key);
			out.println(key + " : " + value + "<br/>");
			
			if(key.equals("login")) {
				if(value != null) {
					if(value.equals("login-success")) {
						out.println("<script>alert(\"[LOGIN WARN] LOGIN SUCCESS!\")</script>");						
						session.removeAttribute("login");	
					}
				}
			} else if(key.equals("update")) {
				if(value != null) {
					if(value.equals("update-success")) {
						out.println("<script>alert(\"[UPDATE WARN] UPDATE SUCCESS!\")</script>");						
						session.removeAttribute("login");	
					}
				}
			}
		}
	%>
	<h2>Main Page</h2>
	<h3>Hello! ${ register.id }</h3>


	<a href="${cp}/update">revise member's info</a>
	<br />
	<a href="${cp}/delete">delete member</a>
	<br />
	<a href="#" id="logout">logout</a>
	<script>
		const logout = document.getElementById('logout');
		logout.addEventListener("click", () => {
			const answer = confirm("YOU WANT TO LOGOUT?");
			if(answer) {
				logout.href = "doLogout";
			}
		});
	</script>
	<jsp:include page="footer.jsp" />
</body>
</html>