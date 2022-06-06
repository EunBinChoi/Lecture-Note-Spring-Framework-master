function isValidUpdateForm() {
	if (!(isValidID($("#id").val()))) {
		$("#id").focus();
		return false;
	}
	else if (!(isValidCurPass($("#curpwd").val()))) {
		$("#curpwd").focus();
		return false;
	}
	else if (!(isValidNewPass($("#newpwd").val()))) {
		$("#newpwd").focus();
		return false;
	}
	else if (!(isValidNewRePass($("#newpwd").val(), $("#newrepwd").val()))) {
		$("#newrepwd").focus();
		return false;
	}
	else if (!(isValidName($("#name").val()))) {
		$("#name").focus();
		return false;
	}
	else if (!(isValidEmail($("#email").val()))) {
		$("#email").focus();
		return false;
	}
	else if (!(isValidPhone($("#phone").val()))) {
		$("#phone").focus();
		return false;
	}
	return true;

}



function isValidID(id) {
	if (id == "") { alert("ID must be filled out"); return false; }
	return true;
}

function isValidCurPass(pwd) {
	if (pwd == "") { alert("PASSWORD must be filled out"); return false; }
	return true;
}


function isValidNewPass(newpwd) {
	if (newpwd == "") { alert("NEW PASSWORD must be filled out"); return false; }
	return true;

}

function isValidNewRePass(newpwd, newrepwd) {
	if (newrepwd == "") { alert("NEW PASSWORD REWRITE must be filled out"); return false; }
	else if(newpwd != newrepwd) {
		alert("NEW PASSWORD REWRITE does not match NEW PASSWORD"); return false;
	}
	return true;
}


function isValidName(name) {
	if (name == "") { alert("NAME must be filled out"); return false; }
	return true;
}

function isValidEmail(email) {
	if (email == "") { alert("EMAIL must be filled out"); return false; }
	else if (!(/^[A-Za-z0-9\-\_\.]+\@[A-Za-z0-9\-]+(\.[A-Za-z]{2,3}){1,2}$/g.test(email))) {
		alert("EMAIL mismatch regex!");
		return false;
	}
	return true;
}

function isValidPhone(phone) {
	if (phone == "") { alert("PHONE must be filled out"); return false; }
	else if (!(/^\d{2,3}-\d{3,4}-\d{4}$/g.test(phone))) {
		alert("PHONE mismatch regex!");
		return false;
	}
	return true;
}


///////////////////////////////////////////////////////////////
////////////////////// 비동기 통신 함수 //////////////////////////
function getXMLHttpRequest() {
	// 브라우저가 IE 6.0이하일 경우
	if (window.ActiveXObject) return new ActiveXObject("Microsoft.XMLHTTP"); 
	// IE 7.0 이상과 나머지 브라우저
	else return new XMLHttpRequest();
}


function checkIdPwd() {	
	const checkIdPwdRequest = getXMLHttpRequest();
	const url = "./checkIdPwd"; // request mapping
	const queryString = "id=" + encodeURIComponent($("#id").val())
						+ "&curpwd=" +  encodeURIComponent($("#curpwd").val());
	const queryStringJson = {"id":$("#id").val(), "curpwd":$("#curpwd").val()};
	const method = "post"; // curpwd가 헤더에 노출되지 않도록
	
	checkIdPwdRequest.open(method, url, true);
	//checkIdPwdRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 기본값
	checkIdPwdRequest.setRequestHeader("Content-Type", "application/json"); // 기본값
	
	checkIdPwdRequest.onreadystatechange = () => {
		if(checkIdPwdRequest.readyState == 4 && checkIdPwdRequest.status == 200) {		
			const result = $("#checkIdPwdResult");				
			result.css("height",   "5px");
			result.css("margin",   "0px");
			result.css("padding",  "0px");
			result.css("font-weight",    "bold");	
			
			if(checkIdPwdRequest.responseText == "present") {
				result.css("display",  "block");
				result.css("color",    "black");	
				result.text("ID/PWD가 일치합니다.");
			} else if(checkIdPwdRequest.responseText == "non-present") {
				result.css("display",  "block");
				result.css("color",    "red");
				result.text("ID/PWD가 일치하지 않습니다.");
			}  else  {
				result.css("display",  "none");
			}
		}
	}
	//checkIdPwdRequest.send(queryString); // post 방식
	checkIdPwdRequest.send(JSON.stringify(queryStringJson));
}


///////////////////////////////////////////////////////////////
//////////////////////// 이벤트 등록 ///////////////////////////
$('#update').submit(() => { 
	const isUpdateValid = isValidUpdateForm();
	return isUpdateValid;
	//return true;
});

$('#curpwd').keyup(() => { 
	checkIdPwd();
});

$('#checkIdPwdBtn').click(() => { 
	checkIdPwd();
});