<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="java.util.Vector, ex04.*, ex01.*, ex02.*, ex03.*, ex05.*"%>
<jsp:useBean id="pmgr" class="ex01.PBlogMgr" />
<jsp:useBean id="rmgr" class="ex02.PReplyMgr" />
<%
	String id = (String) session.getAttribute("idKey");
	String gid = request.getParameter("gid"); 
	// 03_home.jsp에서 선택한 게스트 (guest) id를 gid 변수로 받음
	
	
	/* 세션에서 가져온 id 값이 null이면 01_login.jsp로 이동하고
	request에서 가져온 gid 값이 null이면 03_home.jsp로 이동함
	
	기본적으로 web은 url이 오픈이 되어있는 프로그램이기 때문에 정상적으로 링크를 타고 들어오면
	이런 일은 없지만 정상적인 링크가 아닌 직접적인 url로 입력할 경우에 id와 gid가 null 값으로 리턴받을 수 있는 상황 고려*/
	if (id == null) response.sendRedirect("01_login.jsp");
	else if (gid == null) response.sendRedirect("03_home.jsp");
	PMemberBean mbean = pmgr.getPMember(id);
	// 로그인 사용자의 정보를 세션에 가져온 id의 매개변수로 리턴 받음
	Vector<PBlogBean> pvlist = pmgr.listPBlog(gid);
	// 게스트의 포토포스트 리스트를 Vector로 리턴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>PhotoBlog</title>
<%@include file="js_css.html"%>
<script type="text/javascript">
	function del(num) {
		document.frm.action = "pBlogDelete";
		document.frm.num.value=num;
		document.frm.submit();
	}
	function rDel(rnum) {
		document.frm.action = "pReplyDelete";
		document.frm.rnum.value=rnum;
		document.frm.gid.value="<%=gid%>";
		document.frm.submit();
	}
	function heart(num) {
		document.frm.action = "pBlogUpHCnt";
		document.frm.num.value=num;
		document.frm.gid.value="<%=gid%>";
		document.frm.submit();
	}
	function cmtPost(num) {
		document.frm.action = "pReplyPost";
		cmt = document.getElementById("comments"+num);
		document.frm.comments.value=cmt.value;
		document.frm.num.value=num;
		document.frm.id.value="<%=id%>";
		document.frm.gid.value="<%=gid%>";
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
					<td><a style="font-size: 40px;"
						href="javascript:goURL('03_home.jsp')">H</a> <a
						style="font-size: 40px;" href="javascript:goURL('04_post.jsp')">P</a>
					</td>
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
			<table>
				<%
					for (int i = 0; i < pvlist.size(); i++) {
						PBlogBean pbean = pvlist.get(i);
						PMemberBean tmbean = pmgr.getPMember(pbean.getId());
				%>
				<tr>
					<td width="30">
						<div class="box" style="background: #BDBDBD;">
							<img class="profile" src="photo/<%=tmbean.getProfile()%>"
								width="30" height="30">
						</div>
					</td>
					<td  width="250"><b><%=tmbean.getId()%></b></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<img src="photo/<%=pbean.getPhoto()%>" width="350" height="150">
					</td>
				</tr>
				<tr>
					<td colspan="3"><b><%=pbean.getMessage()%></b></td>
				</tr>
				<tr>
					<td colspan="2" width="250"><a
						href="javascript:heart('<%=pbean.getNum()%>')"> <img
							src="img/heart.jpg" align="top"></a> 좋아요 <%=pbean.getHcnt()%>개</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3" width="200">
						<%
							Vector<PReplyBean> rvlist = rmgr.listPReply(pbean.getNum());
								for (int j = 0; j < rvlist.size(); j++) {
									PReplyBean rbean = rvlist.get(j);
						%> 
						<b><%=rbean.getId()%></b> <%=rbean.getComments()%>&nbsp; 
							<%if (id.equals(rbean.getId())) {%>
								<a href="javascript:rDel('<%=rbean.getRnum()%>')">x</a>
							<%}%><br> 
						<%}%>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="comments<%=pbean.getNum()%>"
							placeholder="댓글달기..." size="50">
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
		<form method="post" name="frm">
			<input type="hidden" name="num"> 
			<input type="hidden" name="comments"> 
			<input type="hidden" name="rnum"> 
			<input type="hidden" name="id"> 
			<input type="hidden" name="gid">
		</form>
		<form method="post" name="frm1"></form>
		<%@include file="06_footer.jsp"%>
	</div>
</body>
</html>

