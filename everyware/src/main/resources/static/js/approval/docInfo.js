/**
 * docInfo.js
 */
$('document').ready(function() {
	$('.note-editor').remove();
	$('input').remove();
	var ref = document.referrer;
	if(ref == 'https://192.168.0.23:8100/waitDocList') {
		$('#approval').css('display', 'inline-block');
		$('#reject').css('display', 'inline-block');
	}
})

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

// 결재
$('#approval').on('click', function() {
	$('#mreq').css('display', 'block');
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
	
});

// 메일발송
$('#send').on('click', function() {
	
});

// 임시저장
$('#save').on('click', function() {
	
});

// 취소
$('#can').on('click', function() {
	
});

// 삭제
$('#del').on('click', function() {
	
});

// 결재정보
$('#info').on('click', function() {
	
});
