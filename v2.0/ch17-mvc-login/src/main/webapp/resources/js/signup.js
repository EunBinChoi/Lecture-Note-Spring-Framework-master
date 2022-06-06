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



///////////////////////////////////////////////////////////////
//////////////////////// 이벤트 등록 ////////////////////////////
$('#signup').submit(() => { 
	const isSignUpValid = isSignUpValidForm();
	return isSignUpValid;
});


