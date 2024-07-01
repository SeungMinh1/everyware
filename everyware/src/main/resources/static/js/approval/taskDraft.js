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







