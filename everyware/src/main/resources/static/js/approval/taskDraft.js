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
					alert('성공');
					location.href = "/draftDocList";
				}
				console.log(result);
			})
			.fail(err => console.log(err));
		}
		console.log(result);
	})
	.fail(err=>console.log(err));
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
	
	var emps2 = $('.lineBody');
	var qrefEmp = [];
	var qrefId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '참조') {
			qrefEmp.push(emps[i].dataset.name);
			qrefId.push(emps[i].id);
		}
	}
	
	var qviewEmp = [];
	var qviewId = [];
	for(var i=1; i<emps2.length; i++) {
		if($($('.lineBody')[i]).children()[1].textContent == '열람') {
			qviewEmp.push(emps[i].dataset.name);
			qviewId.push(emps[i].id);
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












