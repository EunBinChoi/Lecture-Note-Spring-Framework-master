// ���� ������ ���� ���� �ٸ� ���� �ִ� ��ҿ� �Է��ϴ� �Լ�
// user, title, content ���밪 ����
function inputValue(form1, param, form2, idx){
	const paramValue = form1.elements[idx].value;
	form2.elements[idx].value = paramValue;			 
	return;
}

// �� �̸��� ���ڷ� �޾Ƽ� �Է��� ������ ���� �Է����� ������ �Է��϶�� ��� �޽��� ���
function addFile(formName){
	
	// ������ �ƴ� � ���� �ԷµǾ��� ��쿡�� if�� ���θ� �������� ����
	if(formName.addcnt.value==""){
		alert(" �Է��� ���� ������ �Է��ϰ� Ȯ�ι�ư�� �����ּ���" ); 
		formName.addcnt.focus();						 
		return;
	}
	formName.submit(); // �� ����
}

// �� �̸��� ���ڷ� �޾Ƽ� �ƹ��� ������ �������� ������ ���� �����϶�� ��� �޽��� ���
function elementCheck(formName){
   paramIndex = 1; // ���ϼ��� â �ε����� ����Ű�� ����

   // ���� �ִ� ��ü �� ��ҵ��� ������ŭ �ݺ� ����
   for(idx = 0; idx < formName.elements.length; idx++){
	
	  // �� ��ҵ� �� �ε����� �����Ǵ� �� ����� type�� file���� �ƴ��� üũ�ϴ� �κ�
      // <input type="file" name=...> �� ��� ã�Ƴ��� �κ�
      if(formName.elements[idx].type == "file"){
		 // �� ��ҿ� �Էµ� ���� �������� �ƴ��� üũ
         if(formName.elements[idx].value ==""){
	        const message = paramIndex +" ��° ���������� �����Ǿ����ϴ�.\n���ε��� ������ ������ �ּ���";
		    alert(message);
			formName.elements[idx].focus();
			return;		
	     }
	     paramIndex++; 
		// ���� �ݺ��� ���� �� file�̶�� �Ӽ����� ������ �κп� 
		// �ƹ��� ���� �Էµ��� �ʾ��� ��� �� �� ��� ���� �����ϱ� ����
      }
   }
   formName.action = "04_fileInfoView.jsp";
   formName.submit(); // �� ����
}