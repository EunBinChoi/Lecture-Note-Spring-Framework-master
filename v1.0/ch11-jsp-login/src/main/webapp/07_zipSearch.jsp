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
<title>�����ȣ �˻�</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<div align="center">
		<br />
		<form name="zipFrm" method="post">
			<table>
				<tr>
					<td><br/>���θ� �Է� : <input name="area3">
					 <input type="button" value="�˻�" onclick="loadSearch();">
					 </td>
				</tr>
				<!-- �˻���� ���� -->
				<%
					if (search.equals("y")) {
							if (vlist.isEmpty()) {
				%>
				<tr>
					<td align="center"><br/>�˻��� ����� �����ϴ�.</td>
				</tr>
				<%
					} else {
				%>
				<tr>
					<td align="center"><br/>�ذ˻� ��, �Ʒ� �����ȣ�� Ŭ���ϸ� �ڵ����� �Էµ˴ϴ�.</td>
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
				<!-- �˻���� �� -->
				<tr>
					<td align="center"><br/>
					<a href="#" onClick="self.close()">�ݱ�</a></td>
				</tr>
			</table>
			<input type="hidden" name="search" value="y">
		</form>
	</div>
</body>
</html>