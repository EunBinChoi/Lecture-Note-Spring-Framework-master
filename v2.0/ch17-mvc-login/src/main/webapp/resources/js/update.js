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
//////////////////////// 이벤트 등록 ///////////////////////////
$('#update').submit(() => { 
	const isUpdateValid = isValidUpdateForm();
	return isUpdateValid;
});
