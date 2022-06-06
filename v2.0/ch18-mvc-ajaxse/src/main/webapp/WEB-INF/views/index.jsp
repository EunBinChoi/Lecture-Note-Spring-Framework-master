<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC AJAX SEARCH ENGINE</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/index.js" defer></script>
</head>
<body>
<br>
<div class="container" style="margin:20px;">
	<div class="" style="margin:20px; text-align: right;">
		<input class=""  id="userName" type="text" size="20" maxlength="20">
		<button id="searchBtn" class="" type="button">검색</button>	
	</div>
	<table class="table" style="width: 500px; text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th style="padding:5px; background-color: #fafafa; text-align: center;">이름</th>
				<th style="padding:5px; background-color: #fafafa; text-align: center;">나이</th>
				<th style="padding:5px; background-color: #fafafa; text-align: center;">성별</th>
				<th style="padding:5px; background-color: #fafafa; text-align: center;">이메일</th>
			</tr>
		</thead>
		<tbody id="ajaxTable">
		</tbody>
	</table>
</div>
<div class="container" style="margin:20px;">
	<table class="table"  style="width: 500px; text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="2" style="background-color: #fafafa; text-align: center;">회원 등록 양식</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="background-color: #fafafa; text-align: center;"><h5>이름</h5></td>
				<td><input class="" type="text" id="registerName" size="20" maxlength="20"></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; text-align: center;"><h5>나이</h5></td>
				<td><input class="" type="text" id="registerAge" size="20" maxlength="20"></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; text-align: center;"><h5>성별</h5></td>
				<td>
					<div class="" style="text-align: center; margin:0 auto;">
						<div class="" data-toggle="buttons">
							<label class="">
								<input type="radio" class="registerGender" id="registerGenderMale" name="registerGender" autocomplete="off" value="남자" checked>남자
							</label>
							<label class="">
								<input type="radio" class="registerGender" id="registerGenderFemale" name="registerGender" autocomplete="off" value="여자">여자
							</label>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; text-align: center;"><h5>이메일</h5></td>
				<td><input class="" type="text" id="registerEmail" size="20" maxlength="20"></td>
			</tr>
			<tr>
				<td colspan="2"><button id="registerBtn" class="" type="button">등록</button></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>