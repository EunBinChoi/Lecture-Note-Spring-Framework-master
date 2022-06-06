<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<!-- trim-directive-whitespace
		true: 
		브라우저에서 '소스보기'를 할 경우 스크립트 코드로 인한 공백을 제거 
		JSP 를 사용하다보면 java이 있던 자리가 servlet로 변경되고 나서 빈줄 (whitespace)로 남아있는 것을 보게 됨
		크게 문제는 없지만 깔끔해 보이지도 않고 파일의 용량도 늘어나게 되니 없애고 싶을 때 선택할 수 있는 방법!
-->

<html>
<head>
	<title>Home</title>
</head>
<body>
<%
	String trimDirectiveWhitespacesTest1 = "";

%>
<h1>
	Hello world!  
</h1>
<%
	String trimDirectiveWhitespacesTest2 = "";

%>
<P>  The time on the server is ${serverTime}. </P>
<%
	String trimDirectiveWhitespacesTest3 = "";

%>
</body>
</html>
