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
	$()
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
		alert('제목은 필수값입니다');
		$('#msg').focus();
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
							alert('성공');
							location.href = "/draftDocList";
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
	$('#summernote').summernote()

	// CodeMirror
	CodeMirror.fromTextArea(document.getElementById("codeMirrorDemo"), {
		mode : "htmlmixed",
		theme : "monokai"
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
	
	var data = {
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
			html += '<td>'+'<button tyep="button" class="btn">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineApp table').append(html);	
			
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
			html += '<td>'+'<button tyep="button" class="btn">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineRec table').append(html)
			
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
			html += '<td>'+'<button tyep="button" class="btn">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineRef table').append(html)
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
			html += '<td>'+'<button tyep="button" class="btn">삭제</button>'+'</td>';
			html += '</tr>';
			$('#lineView table').append(html)
		}
	});












