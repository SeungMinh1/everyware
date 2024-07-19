/**
 * taskDraft.js
 */
// 모달창
$('#req').on('click', function() {
	$('#mreq').css('display', 'block');
	var msg = $('#msg').val();
	$('#text').text(msg);
})

$('#can').on('click', function() {
	$('#mcan').css('display', 'block');
})

$('#tem').on('click', function() {
	$('#mtem').css('display', 'block');
})

$('#info').on('click', function() {
	$('#minfo').css('display', 'block');
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

// 확인
$('#ok').on('click', function() {
	location.href="/draftDocList";
})

// 취소
$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#cclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#ccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#cccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

// 임시저장
$('#save').on('click', function() {
	let temp = tempInfo();
	
	if($('#msg').val() == '') {
		Swal.fire({
			title: '제목은 필수 값입니다.',
			icon: 'warning'
		})
		.then((result) => {
			$('#msg').focus();
		})
	}
	
	$.ajax('tempInsert', {
		type : 'post'
		, contentType : 'application/JSON'
		, data : JSON.stringify(temp)
	})
	.done(result => {
		if(result) {
				Swal.fire({
					title : '임시저장되었습니다.',
					icon : 'success'
				})
				.then((result)=>{
					location.href = "/temporaryDocList";									
				})
			}
	})
	.fail(err => console.log(err));
})

// 결재요청
$('#request').on('click', function() {
	let doc = docInfo();
	let draft = draftInfo();
	let approval = approvalInfo();
	let reception = receptionInfo();
	let send = sendInfo();
	let ref = refInfo();
	let view = viewInfo();
	
	if($('#msg').val() == '') {
		Swal.fire({
			title: '제목은 필수 값입니다.',
			icon: 'warning'
		})
		.then((result) => {
			$('#msg').focus();
		})
	}
	
	$.ajax('docInsert', {
		type : 'post'
		, contentType : 'application/JSON'
		, data : JSON.stringify(doc)
	})
	.done(result => {
		if(result) {
			$.ajax('draftInsert', {
				type: 'post'
				, contentType: 'application/JSON'
				, data: JSON.stringify(draft)
			})
			.done(result => {
				if (result) {
					$.ajax('approvalInsert', {
						type: 'post'
						, contentType: 'application/JSON'
						, data: JSON.stringify(approval)
					})
					.done(result => {
						if(result) {
							Swal.fire({
								title : '요청되었습니다.',
								icon : 'success'
							})
							.then((result)=>{
								location.href = "/draftDocList";									
							})
						}
					})
					.fail(err => console.log(err));
				}				
			})
			.fail(err => console.log(err));
		}		
	})
	.fail(err=>console.log(err));
	
	if($('#lineRec tr').length > 0) {
		$.ajax('receptionInsert', {
			type: 'post'
			, contentType: 'application/JSON'
			, data: JSON.stringify(reception)
		})
		.done(result => {
			if (result) {
				console.log(result);
			}
		})
		.fail(err => console.log(err));
	}
	
	if($('#lineRec tr').length > 0) {
		$.ajax('sendInsert', {
			type: 'post'
			, contentType: 'application/JSON'
			, data: JSON.stringify(send)
		})
		.done(result => {
			if (result) {
				console.log(result);
			}
		})
		.fail(err => console.log(err));
	}
	
	if($('#lineRef tr').length > 0) {
		$.ajax('refInsert', {
			type: 'post'
			, contentType: 'application/JSON'
			, data: JSON.stringify(ref)
		})
		.done(result => {
			if (result) {
				console.log(result);
			}
		})
		.fail(err => console.log(err));
	}
	
	if($('#lineView tr').length > 0) {
		$.ajax('viewInsert', {
			type: 'post'
			, contentType: 'application/JSON'
			, data: JSON.stringify(view)
		})
		.done(result => {
			if (result) {
				console.log(result);
			}
		})
		.fail(err => console.log(err));
	}
})

// 결재정보 저장
$('#saveInfo').on('click', function() {
	$('.modal').css('display', 'none');
})

// 에디터
$(function() {
	// Summernote
	$('#summernote').summernote({height : 300})

	// CodeMirror
	CodeMirror.fromTextArea(document.getElementById("codeMirrorDemo"), {
		mode : "htmlmixed",
		theme : "monokai",
	});
})

// value 저장
function docInfo() {
	var emps = $('.approvalName');
	var appEmp = [];
	var appId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '승인') {
			appEmp.push(emps[i].dataset.name);
			appId.push(emps[i].id);
		}
	}
	
	var recEmp = [];
	var recId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			recEmp.push(emps[i].dataset.name);
			recId.push(emps[i].id);
		}
	}
	
	var sendEmp = [];
	var sendId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			sendEmp.push($('.draftName')[0].innerText);
			sendId.push($('.boardTable')[0].id);
		}
	}
	
	var emps2 = $('.lineBody');
	var qrefEmp = [];
	var qrefId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '참조') {
			qrefEmp.push(emps2[i].dataset.name);
			qrefId.push(emps2[i].id);
		}
	}
	
	var qviewEmp = [];
	var qviewId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '열람') {
			qviewEmp.push(emps2[i].dataset.name);
			qviewId.push(emps2[i].id);
		}
	}
	
	var inputFile = $("input[name='uploadFile']");
	var files = inputFile[0].files;
		
	var fileData = [];
	for(var i=0; i<files.length; i++) {
		fileData.push(files[i].name)
	}
	
	var data = {
		docTitle			: $('#msg').val()
		, draftEmpId 		: $('.boardTable')[0].id
		, docContent 		: $('#summernote').summernote('code')
		, draftEmp			: $('.draftName')[0].innerText
		, draftEmpDept		: $('.deptName')[0].innerText
		, draftDate			: $('.sysdate')[0].innerText
		, approvalTask		: $('.card-title')[0].innerText
		, approvalFileList	: fileData
		, approvalOrder     : $('input[name=order]:checked').val()
		, approvalNameList	: appEmp
		, approvalIdList	: appId
		, receptionNameList	: recEmp
		, receptionIdList 	: recId
		, refNameList		: qrefEmp
		, refIdList			: qrefId
		, viewNameList		: qviewEmp
		, viewIdList		: qviewId
		, sendNameList 		: sendEmp
		, sendIdList		: sendId
		, docInfo			: $('#container')[0].outerHTML
		, enforceDate 		: $('.inputDateBox input')[0].value
		, receptionDate 	: $('.sysdate')[0].innerText
		, sendDate 			: $('.sysdate')[0].innerText
		, approvalInfo		: $('.modal-footer')[7].outerHTML
	}
	
	return data;
}

