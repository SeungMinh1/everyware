/**
 * docInfo.js
 */
var ref = document.referrer;
if(ref == 'https://192.168.0.23:8100/waitDocList') {
	$('#approval').css('display', 'inline-block');
	$('#reject').css('display', 'inline-block');
}

var approvalemps = [];
for(var i=0; i<$('.approvalName').length; i++) {
	approvalemps.push($('.approvalName')[i].dataset.name);
}
$('.emps')[0].innerText = approvalemps;
$('.apptitle')[0].innerText = $('.inputTitleBox')[0].innerText;

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
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

$('#ccccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#cccccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#ccccccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#cccccccclose').on('click', function() {
	$('.modal').css('display', 'none');
})

// 결재
$('#approval').on('click', function() {
	$('#mapp').css('display', 'block');
	var msg = $('.inputTitleBox')[0].innerText;
	$('#text').text(msg);
});

// 반려
$('#reject').on('click', function() {
	$('#mrej').css('display', 'block');
	var rmsg = $('.inputTitleBox')[0].innerText;
	$('#rtext').text(rmsg);
});

// 상신취소
$('#draftcan').on('click', function() {
	$('#mdraftcan').css('display', 'block');
});

$('#dcan').on('click', function() {
	var params = new URL(location.href).searchParams;
	var id = Number(params.get("docId"));
	let selectAppIds = [];
	let name = $('.approvalName')
	for(var i=0; i<name.length; i++) {
		selectAppIds.push(name[i].id);
	}
	
	var data = {
		approvalIdList :selectAppIds, docId : id
	}
	$.ajax('approvalDelete', {
		type : 'post'
		, data : JSON.stringify(data)
		, contentType : 'application/json'
	})
	.done(result => {
		if(result) {
			$.ajax('docInfoDelete', {
				type : 'post'
				, data : JSON.stringify(id)
				, contentType : 'application/json'
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
})

// 결재요청
$('#req').on('click', function() {
	$('#mreq').css('display', 'block');
	var msg = $('#msg').val();
	$('#text').text(msg);	
})

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
	
	$.ajax('docUpdate', {
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

// 임시저장
$('#tem').on('click', function() {
	$('#mtem').css('display', 'block');
})

$('#save').on('click', function() {
	let temp = tempInfo();
	
	if($('#msg').val() == '') {
		alert('제목은 필수값입니다');
		$('#msg').focus();
	}
	
	$.ajax('tempDocUpdate', {
		type : 'post'
		, contentType : 'application/JSON'
		, data : JSON.stringify(temp)
	})
	.done(result => {
		if(result) {
			alert('성공');
			location.href = "/temporaryDocList";
		}
	})
	.fail(err => console.log(err));
})

// 취소
$('#can').on('click', function() {
	$('#mcan').css('display', 'block');
})

$('#ok').on('click', function() {
	location.href="/draftDocList";
})

// 삭제
$('#del').on('click', function() {
	$('#mdel').css('display', 'block');
});

$('#mmdel').on('click', function() {
	var params = new URL(location.href).searchParams;
	var id = Number(params.get("docId"));
	
	$.ajax('docInfoDelete', {
		type : 'post'
		, data : JSON.stringify(id)
		, contentType : 'application/JSON'
	})
	.done(result => {
		if(result) {
			alert('성공');
			location.href = "/temporaryDocList";
		}
	})
	.fail(err => console.log(err));
});

// 결재정보
$('#info').on('click', function() {
	$('#minfo').css('display', 'block');
});

$('#saveInfo').on('click', function() {
	$('.modal').css('display', 'none');
})

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
	
	var params = new URL(location.href).searchParams;
	var id = Number(params.get("docId"));
	var data = {
		docId				: id
		, docTitle			: $('#msg').val()
		, docContent 		: $('#summernote').summernote('code')
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
		, enforceDate 		: $('.inputDateBox').val()
		, docInfo			: $('#container')[0].outerHTML
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
	
	var params = new URL(location.href).searchParams;
	var id = Number(params.get("docId"));
	var data7 = {
		docId				: id
		, docTitle			: $('#msg').val()
		, docContent 		: $('#summernote').summernote('code')
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

$(document).ready(function(){
		$('#down').click(function() { // pdf저장 button id
		var title = $('.inputTitleBox')[0].innerText;
		
	    html2canvas($('#container')[0]).then(function(canvas) { //저장 영역 div id
					
		
	    // 캔버스를 이미지로 변환
	    var imgData = canvas.toDataURL('image/png');
	    
		     
	    var imgWidth = 210;
		var pageHeight = imgHeight;
		var imgHeight = canvas.height * imgWidth / canvas.width;
		var heightLeft = imgHeight;
		var position = 0;
	    var doc = new jsPDF('p', 'mm');
	       
	    // 첫 페이지 출력
	    doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
	    heightLeft -= pageHeight;	        
	 
	    // 파일 저장
	    doc.save(title + '.pdf');

		  
		});
		
		});
	})