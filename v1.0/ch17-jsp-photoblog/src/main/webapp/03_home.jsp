<%-- 03_home.jsp에서는 포토 하단의 [DEL]을 클릭하면 포토포스트와 관련된 댓글이 함께 삭제됨
삭제 기능은 PBlogDeleteServlet 서블릿에서 처리함
여기까지가 첫번째 단계에서 구현할 흐름 --%>

<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="java.util.Vector, ex03.*, ex01.*, ex04.*, ex02.*, ex05.*"%>

<!-- 포토포스트 및 댓글에 필요한 PBlogMgr과 PReplyMgr 객체 생성 -->
<jsp:useBean id="pmgr" class="ex01.PBlogMgr"/>
<jsp:useBean id="rmgr" class="ex02.PReplyMgr"/>
<%
		// 세션에서 id를 가져옴
		String id = (String)session.getAttribute("idKey");

		// 세션에서 가져온 id값이 null이면 01_login.jsp로 이동
		if(id == null) response.sendRedirect("01_login.jsp");
		
		// id를 매개변수로 getPMember()를 호출하여 로그인 사용자의 정보를 가져옴
		PMemberBean mbean = pmgr.getPMember(id);
		
		// 로그인 사용자를 제외한 5명의 랜덤한 회원 리스트를 Vector 타입으로 가져옴
		Vector<PMemberBean> mvlist = pmgr.listPMember(id);
		
		// 로그인 사용자의 포토포스트 리스트를 Vector 타입으로 가져옴
		Vector<PBlogBean> pvlist = pmgr.listPBlog(id);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>PhotoBlog</title>
	<%@include file="js_css.html" %>
	<script type="text/javascript">
	
		// frm 폼에 삭제하고 싶은 포토포스트의 num을 지정하고
		// pBlogDelete로 URL 맵핑 값으로 지정한 PBlogDeleteServlet 서블릿으로 호출하는 자바스크립트 함수
		function del(num) {
			document.frm.action = "pBlogDelete";
			document.frm.num.value=num;
			document.frm.submit();
		}
		
		// frm 폼에 삭제하고 싶은 포토포스트 댓글을 rnum을 지정하고
		// pReplyDelete로 URL 맵핑 값으로 지정한 PReplyDeleteServlet 서블릿을 호출하는 자바스크립트 함수
		function rDel(rnum) {
			document.frm.action = "pReplyDelete";
			document.frm.rnum.value=rnum;
			document.frm.submit();
		}
		
		// frm 폼에 '좋아요' 카운트를 증가하고 싶은 포토포스트 num을 지정하고
		// pBlogUpHCnt로 URL 맵핑 값으로 지정한 PBlogHCntServlet 서블릿을 호출하는 자바스크립트 함수
		function heart(num) {
			document.frm.action = "pBlogUpHCnt";
			document.frm.num.value=num;
			document.frm.submit();
		}
		
		// frm 폼에 댓글 입력하고 싶은 포토포스트 num을 지정하고 
		// pReplyPost로 URL 맵핑 값으로 지정한 PReplyPostServlet 서블릿을 호출하는 자바스크립트 함수
		// id값은 comment + num 값으로 동적으로 id값이 만들어짐
		
		// 03_home.jsp 페이지에 다수개의 포토포스트가 존재하기 때문에 id값을 동적으로 만들고
		// 댓글로 입력한 comment 값을 가져올 수가 있음
		// 만약 입력폼이 전체 페이지에 정적으로 존재한다면 이런 기능이 필요가 없지만 동적 입력폼이 존재하면 이런 기능으로 구현해야 함
		function cmtPost(num) {
			document.frm.action = "pReplyPost";
			cmt = document.getElementById("comments"+num);
			document.frm.comments.value=cmt.value;
			document.frm.num.value=num;
			document.frm.id.value="<%=id%>";
			document.frm.submit();
		}
		
		// frm1 폼에 url과 gid 값을 지정하고 지정된 url 값으로 gid 값과 같이 요청하여 이동함
		// gid 값은 상황에 따라 있을 수도 있고 없을 수도 있음
		function goURL(url, gid) {
			document.frm1.action=url;
			document.frm1.gid.value=gid;
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
					<h1 style="font-family: fantasy;" align="left">PhotoBlog</h1></td>
				<td>
					<a style="font-size: 40px;" href="javascript:goURL('03_home.jsp','')">H</a>
					<a style="font-size: 40px;" href="javascript:goURL('04_post.jsp','')">P</a>
				</td>
				<td>	
					<div class="box" style="background: #BDBDBD;">
					
						<!-- 로그인 id의 프로필 포토가 보이고 포토를 클릭하면 로그아웃 페이지로 이동 -->
						<img  align="bottom" class="profile" src="./photo/<%=mbean.getProfile()%>" width="30" height="30" onclick="location='02_logout.jsp'">
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 로그인 사용자를 제외한 전체 회원 중에 5명을 랜덤하게 선택하여 프로필 포토와 이름 출력
	프로필 포토를 클릭하면 클릭한 사람의 포토블로그 페이지 05_guest.jsp로 이동 -->
	<div data-role="content">
	<table>
		<tr>
		<%
				for(int i = 0; i < mvlist.size(); i++){
					PMemberBean mvbean = mvlist.get(i);
		%>
			<td width="80">
				<div class="box1" style="background: #BDBDBD;">
				<!-- 포토필 포토를 클릭하면 클릭산 하용자의 포토 블로그로 이동함 -->
					<a href="javascript:goURL('05_guest.jsp','<%=mvbean.getId()%>')">
						<img class="profile1" src="./photo/<%=mvbean.getProfile()%>" width="30" height="30">
					</a>
				</div>
				<div>
					<h4><%=mvbean.getName()%></h4>
				</div>
			</td>
		<%}%>	
		</tr>
	</table>
	
	<!-- 로그인 사용자의 포토블로그를 보여주는 영역
	프로필 포토 및 id 그리고 포토포스트 및 메시지를 보여주고 만약 댓글이 있으면 댓글 리스트를 보여줌 -->
	<table>
		<%
				for(int i = 0; i < pvlist.size(); i++){
					PBlogBean pbean = pvlist.get(i);
					PMemberBean tmbean = pmgr.getPMember(pbean.getId());
		%>
		<tr>
			<td width="30">
				<div class="box" style="background: #BDBDBD;">
					<img class="profile" src="photo/<%=tmbean.getProfile()%>" width="30" height="30">
				</div>
			</td>
			<td width="250"><b><%=tmbean.getId()%></b></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3">
				<img src="photo/<%=pbean.getPhoto()%>" width="350" height="250">
			</td>
		</tr>
		<tr>
			<td colspan="3"><b><%=pbean.getMessage()%></b></td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="javascript:heart('<%=pbean.getNum()%>')">
			
			<!-- 하트 모양의 아이콘이 클릭하면 포토포스트의 '좋아요' 카운트 증가 -->
			<img src="img/heart.jpg" align="top"></a> 좋아요 <%=pbean.getHcnt() %>개</td>
			<!-- [DEL]를 클릭하면 포토포스트 삭제 -->
			<td align="center"><a href="javascript:del('<%=pbean.getNum()%>')">DEL</a></td>
		</tr>
		<tr>
			<td colspan="3" width="200">
				<%
						// 매개변수는 포토포스트의 num 값으로 댓글 리스트를 Vector로 리턴
						Vector<PReplyBean> rvlist = rmgr.listPReply(pbean.getNum());
						for(int j=0; j < rvlist.size(); j++){
							PReplyBean rbean = rvlist.get(j);
				%>
				
				<!-- 댓글을 입력자가 로그인한 사용자와 같은 id라면 댓글을 삭자할 수 있는 [x] 모양이 활성화됨 -->
				<b><%=rbean.getId()%></b> <%=rbean.getComments()%>&nbsp;
				<%if(id.equals(rbean.getId())){%><a href="javascript:rDel('<%=rbean.getRnum()%>')">x</a><%}%><br>			
				<%}%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 댓글을 입력하는 input 태그 -->
				<input id="comments<%=pbean.getNum()%>" placeholder="댓글달기..." size="50">
			</td>
			<td align="center">
				<a href="javascript:cmtPost('<%=pbean.getNum()%>')">게시</a>
			</td>
		</tr>
		<tr>
			<td colspan="3"><br></td>
		</tr>
		<%}%>
	</table>
	</div>
	
	<!-- frm, frm1의 폼은 자바스크립트 함수에서 사용을 하기 위해 선언된 폼이고
	화면에는 보이지 않는 hidden 타입으로 전부 선언함 -->
	<form method="post" name="frm">
		<input type="hidden" name="num">
		<input type="hidden" name="comments">
		<input type="hidden" name="rnum">
		<input type="hidden" name="id">
	</form>
	<form method="post" name="frm1" action="03_home.jsp">
		<input type="hidden" name="gid">
	</form>
	
	<!-- 하단 부분의 페이지 06_footer.jsp 페이지 지시자로 가져옴 -->
	<%@include file="06_footer.jsp" %>
</div>
</body>
</html>