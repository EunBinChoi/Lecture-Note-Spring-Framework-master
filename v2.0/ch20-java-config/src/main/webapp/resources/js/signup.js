function isSignUpValidForm() {
	if (!(isValidID($('#id').val()))) {
		$('#id').focus();
		return false;
	}
	else if (!(isValidPass($('#curpwd').val()))) {
		$('#curpwd').focus();
		return false;
	}
	else if (!(isValidRePass($('#curpwd').val(), $('#currepwd').val()))) {
		$('#currepwd').focus();
		return false;
	}
	else if (!(isValidName($('#name').val()))) {
		$('#name').focus();
		return false;
	}
	else if (!(isValidEmail($('#email').val()))) {
		$('#email').focus();
		return false;
	}
	else if (!(isValidPhone($('#phone').val()))) {
		$('#phone').focus();
		return false;
	}
	return true;

}

function isValidID(id) {
	if (id == "") { alert("ID must be filled out"); return false; }
	return true;
}

function isValidPass(pass) {
	if (pass == "") { alert("PASSWORD must be filled out"); return false; }
	return true;

}

function isValidRePass(pwd, repwd) {
	if (repwd == "") { alert("PASSWORD REWRITE must be filled out"); return false; }
	else if(pwd != repwd) {
		alert("PASSWORD REWRITE does not match PASSWORD"); return false;
	}
	return true;
}

function isValidName(name) {
	// 조건
	// 1) 이름을 무조건 입력
	if (name == "") { alert("NAME must be filled out"); return false; }
	return true;
}

function isValidEmail(email) {
	if (email == "") { alert("EMAIL must be filled out"); return false; }
	// regex
	else if (!(/^[A-Za-z0-9\-\_\.]+\@[A-Za-z0-9\-]+(\.[A-Za-z]{2,3}){1,2}$/g.test(email))) {
		alert("EMAIL mismatch regex!");
		return false;
	}
	return true;
}

function isValidPhone(phone) {
	if (phone == "") { alert("PHONE must be filled out"); return false; }

	// regex
	else if (!(/^\d{2,3}-\d{3,4}-\d{4}$/g.test(phone))) {
		alert("PHONE mismatch regex!");
		return false;
	}
	return true;
}

////////////////////////////////////////////////////////////////////////
// HTTP 서버 (Controller)와 클라이언트 (JSP) 사이에 통신이 일어나는 경우
// 통신 방법: GET: query string 사용   (헤더 노출)
//          POST: query string 사용 (바디로 전달)
// query string을 만드는 방법: localhost:8000/cp/key1=value1&key2=value2

// Content-Type을 통해 header에 전달할 데이터의 타입을 지정할 수 있음
// 1) application/x-www-form-urlencoded (기본값)
// : url은 기본적으로 ASCII 코드만 인식 가능 (영문자, 숫자, ., -, _, ~)
// : url이 인식불가능한 문자 (영문자를 제외한 다른 언어, 특수기호)를 16진수로 encoding에서 전달
// : query string은 구분자 역할하는 특별한 의미를 가진 &, = 인식하기 위해 encoding에서 전달
// : cf) encoding 방식: percent encoding

// 2) multipart/form-data
// : 대용량 파일 첨부
// : 파일의 이름은 인코딩하고 파일의 내용물은 인코딩하지 않음을 명시

// 3) application/json (* 요새 사용하는 방법)
// 4) text/plain 
// : 공백 문자만 +로 변환하고 나머지는 인코딩하지 않음 명시
///////////////////////////////////////////////////////////////////////////
// (*) application/x-www-form-urlencoded된 데이터를 브라우저에서 어떻게 인식할까?
// - 브라우저에서는 기본적으로 urlencoded된 url이 들어오면 url을 디코딩된 상태로 보여줌
///////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////
////////////////////// 비동기 통신 함수 //////////////////////////
function getXMLHttpRequest() {
	// 브라우저가 IE 6.0이하일 경우
	if (window.ActiveXObject) return new ActiveXObject("Microsoft.XMLHTTP"); 
	// IE 7.0 이상과 나머지 브라우저
	else return new XMLHttpRequest();
}


function checkDuplicateId() {	
	const checkIdRequest = getXMLHttpRequest();
	const url = "./checkDuplicateId"; // request mapping
	const queryString = "id=" + encodeURIComponent($("#id").val()); 
	const method = "get";
	
	checkIdRequest.open(method, url + "?" + queryString, true);
	checkIdRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 기본값
	
	checkIdRequest.onreadystatechange = () => {
		if(checkIdRequest.readyState == 4 && checkIdRequest.status == 200) {		
			const result = $("#checkDuplicateResult");		
			
			result.css("height",   "5px");
			result.css("margin",   "0px");
			result.css("padding",  "0px");
			result.css("font-weight",    "bold");	
			
			if(checkIdRequest.responseText == "duplicate") {
				result.css("display",  "block");
				result.css("color",    "red");	
				result.text("중복된 아이디입니다.");
			} else if(checkIdRequest.responseText == "non-duplicate") {
				result.css("display",  "block");
				result.css("color",    "black");
				result.text("사용 가능한 아이디입니다.");
			}  else {
				result.css("display",  "none");
			}
		}
	}
	checkIdRequest.send(null); // body에 전송되는 값이 없음 (get 방식)
}

///////////////////////////////////////////////////////////////
//////////////////////// 이벤트 등록 ////////////////////////////
$('#signup').submit(() => { 
	//const isSignUpValid = isSignUpValidForm();
	//return isSignUpValid;
	return true;
});

$('#id').keyup(() => { 
	checkDuplicateId();
});

$('#duplicateCheckBtn').click(() => { 
	checkDuplicateId();
});