function draftInfo() {
	
	var data1 = {
		draftEmp 		: $('.draftName')[0].innerText
		, draftEmpDept  : $('.deptName')[0].innerText
		, draftTitle 	: $('#msg').val()
		, draftContent 	: $('#draftContent').val()
		, draftDate 	: $('.sysdate')[0].innerText
	}
	
	return data1;
}

function approvalInfo() {
	var emps = $('.approvalName');
	var appEmp = [];
	
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '승인') {
			appEmp.push({ approvalEmpId : emps[i].id, approvalEmp : emps[i].dataset.name, 
						  approvalPosition : $('.approvalPosition')[i].innerText,
						  approvalOrder : $('input[name=order]:checked').val(),
						  draftEmp : $('.draftName')[0].innerText });
		}
	}
	
	var data2 = { 
		approvalEmpList	: appEmp 
		, approvalOrder : $('input[name=order]:checked').val()
	}
	
	return data2;
}

function receptionInfo() {
	var emps = $('.approvalName');
	var recEmp = [];
	
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			recEmp.push({ receptionEmpId : emps[i].id, receptionEmp : emps[i].dataset.name, 					 
						  draftEmp : $('.draftName')[0].innerText,
						  draftEmpId : $('.boardTable')[0].id });
		}
	}
	
	var data3 = { receptionEmpList	: recEmp }
	
	return data3;
}

function sendInfo() {
	var emps = $('.approvalName');
	var sendEmp = [];
	
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			sendEmp.push({ sendEmpId : $('.boardTable')[0].id, sendEmp : $('.draftName')[0].innerText, 					 
						  draftEmp : $('.draftName')[0].innerText,
						  draftEmpId : $('.boardTable')[0].id });
		}
	}
	
	var data4 = { sendEmpList	: sendEmp }
	
	return data4;
}

function refInfo() {
	var emps2 = $('.lineBody');
	var refEmp = [];
	
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '참조') {
			refEmp.push({ refEmpId : emps2[i].id, refEmp : emps2[i].dataset.name,
						 draftEmp : $('.draftName')[0].innerText,
						 draftEmpId : $('.boardTable')[0].id });
		}
	}
	
	var data5 = { refEmpList : refEmp }
	
	return data5;
}

function viewInfo() {
	var emps2 = $('.lineBody');
	var viewEmp = [];
	
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '열람') {
			viewEmp.push({ viewEmpId : emps2[i].id, viewEmp : emps2[i].dataset.name,
						 draftEmp : $('.draftName')[0].innerText,
						 draftEmpId : $('.boardTable')[0].id });
		}
	}
	
	var data6 = { viewEmpList : viewEmp }
	
	return data6;
}

