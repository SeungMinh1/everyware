/**
 * approvalDocList.js
 */
$('#all').click(function() {
	location.href = '/approvalDocList'
})

$('#prog').click(function() {
	location.href = '/goApprovalDocList'
})

$('#comp').click(function() {
	location.href = '/compApprovalDocList'
})

$('#rej').click(function() {
	location.href = '/rejApprovalDocList'
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

