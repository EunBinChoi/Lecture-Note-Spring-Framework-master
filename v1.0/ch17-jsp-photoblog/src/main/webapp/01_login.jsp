<!-- ������� Photo Blog -->

<%@ page contentType="text/html; charset=EUC-KR"%>
<%
		String msg = (String)session.getAttribute("msg");
		// PBlogLogServlet �������� �α��� ���н� ������ �޽����� ������
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- meta viewport �±״� ������ ������, �����е� �� �ڻ��� ����� �������� �� ��Ʈ (viewport) ũ�� ������ ���� ���� 
(W3C ������ ���� ������ ǥ���� �ƴ�)
�׷��� iOS ��ġ (������ �ü�� ������ safari)�� �θ� ���ʿ� ���� ��ǻ� ǥ��ó�� ���ǰ� �ְ�
�ٸ� �ζ���� (Opera, Android, Mobile firefox (Fennec) ��)�� �� �±׸� ä���ϰ� ��

- width=device-width: �� �������� ũ�Ⱑ ����� ���� ũ�⸦ ���󰡵��� ���� �������� 
�����, ����Ʈ�� ���� ȭ�鿡 �´� ������ ȭ���� �ߵ��� ����

- initial-scale=1: ȭ���� �� ������ 1�迭�� �Ѵٴ� �� 
(�� ���� ũ�� Ű��� ȭ���� ���� �Ǿ� ũ�� ����, ����Ʈ�������� ȿ���� ����)
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
	
			<!-- id�� pwd �Է��ϰ� PBlogLoginServlet �������� �����ͺ��̽��� �����Ͽ� �α��� ó��
			PBlogLoginServlet �������� �α��� �����ϸ� �ڵ������� �����α� ������ 03_home.jsp �������� �̵�
			03_home.jsp �������� ��Ʈ����Ʈ ����Ʈ �� ���丮��Ʈ ���� 
			
			�׸��� �α��� ����ڸ� �����ϰ� ������ �ټ� ���� �����α� (05_guest.jsp)�� ������ �� �ִ� ��� ���� ������
			03_home.jsp���� ��� �����ʿ� �ִ� P�� Ŭ���ϸ� 04_post.jsp�� �̵� -->
			
			<!-- �α����� ���� id, pwd�� ���� form 
			form�� �޼ҵ尡 post�̱� ������ PBlogLoginServlet.java �������� ó���Ǵ� �޼ҵ� doPost()
			action���� PBlogLoginServlet.java ������ URL ���� ������ ����� ���̹Ƿ�
			��������� �α��� ó���� PBlogLoginServlet.java �������� ��� -->
			
			<form method="post" name="frm" action="pBlogLogin">
			<!-- �α��ο� �ʿ��� id�� pwd�� �Է¹ޱ� ���� form�� ���� -->
				<p>
					<label for="user_id">���̵�: <input name="id">
					<!-- id ���� �Է¹ޱ� ���� input �±׸� ����
					input �±� type�� �⺻���� text (���� ����) -->
					</label>

				</p>
				<p>
					<label for="user_password">��й�ȣ: <input type="password" name="pwd">
					<!-- pwd ���� �Է¹ޱ� ���� input �±� ���� -->
					</label>
				</p>
				<input type="button" value="�α���" onclick="login()">
			</form>
		</div>
		
		<!-- �α��� ���н� ���̴� �޽��� -->
		<!-- ó�� �α��� ������ �õ��Ѵٸ� ������ �ʴ� �޽���
		PBlogLoginServlet.java �������� �α��� ���и� �ϸ� 
		���ǿ� msg�� �����ϱ� ������ �α��� ���и� ���� ���� ���̴� �޽��� -->
		<%if(msg != null){%>
		<div align="center">
			<font color="red"><%=msg%></font>
		</div>
		<%}%>
		
		<!-- �����α��� ��� ȭ���� �ϴ��� ����ϴ� 06_footer.jsp�� include �����ڷ� ������ -->
		<%@include file="06_footer.jsp"%>
	</div>
</body>
</html>