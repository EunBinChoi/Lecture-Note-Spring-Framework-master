<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="mgr" class="ex01.MemberMgr"/>
<jsp:useBean id="bean" class="ex01.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<%
		boolean result = mgr.insertMember(bean);
		String msg = "ȸ�����Կ� ���� �Ͽ����ϴ�.";
		String location = "04_member.jsp";
		if(result){
			msg = "ȸ�������� �Ͽ����ϴ�.";
			location = "01_login.jsp";
		}
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=location%>";
</script>