<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="ex01.ZipcodeBean"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="mMgr" class="ex01.MemberMgr" />
<%
	 request.setCharacterEncoding("EUC-KR");
	 String search = request.getParameter("search");
	 String address = null;
	 Vector<ZipcodeBean> vlist = null;
	 if (search.equals("y")) {
		address = request.getParameter("address");
		vlist = mMgr.zipcodeRead(address);
	 }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>우편번호 검색</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<div align="center">
		<br />
		<form name="zipFrm" method="post">
			<table>
				<tr>
					<td><br/>도로명 입력 : <input name="area3">
					 <input type="button" value="검색" onclick="loadSearch();">
					 </td>
				</tr>
				<!-- 검색결과 시작 -->
				<%
					if (search.equals("y")) {
							if (vlist.isEmpty()) {
				%>
				<tr>
					<td align="center"><br/>검색된 결과가 없습니다.</td>
				</tr>
				<%
					} else {
				%>
				<tr>
					<td align="center"><br/>※검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다.</td>
				</tr>
				<%
					for (int i = 0; i < vlist.size(); i++) {
							ZipcodeBean bean = vlist.get(i);
							String rZipcode = bean.getZipcode();
							String rArea = bean.getAddress();
							String adds = rArea;
				%>
				<tr>
					<td><a href="#"
						onclick="javascript:sendAdd('<%=rZipcode%>','<%=adds%>')">
							<%=rZipcode%> <%=adds%></a></td>
				</tr>
				<%
					}//for
						}//if
					}//if
				%>
				<!-- 검색결과 끝 -->
				<tr>
					<td align="center"><br/>
					<a href="#" onClick="self.close()">닫기</a></td>
				</tr>
			</table>
			<input type="hidden" name="search" value="y">
		</form>
	</div>
</body>
</html>