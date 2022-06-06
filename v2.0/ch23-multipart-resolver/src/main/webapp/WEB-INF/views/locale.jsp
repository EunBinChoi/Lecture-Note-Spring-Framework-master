<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Locale</title>
</head>
<body>
	<div style="margin: 10px 0px">
		<form name="locale" method="get" action="${cp}/">
			<select id="lang" name="lang" style="padding: 5px">
				<option value="ko">Korean</option>
				<option value="en">English</option>
				<option value="ja">Japanese</option>
				<option value="" selected>No Selected</option>
			</select>
			<input type="submit" value="OK">
		</form>
	</div>
</body>
</html>