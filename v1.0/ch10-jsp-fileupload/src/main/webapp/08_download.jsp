<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%
	// check.jsp�� ���� �Ѿ�� ��(fileName�� ��)�� ����
	String fileName = request.getParameter("fileName");

	// �ٿ�ε� �� ������ ��θ� ���ϰ� File ��ü ����
	ServletContext context = getServletContext();
	String downloadPath = context.getRealPath("upload");
	String filePath = downloadPath + "/" + fileName;
	File file = new File(filePath);

	// MIMETYPE ����
	String mimeType = getServletContext().getMimeType(filePath);
	if (mimeType == null)
		mimeType = "application/octet-stream";
	response.setContentType(mimeType);

	// �ٿ�ε� ���� �� �ѱ� ���ϸ� ������ �� ����
	String encoding = new String(fileName.getBytes("EUC-KR"), "8859_1");
	response.setHeader("Content-Disposition", "attachment; filename= " + encoding);

	// ��û ������ �о Ŭ���̾�Ʈ�� ����
	FileInputStream in = new FileInputStream(file);
	ServletOutputStream outStream = response.getOutputStream();

	byte b[] = new byte[4096];
	int data = 0;
	while ((data = in.read(b, 0, b.length)) != -1) {
		outStream.write(b, 0, data);
	}

	outStream.flush();
	outStream.close();
	in.close();
%>