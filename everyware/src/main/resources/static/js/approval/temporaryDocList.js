/**
 * temporaryDocList.js
 */
$('#del').click(function() {
	if($('input:checkbox:checked').length != 0) {
		$('#mdel').css('display', 'block');
	}else {
		$('#mno').css('display', 'block');
	}
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#cclose').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#ok').on('click', function() {
	let selectDocIds = [];
	$('.chk:checked').each(function() {
		selectDocIds.push($(this).val());
	})
	console.log(selectDocIds);
	$.ajax({
		url : 'docDelete',
		type : 'post',
		data : JSON.stringify(selectDocIds),
		contentType : 'application/json',
	})
	.done(result => {
		if(result) {
			alert('선택된 항목이 삭제되었습니다.');
			window.location.reload();
		} else {
			alert('실패');
		}
	})
})

