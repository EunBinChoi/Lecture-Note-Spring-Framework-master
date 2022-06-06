<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="ex01.*, ex02.*, ex03.*, ex04.*, ex05.*"%>
<jsp:useBean id="pmgr" class="ex01.PBlogMgr" />
<%-- 04_post.jsp는 스마트폰에 저장되어 있는 이미지 및 촬영을 통한 포토를 메시지와 함께 전송하는 기능의 페이지
전송된 포토와 메시지는 PBlogPostServlet 서블릿에서 저장 기능을 처리하고 자동으로 03_home.jsp로 이동 --%>


<%
	String id = (String) session.getAttribute("idKey");

	// session에서 가져온 id가 null이면 로그인 페이지로 이동
	if (id == null) response.sendRedirect("01_login.jsp");
	PMemberBean mbean = pmgr.getPMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>PhotoBlog</title>
<%@include file="js_css.html"%>
<script type="text/javascript">
	function send() {
		document.frm.submit();
	}
	function goURL(url) {
		document.frm1.action = url;
		document.frm1.submit();
	}
</script>
</head>
<body>
	<div data-role="page" align="center">
		<div data-role="header">
			<table>
				<tr>
					<td align="left" width="200">
						<h1 style="font-family: fantasy;" align="left">PhotoBlog</h1>
					</td>
					
					<!-- [H]를 클릭하면 03_home.jsp로 이동 -->
					<td><a style="font-size: 40px;"
						href="javascript:goURL('03_home.jsp','')">H</a> <a
						style="font-size: 40px;" href="javascript:goURL('04_post.jsp','')">P</a>
					</td>
					
					<!-- 포토필 포토를 클릭하면 로그아웃 페이지로 이동 -->
					<td>
						<div class="box" style="background: #BDBDBD;">
							<img class="profile" src="photo/<%=mbean.getProfile()%>" width="30"
								height="30" onclick="location='02_logout.jsp'">
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div data-role="content">
			<!-- 파일 업로드 기능을 위해서는 반드시 method는 post 방식이고
			enctype을 multipart/form-data로 선언해야 함 -->
			<form method="post" name="frm" action="pBlogPost"
				enctype="multipart/form-data" class="post_form">
				<div class="preview">
					<div class="upload">
						<div class="post_btn">
							<div class="plus_icon"></div>
							<p>포스트 이미지 추가</p>
							
							<!-- 선택한 포토가 웹 서버 (Tomcat)로 업로드되기 전에 
							미리보기 기능을 위해 선언한 canvas 태그 -->
							<canvas id="imageCanvas"></canvas>
						</div>
					</div>
				</div>
				<p>
				
					<!-- 포토가 저장되기 위해서 타입은 file로 선언하고
					반드시 요청되기 위한 옵션으로 required으로 선언  -->
					<input type="file" name="photo" id="id_photo" required="required">
				</p>
				<p>
					<!-- textarea 태그는 포토포스트에 포토와 간단히 메시지를 저장 -->
					<textarea name="message" cols="50" rows="5"
						placeholder="message 등록해 주세요."></textarea>
				</p>
				
				<!-- 로그인 id가 포토포스트에 저장하기 위해 hidden으로 선언함 -->
				<input type="hidden" value="<%=id%>" name="id"> 
				<input type="button" value="저장" onclick="send()">
			</form>
		</div>
		<form method="post" name="frm1"></form>
		<%@include file="06_footer.jsp"%>
	</div>
	<script>
	<!-- 파일 선택하면 해당 선택된 파일이 포스트 이미지 부분에 보임 -->
		var fileInput = document.querySelector("#id_photo"), 
		button = document.querySelector(".input-file-trigger"), 
		the_return = document.querySelector(".file-return");
		
		// Show image
		fileInput.addEventListener('change', handleImage, false);
		var canvas = document.getElementById('imageCanvas');
		var ctx = canvas.getContext('2d');

		function handleImage(e) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = new Image();
				img.onload = function() {
					canvas.width = 300;
					canvas.height = 300;
					ctx.drawImage(img, 0, 0, 300, 300);
				};
				img.src = event.target.result;
			};
			
			// 포스트 이미지 부분에 보이고자 할 때 서버에 저장된 이미지 url 주소로 로드 
			reader.readAsDataURL(e.target.files[0]);
		}
	</script>
</body>
</html>