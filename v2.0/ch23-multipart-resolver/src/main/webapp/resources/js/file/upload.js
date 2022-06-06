function isValidUserId() {
	if ($('#userName').val() == "") {
		alert("Please input user's id!");
		$('#userName').focus();
		return false;
	}
	return true;
}

function isValidTitle() {
	if ($('#title').val() == "") {
		alert("Please input title!");
		$('#title').focus();
		return false;
	}
	return true;
}

function isValidContent() {
	/*if ($('#content').val() == "") {
		alert("Please input content!");
		$('#content').focus();
		return false;
	}*/
	return true;
}


function isValidInputFileCount() {
	if ($('#fileCount').val() == "") {
		alert("Please input the number of file!");
		$('#fileCount').focus();
		return false;
	} else if(!($('#fileCount').val() >= 0 && $('#fileCount').val() <= 100)) {
		alert("Please input the number of file in range (0 ~ 100)!");
		$('#fileCount').focus();
		return false;
	}
	return true;
}

///////////////////////////////////////////////////////////////
//////////////////////// 이벤트 등록 ///////////////////////////

$('#fileCountBtn').click(() => {
	if (isValidInputFileCount()) {
		$('#fileForm').submit();
	}
});

$('#fileSubmitBtn').click(() => {
	if (!isValidUserId() || !isValidTitle() || !isValidContent()) {
		return false;
	}
	$('#fileForm').attr("action", "./doUpload");
	$('#fileForm').submit();

});

$('#resetBtn').click(() => {
	window.location.href = "./upload";
	// redirect
	// 기존 parameter 데이터 지움
});



const fileInputLength = $('input[type=file]').length;
const table = $('#previewTable')[0];


for(let i = 0; i < fileInputLength; i++) {
	selectFile(i);
}

function selectFile(fileIdx) {	
	$('#fileUpload' + fileIdx).change(() => {
		$('#previewTable').prev().html("<h3>Your Selected Files (Preview)</h3>");
		
		const files = $('#fileUpload' + fileIdx);
		for(let j = 0; j < files.length; j++) {
			const file = files.get(0).files;
			
			if(!$('#previewTable').find('tr')[fileIdx]) {
				$('#previewTable').append("<tr id='previewRow" + fileIdx + "'></tr>");
			} else {
				$('#previewTable').find('tr')[fileIdx].innerHTML = "<tr id='previewRow" + fileIdx + "'></tr>";
			}
			
			for(let k = 0; k < file.length; k++) {	
				const selected = file[k];
				
				if(selected) {
										
					if(selected.type.startsWith("image")) {	
						$('#previewRow' + fileIdx).append("<td id='previewCol"+ k + "'> \
																<figure style='text-align: center; font-size: 15px; margin: 0px;'> \
																	<img id=\'previewFileUpload" + fileIdx + "" + k + "\' src= \'\' alt= \'previewImage\'> \
																	<figcaption>Selected File [" + fileIdx + "][" + k + "]</figcaption> \
																</figure>\
														   </td>");
											
						const reader = new FileReader();
			
						reader.onload = () => {
							$("#previewFileUpload" + fileIdx + "" + k).css ("width",   "200px");
							$("#previewFileUpload" + fileIdx + "" + k).css ("height",  "200px");
							$("#previewFileUpload" + fileIdx + "" + k).css ("padding", "5px");
							$("#previewFileUpload" + fileIdx + "" + k).attr("src", reader.result);
						}
				
						reader.readAsDataURL(selected); // 파일의 URL을 통해 파일을 읽어옴
						
					} else {
						$('#previewRow' + fileIdx).append("<td id='previewCol"+ k + "'> \
																<div style='text-align: center; font-size: 15px; margin: 0px;'> \
																	<textarea id=\'previewFileUpload" + fileIdx + "" + k + "\' src= \'\' alt= \'previewImage\'></textarea> \
																	<p style='margin: 0px; padding: 0px;'>Selected File [" + fileIdx + "][" + k + "]</p> \
																</div> \
															</td>");
															
						const reader = new FileReader();
			
						reader.onload = (fileReaderEvent) => {
							$("#previewFileUpload" + fileIdx + "" + k).css ("width",   "200px");
							$("#previewFileUpload" + fileIdx + "" + k).css ("height",  "180px");
							$("#previewCol" + k).css ("padding", "10px");
							$("#previewFileUpload" + fileIdx + "" + k).val (fileReaderEvent.target.result);
						}
				
						reader.readAsText(selected, "UTF-8"); // 파일의 URL을 통해 파일을 읽어옴
					}

				}
	
			}
			
		}
		
	});
}

/*$('input[type=file]').change(() => {
	//const table = $('#previewTable')[0];
	//const row = table.insertRow(0);
	//const cell = row.insertCell(fileRealCount);
	const files = $('input[type=file]'); //.get(0).files.length;	
	console.log(files);
	
	
	for(let i = 0; i < files.length; i++) {		
		console.log('i', i);
		const file = files.get(i).files;
		console.log(file);
		console.log(file.length);
		
		for(let j = 0; j < file.length; j++) {
			console.log('j', j);
			const fileSelected = file[j];
			console.log(fileSelected);
		}
	}
	
	
	/*console.log($("input[type=file]"));
	const files = $("input[type=file]"); 
	for(let i = 0; i < files.length; i++) {
		console.log(files.get(i));
		
		
		//for(let j = 0; j < files[i].length; j++) {
			const fileSelected =  files.get(i).files[0];
			console.log(fileSelected);
			
			if (fileSelected) {
				const reader = new FileReader();
		
				reader.onload = function() {
					$("#previewFileUpload" + i).css ("width", "200px");
					$("#previewFileUpload" + i).attr("src", reader.result);
				}
		
				reader.readAsDataURL(fileSelected); // 파일의 URL을 통해 파일을 읽어옴
			}
		//}
		
	}*/
	

	
//});*/