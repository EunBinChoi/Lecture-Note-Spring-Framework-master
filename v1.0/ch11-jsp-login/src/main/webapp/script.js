function loginCheck() {
	if (document.loginFrm.id.value == "") {
		alert("아이디를 입력해 주세요.");
		document.loginFrm.id.focus();
		return;
	}
	if (document.loginFrm.pwd.value == "") {
		alert("비밀번호를 입력해 주세요.");
		document.loginFrm.pwd.focus();
		return;
	}
	document.loginFrm.submit();
}

function idCheck(id) {
	frm = document.regFrm;
	if (id == "") {
		alert("아이디를 입력해 주세요.");
		frm.id.focus();
		return;
	}
	url = "./06_idCheck.jsp?id=" + id;
	window.open(url, "IDCheck", "width=500,height=300");
}

function zipSearch() {
	url = "07_zipSearch.jsp?search=n";
	window.open(url, "ZipCodeSearch", "width=500,height=300, scrollbars=yes");
}

function loadSearch() {
	frm = document.zipFrm;
	if (frm.area3.value == "") {
		alert("도로명을 입력하세요.");
		frm.area3.focus();
		return;
	}
	frm.action = "07_zipSearch.jsp"
	frm.submit();
}

function sendAdd(zipcode, adds) {
	opener.document.regFrm.zipcode.value = zipcode;
	opener.document.regFrm.address.value = adds;
	self.close();
}

function inputCheck(){
	if(document.regFrm.id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regFrm.id.focus();
		return;
	}
	if(document.regFrm.pwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regFrm.pwd.focus();
		return;
	}
	if(document.regFrm.repwd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regFrm.repwd.focus();
		return;
	}
	if(document.regFrm.pwd.value != document.regFrm.repwd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regFrm.repwd.value="";
		document.regFrm.repwd.focus();
		return;
	}
	if(document.regFrm.name.value==""){
		alert("이름을 입력해 주세요.");
		document.regFrm.name.focus();
		return;
	}
	if(document.regFrm.birthday.value==""){
		alert("생년월일을 입력해 주세요.");
		document.regFrm.birthday.focus();
		return;
	}
	if(document.regFrm.email.value==""){
		alert("이메일을 입력해 주세요.");
		document.regFrm.email.focus();
		return;
	}
    const str=document.regFrm.email.value;	   
    const atPos = str.indexOf('@');
    const atLastPos = str.lastIndexOf('@');
    const dotPos = str.indexOf('.'); 
    const spacePos = str.indexOf(' ');
    const commaPos = str.indexOf(',');
    const eMailSize = str.length;
    if (atPos > 1 && atPos == atLastPos && 
	   dotPos > 3 && spacePos == -1 && commaPos == -1 
	   && atPos + 1 < dotPos && dotPos + 1 < eMailSize);
    else {
          alert('E-mail 주소 형식이 잘못되었습니다.\n\r다시 입력해 주세요!');
	      document.regFrm.email.focus();
		  return;
    }
    if(document.regFrm.zipcode.value==""){
		alert("우편번호를 검색해 주세요.");
		return;
	}
	if(document.regFrm.job.value=="0"){
		alert("직업을 선택해 주세요.");
		document.regFrm.job.focus();
		return;
	}
	document.regFrm.submit();
}

function win_close(){
	self.close(); // 현재 창이 닫힘
}