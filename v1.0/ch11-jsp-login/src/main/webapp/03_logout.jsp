<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%
      session.invalidate();
%>
<script>
   alert('로그아웃 되었습니다.');
   location.href="01_login.jsp";
</script>