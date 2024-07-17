/**
 * refDocList.js
 */
$('#all').click(function() {
	location.href = '/refViewDocList'
})

$('#ref').click(function() {
	location.href = '/refDocList'
})

$('#view').click(function() {
	location.href = '/viewDocList'
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

