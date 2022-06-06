function getXMLHttpRequest() {
	// 브라우저가 IE 6.0이하일 경우
	if (window.ActiveXObject) return new ActiveXObject("Microsoft.XMLHTTP"); 
	// IE 7.0 이상과 나머지 브라우저
	else return new XMLHttpRequest();
}


/*
	HTTP 통신에서 클라이언트와 서버 사이에 데이터를 주고 받을 때 
	현재 예제에서는 클라이언트 (index.jsp, index.js)가 되고 서버는 (UsersController.java)가 됨
	
	GET, POST 방식
	1) GET: query string이 헤더에 노출 (+ 헤더에 노출되므로 query string의 길이 제한이 있음, 보안상 위험)
	2) POST: query string이 헤더에 노출되지 않음
	
	Content-Type을 통해 Header에 전달할 데이터의 타입을 지정할 수 있음
	(application/x-www-form-urlencoded (기본 방식), multipart/form-data, text/plain, text/javascript ...) 
	1) application/x-www-form-urlencoded: 모든 문자들은 서버로 보내기 전에 퍼센트 인코딩 됨 (영문자, 숫자 등 제외한 값을 16진수로 인코딩)
	2) multipart/form-data: 모든 문자를 인코딩하지 않음을 명시 (파일의 이름은 인코딩하고 나머지 내용물은 인코딩하지 않음)
	3) text/plain: 공백 문자만 +로 변환하고 나머지 문자는 모두 인코딩되지 않음을 명시함
	
	============================================================================================================
	ajax를 통해서 데이터를 전달할 때 application/x-www-form-urlencoded이 기본값인 이유 ?
	- query string을 만들 때 &, =과 같은 특수기호는 구분자 역할을 하는 특별한 의미를 가진 특수문자이기 때문에 이를 그대로 url로 사용할 수 없음
	- url는 기본적으로 US-ASCII 코드만 이용이 가능 (숫자 (0-9), 영문자 (A-Z, a-z), 일부 특수기호 ("-", ".", "_", "~"))
	- 만약 url를 구성하는 문자 중에 아스키코드로 변환이 불가능한 다른 언어의 문자인 경우 url이 인식할 수 없으므로 동일한 방법을 이용해서 인코딩 함
	
	어떻게 브라우저가 인식할까 ?
	- 브라우저는 기본적으로 urlencoded된 url이 들어오면 해당 url을 디코딩된 상태로 보여줌
	
	그러나!! 요새는 application/x-www-form-urlencoded보다는 json 형태로 전달하는 것을 선호함 (post 방식에서)
	(rest api에서는 json 형태로 보냄)
	============================================================================================================
	
	- searchFunction:
		- 사용자가 검색하기 위해 입력한 keyword를 통해 해당 keyword를 userName에 포함하고 있는 데이터를 DB에서 검색해서 가지고 옴
		- get 방식으로 keyword 전달
		- get 방식으로 전달할 경우 query string으로 담아서 가는데 헤더에 노출된 채로 넘어감
		- localhost:8000/mvc-ajaxse?key1=value1&key2=value2.....
		- request.send(null) (또는 request.send())
		
	- registerFunction:
		- 사용자가 입력한 폼 데이터를 DB에 저장
		- post 방식으로 사용자가 입력한 데이터를 전달
		- post 방식으로 전달할 경우 동일하게 query string으로 담아서 가는데 헤더에 노출된 채로 넘어가는 게 아니고 body에 포함시킴
		- localhost:8000/mvc-ajaxse (url은 그대로)
		- body: key1=value1&key2=value2.....
		- request.send(queryString) (body에 전달하기 위해)
		
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////// [SEARCH FORM] /////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
function onclickSearchForm() { // 클릭 이벤트 콜백 함수
	$("#searchBtn").click(() => {
		//if(isValidSearchForm()) { searchFunction(); } // @ExceptionHandler 체크를 위해 이벤트 등록 해제
		searchFunction(); 
	}) 
	$("#userName").keyup(() => {
		searchFunction(); 
	}) 
}


function searchFunction() { // 비동기 통신 함수
	const searchRequest = getXMLHttpRequest();
	const url = "./search";
	const queryString = "userName=" + encodeURIComponent($("#userName").val());
	searchRequest.open("get", url + "?" + queryString, true);
	//searchRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// 지정하지 않아도 url에 노출되어있어서 상관 없음
	
	searchRequest.onreadystatechange = () => {
		if (searchRequest.readyState == 4 && searchRequest.status == 200) {
			// 검색된 결과를 table로 출력 (ajaxTable)
			const table = $("#ajaxTable")[0]; // dom 반환
			table.innerHTML = "";
		
			const object = JSON.parse(searchRequest.responseText);
			for (let i = 0; i < object.length; i++) {
				const user = [object[i].userName, object[i].userAge, object[i].userGender, object[i].userEmail]
				const row = table.insertRow(0); // 0번째 행에 추가 (tr)

				for (let j = 0; j < user.length; j++) {
					const cell = row.insertCell(j); // j번째 열 추가 (td)
					cell.innerHTML = user[j];
				}
			}
		
		}
	};
	searchRequest.send(null);

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////// [REGISTER FORM] /////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
function onclickRegisterForm() { // 클릭 이벤트 콜백 함수
	$("#registerBtn").click(() => {
		//if(isValidRegisterForm()) { registerFunction(); } // @ExceptionHandler 체크를 위해 이벤트 등록 해제
		registerFunction();
	}) 
}

function registerFunction() { // 비동기 통신 함수
	const registerRequest = getXMLHttpRequest();
	const url = "./register";
	const age = $("#registerAge").val();	
	const queryString = "userName=" + encodeURIComponent($("#registerName").val())
		+ "&userAge=" + encodeURIComponent(parseInt($("#registerAge").val()))
		+ "&userGender=" + encodeURIComponent($("input[name=registerGender]:checked").val())
		+ "&userEmail=" + encodeURIComponent($("#registerEmail").val());
	const queryStringJson = {"userName" :$("#registerName").val(),
							"userAge"   : parseInt($("#registerAge").val()),
							"userGender":$("input[name=registerGender]:checked").val(),
							"userEmail" :$("#registerEmail").val()};
	registerRequest.open("post", url, true);
		
	// 서버 (UsersController.java)로 데이터를 보내기 전에 데이터에 대한 타입을 지정해줌
	// POST 방식은 body에 포함해서 가기 때문에 queryString으로 작성된 부분을 url로 바로 전달하는 게 아니라
	// registerRequest.send(queryString)으로 전달
	//registerRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	registerRequest.setRequestHeader("Content-Type", "application/json");
	
	registerRequest.onreadystatechange = () => {
			if (registerRequest.readyState == 4 && registerRequest.status == 200) {
				const result = registerRequest.responseText; 
					
				if (result == "register-fail") {
					alert("등록 실패했습니다.");
				}
				else {
					
					// 등록된 사람이 search 결과 테이블에 포함되도록 (view에서)
					const userName = $("#userName");
					const registerName = $("#registerName");
					const registerAge = $("#registerAge");
					const registerEmail = $("#registerEmail");
	
					userName.val(""); // select * (keyword가 빈문자열)
	
					// 입력 폼의 데이터를 초기화
					registerName.val("");
					registerAge.val("");
					$("#registerGenderMale").prop("checked", true);
					$("#registerGenderFemale").prop("checked", false);
					registerEmail.val("");
	
		
					searchFunction();
				} 
			}
		} 
		registerRequest.send(JSON.stringify(queryStringJson));
		//registerRequest.send(queryString);
	};



window.onload = () => { searchFunction(); }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////// [REGISTER FORM] /////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

function isValidRegisterForm() { // 데이터 포맷 확인
	const registerName   = $("#registerName");
	const registerAge    = $("#registerAge");
	const registerGender = $("input[name=registerGender]:checked");
	const registerEmail  = $("#registerEmail");
	
	if(!isValidName(registerName.val())) {
		registerName.focus(); return false;
	} else if(!isValidAge(registerAge.val())) {
		registerAge.focus(); return false;
	} else if(!isValidGender(registerGender.val())) {
		registerGender.focus(); return false;
	} else if(!isValidEmail(registerEmail.val())) {
		registerEmail.focus(); return false;
	}
	
	return true;
}
function isValidName(name) {
	if (name == "") { alert("NAME must be filled out"); return false; }
	return true;
}


function isValidAge(age) {
	if (age == "") { alert("AGE must be filled out"); return false; }
	else if (isNaN(parseInt(age))) { alert("AGE must be input number!"); return false; }
	else if (!(parseInt(age) >= 0 && parseInt(age) <= 200)) { alert("AGE mismatch regex!"); return false; }
	return true;
}

function isValidGender(gender) {
	if (gender == "") { alert("GENDER must be filled out"); return false; }
	else if (!(gender == "남자" || gender == "여자")) { alert("GENDER value is not valid!"); return false; }
	return true;
}


function isValidEmail(email) {
	if (email == "") { alert("EMAIL must be filled out"); return false; }
	else if (!(/^[A-Za-z0-9\-\_\.]+\@[A-Za-z0-9\-]+(\.[A-Za-z]{2,3}){1,2}$/g.test(email))) { alert("EMAIL mismatch regex!"); return false; }
	return true;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////// [REGISTER FORM] /////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
function isValidSearchForm() { // 데이터 포맷 확인
	const registerName = $("#userName");
	if(registerName.val() == "") {
		alert("NAME must be filled out"); 
		registerName.focus();
		return false;
	} 
	
	return true;
}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////// [이벤트 등록] ///////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
onclickRegisterForm();
onclickSearchForm();