function tempInfo() {
	var emps = $('.approvalName');
	var appEmp = [];
	var appId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '승인') {
			appEmp.push(emps[i].dataset.name);
			appId.push(emps[i].id);
		}
	}
	
	var recEmp = [];
	var recId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			recEmp.push(emps[i].dataset.name);
			recId.push(emps[i].id);
		}
	}
	
	var sendEmp = [];
	var sendId = [];
	for(var i=0; i<emps.length; i++) {
		if($('.approval')[i].innerText == '수신') {
			sendEmp.push($('.draftName')[0].innerText);
			sendId.push($('.boardTable')[0].id);
		}
	}
	
	var emps2 = $('.lineBody');
	var qrefEmp = [];
	var qrefId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '참조') {
			qrefEmp.push(emps2[i].dataset.name);
			qrefId.push(emps2[i].id);
		}
	}
	
	var qviewEmp = [];
	var qviewId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '열람') {
			qviewEmp.push(emps2[i].dataset.name);
			qviewId.push(emps2[i].id);
		}
	}
	
	var data7 = {
		docTitle			: $('#msg').val()
		, draftEmpId 		: $('.boardTable')[0].id
		, docContent 		: $('#summernote').summernote('code')
		, draftEmp			: $('.draftName')[0].innerText
		, draftEmpDept		: $('.deptName')[0].innerText
		, draftDate			: $('.sysdate')[0].innerText
		, approvalTask		: $('.card-title')[0].innerText
		, approvalFile		: ''
		, approvalOrder     : $('input[name=order]:checked').val()
		, approvalNameList	: appEmp
		, approvalIdList	: appId
		, receptionNameList	: recEmp
		, receptionIdList 	: recId
		, refNameList		: qrefEmp
		, refIdList			: qrefId
		, viewNameList		: qviewEmp
		, viewIdList		: qviewId
		, sendNameList 		: sendEmp
		, sendIdList		: sendId
		, docInfo			: $('#container')[0].outerHTML
		, enforceDate 		: $('.inputDateBox input')[0].value
		, createDate 		: $('.sysdate')[0].innerText
		, approvalInfo		: $('.modal-footer')[7].outerHTML
	}
	
	return data7;
}

// 결재자 등록
$('.nested li').draggable({revert: "valid"});
$('#lineApp').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			
			var html = "";
			var name = ui.draggable[0].className.substr(0,3);
			var id = ui.draggable[0].dataset.id;
			var dept = ui.draggable[0].id;
			var position = ui.draggable[0].dataset.pos;
			
			html += '<tr class="lineBody" id='+id+' data-name="'+name+'"'+'>';
			html += '<td>'+'>>'+'</td>';
			html += '<td>'+'결재'+'</td>';
			html += '<td>'+name+'</td>';
			html += '<td>'+dept+'</td>';
			html += '<td>'+''+'</td>';
			html += '<td>'+'<button tyep="button" class="btn delete">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineApp table').append(html);
			deleteBtn();
			
			var sign = "";
			
			sign += '<table class="signBox">'
			sign += '<tr>'
			sign += '<td class="approval" rowspan="4">승인</td>'
			sign += '<td class="approvalPosition">'+position+'</td>'
			sign += '</tr>'
			sign += '<tr>'
			sign += '<td class="approvalName" rowspan="2" id='+id+' data-name="'+name+'"'+'>'+name+'</td>'
			sign += '</tr>'
			sign += '</table>'
			$('.allSignBox').prepend(sign);
		}
	});
	
$('#lineRec').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			var html = "";
			var name = ui.draggable[0].className.substr(0,3);
			var id = ui.draggable[0].dataset.id;
			var dept = ui.draggable[0].id;
			var position = ui.draggable[0].dataset.pos;
			
			html += '<tr class="lineBody" id='+id+' data-name="'+name+'"'+'>';
			html += '<td>'+'>>'+'</td>';
			html += '<td>'+'수신'+'</td>';
			html += '<td>'+name+'</td>';
			html += '<td>'+dept+'</td>';
			html += '<td>'+''+'</td>';
			html += '<td>'+'<button tyep="button" class="btn delete">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineRec table').append(html)
			deleteBtn();
			
			var sign = "";
			
			sign += '<table class="signBox">'
			sign += '<tr>'
			sign += '<td class="approval" rowspan="4">수신</td>'
			sign += '<td class="approvalPosition">'+position+'</td>'
			sign += '</tr>'
			sign += '<tr>'
			sign += '<td class="approvalName" rowspan="2" id='+id+' data-name="'+name+'"'+'>'+name+'</td>'
			sign += '</tr>'
			sign += '</table>'
			$('.allSignBox').prepend(sign);
		}
	});
	
