<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/delete.js" defer></script>
</head>
<body>
	<%			
		Enumeration<String> attributeNames = session.getAttributeNames(); // attribute names 값만 모여있음
		//out.println(attributeNames.toString());
		while(attributeNames.hasMoreElements()) {
			String key = (String)attributeNames.nextElement();
			String value = (String)session.getAttribute(key);
			out.println(key + " : " + value + "<br/>");
			
			if(key.equals("delete")) {
				if(value != null) {
					if(value.equals("delete-fail")) {
						out.println("<script>alert('[DELETE WARN] DELETE FAIL!')</script>"); // 2)
						session.removeAttribute("delete");	
					}
				}
				
			}			
		}
	%>
	<jsp:include page="header.jsp" />
	<h2>Notice for deleting your info</h2>
	<p>Please read the introduction for membership cancellation.</p>
	<form id="delete" name="delete" method="post" action="${cp}/doDelete">
		<label for="agree">check if you want to really delete your
			info.</label> <input type="checkbox" id="agree" name="agree"> <input
			type="submit" value="Agree">
	</form>
	<%
		
	%>
	<jsp:include page="footer.jsp" />
</body>
</html>