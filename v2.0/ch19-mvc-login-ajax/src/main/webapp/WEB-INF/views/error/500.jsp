<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"/> 
	<h1>500 Error Page</h1>
	<p>Application has encountered an error. Please contact support on
		...</p>

    Failed URL: ${url} <br/>
    Exception:  ${exception.message}
    <jsp:include page="../footer.jsp"/> 
</body>
</html>