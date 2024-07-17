/**
 * sendDocList.js
 */
$('#all').click(function() {
	location.href = '/sendDocList'
})

$('#wait').click(function() {
	location.href = '/waitSendDocList'
})

$('#rec').click(function() {
	location.href = '/recSendDocList'
})

$('#prog').click(function() {
	location.href = '/goSendDocList'
})

$('#comp').click(function() {
	location.href = '/compSendDocList'
})

$('#rej').click(function() {
	location.href = '/rejSendDocList'
})

$('#re').click(function() {
	location.href = '/retSendDocList'
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

