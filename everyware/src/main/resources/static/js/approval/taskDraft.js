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
	
})

// 결재정보 저장
$('#saveInfo').on('clikc', function() {
	
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
function draftInfo() {
	
}

// 결재자 등록
$('.nested li').draggable({revert: "valid"});
$('#lineApp').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			$('.lineBody')[1].children[1].append('결재')
			console.log(ui)
			//$('.lineBody')[1].children[2].append(ui.draggable[0].data('emp'))
		}
	});
	
$('#lineRec').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			$('.lineBody')[2].children[1].append('수신')
		}
	});
	
$('#lineRef').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			$('.lineBody')[3].children[1].append('참조')
		}
	});
	
$('#lineView').droppable({
		accept: $('.nested li'),
		drop: function(e, ui) {
			$('.lineBody')[4].children[1].append('열람')
		}
	});
















