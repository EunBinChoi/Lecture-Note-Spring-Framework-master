<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,me.spring.member.beans.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/file/upload.css">

<title>Sally's Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/file/upload.js" defer></script>
</head>
<body>
	
	<jsp:include page="../header.jsp" />
	<jsp:include page="../locale.jsp" />
	<hr/>

	<%
		String idKey = "";
		if(session.getAttribute("idKey") != null) {
			idKey = (String)session.getAttribute("idKey");
		}
		
		String userName = "";
		if(request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
		} else {
			userName = idKey;
		}
				
		String title = "";
		if(request.getParameter("title") != null) {
			title = request.getParameter("title");
		}
		
		String content = "";
		if(request.getParameter("content") != null) {
			content = request.getParameter("content");
		}
		
		String fileCount ="0";
		int fileCountInt = 0;
		if(request.getParameter("fileCount") != null){
			fileCount = request.getParameter("fileCount");
			try {
				fileCountInt = Integer.parseInt(fileCount);
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
	%>
	
	
	<!-- 파일 업로드 폼 -->
	<h3>File Upload</h3>
	<form name="fileForm" id="fileForm" method="post" enctype="multipart/form-data" accept-charset="UTF-8" style="padding: 10px;">
		<table style="width: 500px; margin: 5px 0px;">
		<tr>
		<td><label for="user">user</label></td>
		<td><input type="text" id="userName" name="userName" size="20" maxlength="20" value="<%=userName%>" placeholder="Please input user's id." style="font-family: Arial, Helvetica, sans-serif; font-size: 12px;"> </td>
		</tr>
		
		<tr>
		<td><label for="title">title</label></td>
		<td><input type="text" id="title" name="title" size="47" maxlength="30" value="<%=title%>" placeholder="Please input title." style="font-family: Arial, Helvetica, sans-serif; font-size: 12px;"></td>
		</tr>
		
		<tr>
		<td><label for="content">content</label></td>
		<td><textarea id="content" name="content" rows="10" cols="50" maxlength="500" placeholder="Please input content." style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; resize: none;"><%=content%></textarea></td>
		</tr>
		
		<tr>
		<td><label for="fileCount">num of file</label></td>
		<td>
		<input type="number" id="fileCount" name="fileCount" min="0" max="10" value="<%=fileCountInt%>"> 
		<input type="button" id="fileCountBtn" value="OK" style="font-family: Arial, Helvetica, sans-serif; font-size: 12px;">
		</td>
		</tr>


		<% for(int i = 0; i < fileCountInt; i++) { %>
		<tr>
			<td><label for="fileUpload<%=i%>"></label></td>
			<td><input type="file" id="fileUpload<%=i%>"  name="file<%=i%>" size="60" multiple></td>
		</tr>
		<% } %>
		

		<tr>
		<td><label for="fileSubmitBtn"></label></td>
		<td><input type="submit" id="fileSubmitBtn" name="fileSubmitBtn" value="Submit" style="height: 30px; width: 200px;" multiple></td>
		</tr>
		
		<tr>
		<td><label for="resetBtn"></label></td>
		<td><input type="reset"  id="resetBtn" name="resetBtn" value="Reset" style="height: 30px; width: 200px;"></td>
		</tr>
		</table>		
	</form>
	
	<hr/>
	<table id="previewTable" style="border-color: white; margin-top: 20px">
	</table>

	
	<jsp:include page="../footer.jsp" />
</body>
</html>