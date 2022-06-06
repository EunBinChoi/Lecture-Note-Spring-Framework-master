<!-- 모바일웹 Photo Blog -->

<%@ page contentType="text/html; charset=EUC-KR"%>
<%
		String msg = (String)session.getAttribute("msg");
		// PBlogLogServlet 서블릿에서 로그인 실패시 저장한 메시지를 가져옴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- meta viewport 태그는 애플이 아이폰, 아이패드 등 자사의 모바일 브라우저의 뷰 포트 (viewport) 크기 조절을 위해 만듦 
(W3C 명세에는 없기 때문에 표준은 아님)
그러나 iOS 장치 (아이폰 운영체제 브라우저 safari)가 널리 사용됨에 따라 사실상 표준처럼 사용되고 있고
다른 부라우저 (Opera, Android, Mobile firefox (Fennec) 등)도 이 태그를 채택하게 됨

- width=device-width: 웹 페이지의 크기가 모니터 실제 크기를 따라가도록 만든 설정으로 
모니터, 스마트폰 등의 화면에 맞느 비율로 화면이 뜨도록 도움

- initial-scale=1: 화면의 줌 정도를 1배열로 한다는 뜻 
(이 값을 크게 키우면 화면이 줌이 되어 크게 보임, 스마트폰에서만 효과가 있음)
-->
<title>PhotoBlog</title>
<%@include file="js_css.html"%>
<script type="text/javascript">
 		function login() {
			document.frm.submit();
		}
 	</script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<table>
				<tr>
					<td align="left" width="200">
						<h1 style="font-family: fantasy;" align="left">&nbsp;&nbsp;PhotoBlog</h1>
					</td>
				</tr>
			</table>
		</div>
		<div data-role="content">
			<h1>LOGIN</h1>
	
			<!-- id와 pwd 입력하고 PBlogLoginServlet 서블릿에서 데이터베이스와 연동하여 로그인 처리
			PBlogLoginServlet 서블릿에서 로그인 성공하면 자동적으로 포토블로그 페이지 03_home.jsp 페이지로 이동
			03_home.jsp 페이지는 포트포스트 리스트 및 포토리스트 삭제 
			
			그리고 로그인 사용자를 제외하고 랜덤한 다섯 명의 포토블로그 (05_guest.jsp)에 접속할 수 있는 기능 제공 페이지
			03_home.jsp에서 상단 오른쪽에 있는 P를 클릭하면 04_post.jsp로 이동 -->
			
			<!-- 로그인을 위한 id, pwd을 위한 form 
			form의 메소드가 post이기 때문에 PBlogLoginServlet.java 서블릿에서 처리되는 메소드 doPost()
			action값은 PBlogLoginServlet.java 서블릿의 URL 맵핑 값으로 선언된 값이므로
			결과적으로 로그인 처리는 PBlogLoginServlet.java 서블릿에서 담당 -->
			
			<form method="post" name="frm" action="pBlogLogin">
			<!-- 로그인에 필요한 id와 pwd를 입력받기 위한 form을 선언 -->
				<p>
					<label for="user_id">아이디: <input name="id">
					<!-- id 값을 입력받기 위한 input 태그를 선언
					input 태그 type의 기본값은 text (생략 가능) -->
					</label>

				</p>
				<p>
					<label for="user_password">비밀번호: <input type="password" name="pwd">
					<!-- pwd 값을 입력받기 위한 input 태그 선언 -->
					</label>
				</p>
				<input type="button" value="로그인" onclick="login()">
			</form>
		</div>
		
		<!-- 로그인 실패시 보이는 메시지 -->
		<!-- 처음 로그인 실행을 시도한다면 보이지 않는 메시지
		PBlogLoginServlet.java 서블릿에서 로그인 실패를 하면 
		세션에 msg를 저장하기 때문에 로그인 실패를 했을 때만 보이는 메시지 -->
		<%if(msg != null){%>
		<div align="center">
			<font color="red"><%=msg%></font>
		</div>
		<%}%>
		
		<!-- 포토블로그의 모든 화면의 하단을 담당하는 06_footer.jsp를 include 지시자로 가져옴 -->
		<%@include file="06_footer.jsp"%>
	</div>
</body>
</html>