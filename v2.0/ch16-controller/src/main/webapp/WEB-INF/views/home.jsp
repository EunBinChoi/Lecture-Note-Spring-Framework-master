<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME PAGE</title>
</head>
<body>
<h1>HOME PAGE</h1>
<%
	// @ModelAttribute로 지정한 값
	String cp = "";
	if(request.getAttribute("cp") != null) {
		cp = (String)request.getAttribute("cp");
	}
	String serverTime = "";
	if(request.getAttribute("serverTime") != null) {
		serverTime = (String)request.getAttribute("serverTime");
	}
	
	// 각 메소드에서 저장한 model 값
	String key = "";
	if(request.getAttribute("key") != null) {
		key = (String)request.getAttribute("key");
	}
%>
<P>key value is ${cp}.</P>
<P>key value is <%= cp %>.</P>
<br/>
<P>key value is ${serverTime}.</P>
<P>key value is <%= serverTime %>.</P>
<br/>
<P>key value is ${key}.</P>
<P>key value is <%= key %>.</P>
</body>
</html>