$('#lineRef').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			var html = "";
			var name = ui.draggable[0].className.substr(0,3);
			var id = ui.draggable[0].dataset.id;
			var dept = ui.draggable[0].id
			
			html += '<tr class="lineBody" id='+id+' data-name="'+name+'"'+'>';
			html += '<td>'+'>>'+'</td>';
			html += '<td>'+'참조'+'</td>';
			html += '<td>'+name+'</td>';
			html += '<td>'+dept+'</td>';
			html += '<td>'+''+'</td>';
			html += '<td>'+'<button tyep="button" class="btn delete">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineRef table').append(html)
			deleteBtn();
		}
	});
	
$('#lineView').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			var html = "";
			var name = ui.draggable[0].className.substr(0,3);
			var id = ui.draggable[0].dataset.id;
			var dept = ui.draggable[0].id
			
			html += '<tr class="lineBody" id='+id+' data-name="'+name+'"'+'>';
			html += '<td>'+'>>'+'</td>';
			html += '<td>'+'열람'+'</td>';
			html += '<td>'+name+'</td>';
			html += '<td>'+dept+'</td>';
			html += '<td>'+''+'</td>';
			html += '<td>'+'<button type="button" class="btn delete">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineView table').append(html)
			deleteBtn();
		}
	});

$('body').droppable({accept: $('.nested li')})

function deleteBtn() {
	$('.delete').on('click', function(e) {
		$(e.target).parent().parent().remove();
		for(var i=0; i<$('.approvalName').length; i++) {
			if($(e.target).parent().parent()[0].id == $('.approvalName')[i].id) {
				$($('.approvalName')[i]).parent().parent().parent().remove();
			}
		}
	})
}

$(function () {
//=========== 크기&확장자 제한 ============
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 10 * 1024 * 1024; // 10MB

    function checkExtentsion(fileName, fileSize){
        if (fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없습니다.")
            return false;
        }
        return true;
    };

//파일업로드
function uploadFile(){
	var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		var fileData = [];
		for(var i=0; i<files.length; i++) {
			fileData.push(files[i].name)
		}
		
		console.log("=== 1 ===");
		console.log(files);
		
		//업로드 된 파일이 있을때
		if(files.length != 0){
			// formData에 데이터 넣기
			for(var i = 0; i < files.length; i++){
				if(!checkExtentsion(files[i].name, files[i].size)){
					return false;
				}
				if(files[i].type.indexOf('audio') != -1){
					alert("오디오 파일이 포함되어 있습니다.")
					return false;
				}
				formData.append("uploadFile", files[i]);
		    }
		};
		
		$.ajax('/uploadAjax', { 
			processData : false,
			contentType : false,
			data : formData,
			type : 'post',
			success: function(result){
				console.log("=== 2 ===");
				console.log(result);
				
				//업로드된 파일 tr로 보여줌
				showUploadFile(result);
				
			/*=============================================
				 result를 fileList에 담음 => 
				 fileList는 mail_insert.html에 전역으로 선언함.
			===============================================*/
				fileList.push(...result);
				
				//input 파일선택 초기화
				$(".uploadDiv").html(cloneInputFileDiv.html());
				
				//input 파일을 새로 그려줬기 때문에 다시 change를 걸어줘야함.
				$("input[type='file']").change(function(){
						uploadFile();
				})
	
			}
		}).fail(err => console.log(err));
	
	return fileData;
}

//====== 파일이 올라갔을때 바로 출력 =======
	$("input[type='file']").change(function(){
		uploadFile();
	});
	
// ====== 파일첨부 input 복사 ( 초기화 목적 ) ==========
	var cloneInputFileDiv = $(".uploadDiv").clone();
	
// ====== 미리보기 ( tbody 태그 밑에 tr td 생성 ) ======
	var uploadResult = $(".uploadResult ul");
	
	//ajax -> result 받아와서 -> each 
	function showUploadFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			// 파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);
		    // 2024/7/3 + "/"" + UUID(uploadFileName) + "_" + test1.png
	  
			str += "<li class='li_style'" + ">"
			str += "<a class='a_style' href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</a>"
			str += "<span>" + '(' + obj.fileSize + ')' + "</span>"
			str += "<span style='"+ "cursor: pointer'" +"data-file=\'" + fileCallPath + "\'data-type='file'> x </span>"
			str += "</li>"
			
		});
		uploadResult.append(str);
	};

// ============= 삭제 ==============
	$(".uploadResult").on('click', 'span', function(){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		console.log(type);
		var li = $(this).parent();
		console.log(li);
		$.ajax({
			url: '/deleteFile',
			data : {fileName: targetFile, type: type},
			dataType:'text',
			type : 'POST',
			success: function(){
				li.remove();
				
				//삭제시 List에서도 삭제
				fileList = fileList.filter(file => {
                    var fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uploadFileName + "_" + file.originFileName);
                    return fileCallPath !== targetFile;
                });
			}
		});//$.ajax
		
	});
	
});







