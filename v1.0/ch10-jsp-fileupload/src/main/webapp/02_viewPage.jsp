<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%> 
<%-- ���� ���ε�� ���� ���ϰ� ������ ������ ���� �� ����⸦ �����ϱ� ���� --%>
<%@page import="java.util.*, java.io.*, java.net.*"%> 
<%-- File Ŭ������ ����ϱ� ���� --%>


<%
	String saveFolder = request.getRealPath("files");
	// �ε� ������ ���� ��� (������ file ���� ��ġ ����)
	
	String encType = "EUC-KR"; // ���ε� �� ������ ���ڵ� Ÿ�� 
	int maxSize = 5 * 1024 * 1024; // ���ε� �� ������ �ִ� ũ�� (5 * 2^10 * 2^10, 5mb)
	try {
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, maxSize,
				encType, new DefaultFileRenamePolicy());
		Enumeration params = multi.getParameterNames();
		// Enumeration�� ���� ��ҵ��� �迭 ���·� �����Ǿ��ִ� ����
		// java.util.* ����Ʈ

		while (params.hasMoreElements()) { 
			// �޼ҵ��� ��ȯ ���°� Enumeration
			// hasMoreElements() �޼ҵ带 ����ؼ� ���Ե� ��ҵ��� �ִ��� �˻�
			String name = (String) params.nextElement(); 
			// Enumeration�� �ִ� �޼ҵ� (���� ��� ��ȯ�ϰ� ���� ��� �ִ� ��ġ�� ��ȯ)
			String value = multi.getParameter(name);
			// ����� �̸��� getParameter() �޼ҵ忡 ���ڷ� �����ؼ� �ش�Ǵ� �̸��� ��� ���� ����
			// request���� ���Ǵ� getParameter()�� ������ ����
			// enctype="multipart/form-data"�� ������ ���� ��� �Ķ������ �̸��� ���ؼ� 
			// ���� ��� ���� ���Ǵ� �޼ҵ�
			
			out.println(name + " = " + value + "<br/>");
			// while���� ���ؼ� �ݺ� ������ �Ǹ鼭 name, value ������ ��䰪�� ������ ���
		}

		Enumeration files = multi.getFileNames();
		// enctype="multipart/form-data"�� ������ �� ��� �� 
		// input type="file" �Ӽ����� ������ �� ����� �̸��� Enumeration ��ȯ
		while (files.hasMoreElements()) { 
			/* java.util.Enumeration �������̽��� �ִ� �޼ҵ�
			Enumeration�� ��Ұ� �ִ��� ������ üũ
			�ϳ��� �ִٰ� ���� �� �� ���� ��Ұ� �ִ��� ������ ��ȯ */
			
			String name = (String) files.nextElement();
			// java.util.Enumeration �������̽��� �ִ� �޼ҵ�� Enumeration�� ��Ұ� ���� ��
			// ��Ҹ� �����ؼ� Object (��ü) Ÿ������ ��ȯ�ϴ� �޼ҵ� (Object -> String Ÿ�� ĳ����)
			String filename = multi.getFilesystemName(name);
			filename = URLEncoder.encode(filename);
			// ������ ������ ����� ���� �̸� ��ȯ
			// ����ڰ� ���ϼ��� â�� ���ؼ� ������ ������ ������ ������ ���ε�� �� 
			// ������ ���ε�Ǿ��ִ� ���ϵ� �� ���ϸ��� �ߺ��Ǵ� ��찡 ������ �̸� ����
			// ����Ǵ� ���ϸ��� ...1.xxx, ...2.,xxx �̷��� �ѹ��� ��
			
			String original = multi.getOriginalFileName(name);
			// ���� ���ϸ� ��ȯ
			// �ߺ��Ǵ� ������ ���� ��� ���� �� ���� �̸� ��ȯ
			
			String type = multi.getContentType(name); // ���� ���ؽ�Ʈ Ÿ�� ��ȯ
			File f = multi.getFile(name); 
			// File Ŭ������ java.io ��Ű��
			// getFile() �޼ҵ忡 File Ÿ������ ����� ��ȯ�ϱ� ������ ��ȯ���� �°� ����� �ޱ� ���ؼ�
			// File Ÿ���� f�� getFile()�� ���� ��� ����
			// File ��ü�� ���ؼ� ���ε� �� ���Ͽ� ���� ���� �˾Ƴ�
			
			out.println("�Ķ���� �̸� : " + name + "<br/>");
			out.println("���� ���� �̸� : " + original + "<br/>");
			out.println("����� ���� �̸� : " + filename + "<br/>");
			out.println("���� Ÿ�� : " + type + "<br/>");
			if (f != null) { // File ��ü�� null�� �ƴ϶��
				out.println("ũ�� : " + f.length()+ "����Ʈ"); 
				// File Ŭ������ �ִ� length() �޼ҵ带 ���� ���ε� ������ ũ�� �˾Ƴ�
				out.println("<br/>");
			}
		}
	} catch (IOException ioe) { 
		// MultipartRequest �����ڴ� IOException ���� ���� 
		// (���ε��� ������ ������ �ִ� ũ�⺸�� ũ�ų� ������ ���� �� ������ �� ��� IOException ����)
		System.out.println(ioe);
	} catch (Exception ex) { // �̿� ����
		System.out.println(ex);
	}
%>
