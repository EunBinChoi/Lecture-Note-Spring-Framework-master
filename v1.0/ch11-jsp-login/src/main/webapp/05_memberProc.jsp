<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="mgr" class="ex01.MemberMgr"/>
<jsp:useBean id="bean" class="ex01.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<%
		boolean result = mgr.insertMember(bean);
		String msg = "회원가입에 실패 하였습니다.";
		String location = "04_member.jsp";
		if(result){
			msg = "회원가입을 하였습니다.";
			location = "01_login.jsp";
		}
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=location%>";
</script>