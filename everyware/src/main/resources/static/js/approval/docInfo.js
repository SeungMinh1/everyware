/**
 * docInfo.js
 */
$('document').ready(function() {
	$('.note-editor').remove();
	$('input').remove();
	var params = new URL(location.href).searchParams;
	var id = params.get("docId");
	$.ajax({
		type : 'get'
		, url : 'docInfo?docId=' + id
		, success : function(result) {
			console.log(result)
		}
	})
	
})