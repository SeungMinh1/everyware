/**
 * waitRefViewDocList.js
 */
$$('#approval').click(function() {
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
	
})