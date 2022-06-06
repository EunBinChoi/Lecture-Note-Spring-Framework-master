<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%> 
<%-- 파일 업로드시 기존 파일과 동일한 파일이 있을 때 덮어쓰기를 방지하기 위해 --%>
<%@page import="java.util.*, java.io.*, java.net.*"%> 
<%-- File 클래스를 사용하기 위해 --%>


<%
	String saveFolder = request.getRealPath("files");
	// 로드 파일의 실제 경로 (서버에 file 폴더 위치 지정)
	
	String encType = "EUC-KR"; // 업로드 할 파일의 인코딩 타입 
	int maxSize = 5 * 1024 * 1024; // 업로드 할 파일의 최대 크기 (5 * 2^10 * 2^10, 5mb)
	try {
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, maxSize,
				encType, new DefaultFileRenamePolicy());
		Enumeration params = multi.getParameterNames();
		// Enumeration은 여러 요소들이 배열 형태로 구성되어있는 형태
		// java.util.* 임포트

		while (params.hasMoreElements()) { 
			// 메소드의 반환 형태가 Enumeration
			// hasMoreElements() 메소드를 사용해서 포함된 요소들이 있는지 검사
			String name = (String) params.nextElement(); 
			// Enumeration에 있는 메소드 (현재 요소 반환하고 다음 요소 있는 위치로 반환)
			String value = multi.getParameter(name);
			// 얻어진 이름을 getParameter() 메소드에 인자로 전달해서 해당되는 이름에 담긴 값을 얻어옴
			// request에서 사용되는 getParameter()와 동일한 역할
			// enctype="multipart/form-data"로 지정된 폼에 담긴 파라미터의 이름을 통해서 
			// 값을 얻기 위해 사용되는 메소드
			
			out.println(name + " = " + value + "<br/>");
			// while문에 의해서 반복 수행이 되면서 name, value 변수에 담긴값을 브라우저 출력
		}

		Enumeration files = multi.getFileNames();
		// enctype="multipart/form-data"로 지정된 폼 요소 중 
		// input type="file" 속성으로 지정된 폼 요소의 이름을 Enumeration 반환
		while (files.hasMoreElements()) { 
			/* java.util.Enumeration 인터페이스에 있는 메소드
			Enumeration에 요소가 있는지 없는지 체크
			하나가 있다고 했을 때 그 다음 요소가 있는지 없는지 반환 */
			
			String name = (String) files.nextElement();
			// java.util.Enumeration 인터페이스에 있는 메소드로 Enumeration에 요소가 있을 때
			// 요소를 추출해서 Object (객체) 타입으로 반환하는 메소드 (Object -> String 타입 캐스팅)
			String filename = multi.getFilesystemName(name);
			filename = URLEncoder.encode(filename);
			// 서버에 실제로 저장된 파일 이름 반환
			// 사용자가 파일선택 창을 통해서 선택한 파일이 서버의 폴더에 업로드될 때 
			// 기존에 업로드되어있던 파일들 중 파일명이 중복되는 경우가 있으면 이름 변경
			// 변경되는 파일명은 ...1.xxx, ...2.,xxx 이렇게 넘버링 됨
			
			String original = multi.getOriginalFileName(name);
			// 실제 파일명 반환
			// 중복되는 파일이 있을 경우 변경 전 파일 이름 반환
			
			String type = multi.getContentType(name); // 파일 컨텍스트 타입 반환
			File f = multi.getFile(name); 
			// File 클래스는 java.io 패키지
			// getFile() 메소드에 File 타입으로 결과를 반환하기 때문에 반환형에 맞게 결과를 받기 위해서
			// File 타입의 f로 getFile()에 대한 결과 받음
			// File 객체를 통해서 업로드 된 파일에 대한 정보 알아냄
			
			out.println("파라미터 이름 : " + name + "<br/>");
			out.println("실제 파일 이름 : " + original + "<br/>");
			out.println("저장된 파일 이름 : " + filename + "<br/>");
			out.println("파일 타입 : " + type + "<br/>");
			if (f != null) { // File 객체가 null이 아니라면
				out.println("크기 : " + f.length()+ "바이트"); 
				// File 클래스에 있는 length() 메소드를 통해 업로드 파일의 크기 알아냄
				out.println("<br/>");
			}
		}
	} catch (IOException ioe) { 
		// MultipartRequest 생성자는 IOException 예외 던짐 
		// (업로드할 파일이 지정한 최대 크기보다 크거나 페이지 읽을 때 문제가 될 경우 IOException 던짐)
		System.out.println(ioe);
	} catch (Exception ex) { // 이외 예외
		System.out.println(ex);
	}
%>
