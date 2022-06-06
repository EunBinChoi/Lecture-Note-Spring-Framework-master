<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	/* String id = request.getParameter("id");
	out.print(id); // request.getParameter() ��� �Ұ�  */
	
	String id = "";
	String subject = "";
	String fileName1 = "";
	String fileName2 = "";
	String orgfileName1 = "";
	String orgfileName2 = "";
	
	String uploadPath = request.getRealPath("upload"); // upload�� ������ / ������ ��θ� ���ؿ�
	//out.print(uploadPath);

	try {
		MultipartRequest multi = new MultipartRequest( // MultipartRequest �ν��Ͻ� ����(cos.jar�� ���̺귯��)
				request, 
				uploadPath, // ������ ������ ���丮 ����
				10 * 1024, // ÷������ �ִ� �뷮 ����(bite) / 10KB / �뷮 �ʰ� �� ���� �߻�
				"EUC-KR", // ���ڵ� ��� ����
				new DefaultFileRenamePolicy() // �ߺ� ���� ó��(������ ���ϸ��� ���ε�Ǹ� �ڿ� ���� ���� �ٿ� �ߺ� ȸ��)
		);

		id = multi.getParameter("id"); // form�� name="id"�� ���� ����
		subject = multi.getParameter("subject"); // form�� name="subject"�� ���� ����

		/* form�� <input type="file"> name���� �� ��� name�� ���Ҷ� ���
		Enumeration files=multi.getFileNames(); // form�� type="file" name�� ����
		String file1 =(String)files.nextElement(); // ù��° type="file"�� name ����
		String file2 =(String)files.nextElement(); // �ι�° type="file"�� name ����
		*/

		fileName1 = multi.getFilesystemName("file1"); // name=file1�� ���ε�� �ý��� ���ϸ��� ����(�ߺ��� ������ ������, �ߺ� ó�� �� ���� �̸�)
		orgfileName1 = multi.getOriginalFileName("file1"); // name=file1�� ���ε�� �������� �̸��� ����(�ߺ� ó�� �� �̸�)

		fileName2 = multi.getFilesystemName("file2");
		orgfileName2 = multi.getOriginalFileName("file2");
		
	} catch (Exception e) {
		e.getStackTrace();
	} // ���ε� ����
%>

	<!-- ���ε� �� ������ Ȯ���ϴ� ������ �̵� / ������ ���� �����͸� hidden ������� ���� -->
	<form action="07_check.jsp" method="post">
		<input type="hidden" name="id" value="<%=id%>"> <input
			type="hidden" name="subject" value="<%=subject%>"> <input
			type="hidden" name="fileName1" value="<%=fileName1%>"> <input
			type="hidden" name="fileName2" value="<%=fileName2%>"> <input
			type="hidden" name="orgfileName1" value="<%=orgfileName1%>">
		<input type="hidden" name="orgfileName2" value="<%=orgfileName2%>">
		<input type="submit" value="���ε� Ȯ��">
	</form>
</body>
</html>