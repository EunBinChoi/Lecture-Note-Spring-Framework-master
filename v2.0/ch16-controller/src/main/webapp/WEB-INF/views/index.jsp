<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="me.spring.controller.beans.Member, java.util.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE</title>
</head>
<body>
<h1>INDEX PAGE</h1>
<!-- Object = (type casting) => List<Member> -->
<%! @SuppressWarnings("unchecked") %>
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
	Member member = null;
	if(request.getAttribute("member") != null) {
		member = (Member)request.getAttribute("member");
	}
	
	List<Member> members = null;
	if(request.getAttribute("members") != null) {
		members = (List<Member>)request.getAttribute("members");
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
<br/>
<P>key value is ${member}.</P>
<P>key value is <%= member %>.</P>
<br/>
<P>key value is ${members}.</P>
<P>key value is <%= members %>.</P>
</body>
</html>