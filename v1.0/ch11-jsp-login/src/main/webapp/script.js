function loginCheck() {
	if (document.loginFrm.id.value == "") {
		alert("���̵� �Է��� �ּ���.");
		document.loginFrm.id.focus();
		return;
	}
	if (document.loginFrm.pwd.value == "") {
		alert("��й�ȣ�� �Է��� �ּ���.");
		document.loginFrm.pwd.focus();
		return;
	}
	document.loginFrm.submit();
}

function idCheck(id) {
	frm = document.regFrm;
	if (id == "") {
		alert("���̵� �Է��� �ּ���.");
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
		alert("���θ��� �Է��ϼ���.");
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
		alert("���̵� �Է��� �ּ���.");
		document.regFrm.id.focus();
		return;
	}
	if(document.regFrm.pwd.value==""){
		alert("��й�ȣ�� �Է��� �ּ���.");
		document.regFrm.pwd.focus();
		return;
	}
	if(document.regFrm.repwd.value==""){
		alert("��й�ȣ�� Ȯ���� �ּ���");
		document.regFrm.repwd.focus();
		return;
	}
	if(document.regFrm.pwd.value != document.regFrm.repwd.value){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		document.regFrm.repwd.value="";
		document.regFrm.repwd.focus();
		return;
	}
	if(document.regFrm.name.value==""){
		alert("�̸��� �Է��� �ּ���.");
		document.regFrm.name.focus();
		return;
	}
	if(document.regFrm.birthday.value==""){
		alert("��������� �Է��� �ּ���.");
		document.regFrm.birthday.focus();
		return;
	}
	if(document.regFrm.email.value==""){
		alert("�̸����� �Է��� �ּ���.");
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
          alert('E-mail �ּ� ������ �߸��Ǿ����ϴ�.\n\r�ٽ� �Է��� �ּ���!');
	      document.regFrm.email.focus();
		  return;
    }
    if(document.regFrm.zipcode.value==""){
		alert("�����ȣ�� �˻��� �ּ���.");
		return;
	}
	if(document.regFrm.job.value=="0"){
		alert("������ ������ �ּ���.");
		document.regFrm.job.focus();
		return;
	}
	document.regFrm.submit();
}

function win_close(){
	self.close(); // ���� â�� ����
}