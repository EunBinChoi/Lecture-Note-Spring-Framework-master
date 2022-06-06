function isLoginValidForm() {
	if (!(isValidID($("#loginId").val()))) {
		$("#loginId").focus();
		return false;
	}
	else if (!(isValidPass($("#loginCurpwd").val()))) {
		$("#loginCurpwd").focus();
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

///////////////////////////////////////////////////////////////
//////////////////////// 이벤트 등록 ///////////////////////////
$('#login').submit(() => { 
	//const isLoginValid = isLoginValidForm();
	//return isLoginValid;
	return true;
});

