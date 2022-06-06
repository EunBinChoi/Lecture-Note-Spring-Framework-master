<%-- 03_home.jsp������ ���� �ϴ��� [DEL]�� Ŭ���ϸ� ��������Ʈ�� ���õ� ����� �Բ� ������
���� ����� PBlogDeleteServlet �������� ó����
��������� ù��° �ܰ迡�� ������ �帧 --%>

<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="java.util.Vector, ex03.*, ex01.*, ex04.*, ex02.*, ex05.*"%>

<!-- ��������Ʈ �� ��ۿ� �ʿ��� PBlogMgr�� PReplyMgr ��ü ���� -->
<jsp:useBean id="pmgr" class="ex01.PBlogMgr"/>
<jsp:useBean id="rmgr" class="ex02.PReplyMgr"/>
<%
		// ���ǿ��� id�� ������
		String id = (String)session.getAttribute("idKey");

		// ���ǿ��� ������ id���� null�̸� 01_login.jsp�� �̵�
		if(id == null) response.sendRedirect("01_login.jsp");
		
		// id�� �Ű������� getPMember()�� ȣ���Ͽ� �α��� ������� ������ ������
		PMemberBean mbean = pmgr.getPMember(id);
		
		// �α��� ����ڸ� ������ 5���� ������ ȸ�� ����Ʈ�� Vector Ÿ������ ������
		Vector<PMemberBean> mvlist = pmgr.listPMember(id);
		
		// �α��� ������� ��������Ʈ ����Ʈ�� Vector Ÿ������ ������
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
	
		// frm ���� �����ϰ� ���� ��������Ʈ�� num�� �����ϰ�
		// pBlogDelete�� URL ���� ������ ������ PBlogDeleteServlet �������� ȣ���ϴ� �ڹٽ�ũ��Ʈ �Լ�
		function del(num) {
			document.frm.action = "pBlogDelete";
			document.frm.num.value=num;
			document.frm.submit();
		}
		
		// frm ���� �����ϰ� ���� ��������Ʈ ����� rnum�� �����ϰ�
		// pReplyDelete�� URL ���� ������ ������ PReplyDeleteServlet ������ ȣ���ϴ� �ڹٽ�ũ��Ʈ �Լ�
		function rDel(rnum) {
			document.frm.action = "pReplyDelete";
			document.frm.rnum.value=rnum;
			document.frm.submit();
		}
		
		// frm ���� '���ƿ�' ī��Ʈ�� �����ϰ� ���� ��������Ʈ num�� �����ϰ�
		// pBlogUpHCnt�� URL ���� ������ ������ PBlogHCntServlet ������ ȣ���ϴ� �ڹٽ�ũ��Ʈ �Լ�
		function heart(num) {
			document.frm.action = "pBlogUpHCnt";
			document.frm.num.value=num;
			document.frm.submit();
		}
		
		// frm ���� ��� �Է��ϰ� ���� ��������Ʈ num�� �����ϰ� 
		// pReplyPost�� URL ���� ������ ������ PReplyPostServlet ������ ȣ���ϴ� �ڹٽ�ũ��Ʈ �Լ�
		// id���� comment + num ������ �������� id���� �������
		
		// 03_home.jsp �������� �ټ����� ��������Ʈ�� �����ϱ� ������ id���� �������� �����
		// ��۷� �Է��� comment ���� ������ ���� ����
		// ���� �Է����� ��ü �������� �������� �����Ѵٸ� �̷� ����� �ʿ䰡 ������ ���� �Է����� �����ϸ� �̷� ������� �����ؾ� ��
		function cmtPost(num) {
			document.frm.action = "pReplyPost";
			cmt = document.getElementById("comments"+num);
			document.frm.comments.value=cmt.value;
			document.frm.num.value=num;
			document.frm.id.value="<%=id%>";
			document.frm.submit();
		}
		
		// frm1 ���� url�� gid ���� �����ϰ� ������ url ������ gid ���� ���� ��û�Ͽ� �̵���
		// gid ���� ��Ȳ�� ���� ���� ���� �ְ� ���� ���� ����
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
					
						<!-- �α��� id�� ������ ���䰡 ���̰� ���並 Ŭ���ϸ� �α׾ƿ� �������� �̵� -->
						<img  align="bottom" class="profile" src="./photo/<%=mbean.getProfile()%>" width="30" height="30" onclick="location='02_logout.jsp'">
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- �α��� ����ڸ� ������ ��ü ȸ�� �߿� 5���� �����ϰ� �����Ͽ� ������ ����� �̸� ���
	������ ���並 Ŭ���ϸ� Ŭ���� ����� �����α� ������ 05_guest.jsp�� �̵� -->
	<div data-role="content">
	<table>
		<tr>
		<%
				for(int i = 0; i < mvlist.size(); i++){
					PMemberBean mvbean = mvlist.get(i);
		%>
			<td width="80">
				<div class="box1" style="background: #BDBDBD;">
				<!-- ������ ���並 Ŭ���ϸ� Ŭ���� �Ͽ����� ���� ��α׷� �̵��� -->
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
	
	<!-- �α��� ������� �����α׸� �����ִ� ����
	������ ���� �� id �׸��� ��������Ʈ �� �޽����� �����ְ� ���� ����� ������ ��� ����Ʈ�� ������ -->
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
			
			<!-- ��Ʈ ����� �������� Ŭ���ϸ� ��������Ʈ�� '���ƿ�' ī��Ʈ ���� -->
			<img src="img/heart.jpg" align="top"></a> ���ƿ� <%=pbean.getHcnt() %>��</td>
			<!-- [DEL]�� Ŭ���ϸ� ��������Ʈ ���� -->
			<td align="center"><a href="javascript:del('<%=pbean.getNum()%>')">DEL</a></td>
		</tr>
		<tr>
			<td colspan="3" width="200">
				<%
						// �Ű������� ��������Ʈ�� num ������ ��� ����Ʈ�� Vector�� ����
						Vector<PReplyBean> rvlist = rmgr.listPReply(pbean.getNum());
						for(int j=0; j < rvlist.size(); j++){
							PReplyBean rbean = rvlist.get(j);
				%>
				
				<!-- ����� �Է��ڰ� �α����� ����ڿ� ���� id��� ����� ������ �� �ִ� [x] ����� Ȱ��ȭ�� -->
				<b><%=rbean.getId()%></b> <%=rbean.getComments()%>&nbsp;
				<%if(id.equals(rbean.getId())){%><a href="javascript:rDel('<%=rbean.getRnum()%>')">x</a><%}%><br>			
				<%}%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- ����� �Է��ϴ� input �±� -->
				<input id="comments<%=pbean.getNum()%>" placeholder="��۴ޱ�..." size="50">
			</td>
			<td align="center">
				<a href="javascript:cmtPost('<%=pbean.getNum()%>')">�Խ�</a>
			</td>
		</tr>
		<tr>
			<td colspan="3"><br></td>
		</tr>
		<%}%>
	</table>
	</div>
	
	<!-- frm, frm1�� ���� �ڹٽ�ũ��Ʈ �Լ����� ����� �ϱ� ���� ����� ���̰�
	ȭ�鿡�� ������ �ʴ� hidden Ÿ������ ���� ������ -->
	<form method="post" name="frm">
		<input type="hidden" name="num">
		<input type="hidden" name="comments">
		<input type="hidden" name="rnum">
		<input type="hidden" name="id">
	</form>
	<form method="post" name="frm1" action="03_home.jsp">
		<input type="hidden" name="gid">
	</form>
	
	<!-- �ϴ� �κ��� ������ 06_footer.jsp ������ �����ڷ� ������ -->
	<%@include file="06_footer.jsp" %>
</div>
</body>
</html>