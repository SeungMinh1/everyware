/**
 * receptionDocList.js
 */
$('#all').click(function() {
	location.href = '/receptionDocList'
})

$('#wait').click(function() {
	location.href = '/waitReceptionDocList'
})

$('#rec').click(function() {
	location.href = '/recReceptionDocList'
})

$('#prog').click(function() {
	location.href = '/goReceptionDocList'
})

$('#comp').click(function() {
	location.href = '/compReceptionDocList'
})

$('#rej').click(function() {
	location.href = '/rejReceptionDocList'
})

$('#re').click(function() {
	location.href = '/retReceptionDocList'
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

