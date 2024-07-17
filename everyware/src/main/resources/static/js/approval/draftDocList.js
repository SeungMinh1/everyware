/**
 * draftDocList.js
 */
$('#all').click(function() {
	location.href = '/draftDocList'
})

$('#prog').click(function() {
	location.href = '/goDraftDocList'
})

$('#comp').click(function() {
	location.href = '/compDraftDocList'
})

$('#rej').click(function() {
	location.href = '/rejDraftDocList'
})

$('.close').on('click', function() {
	$('.modal').css('display', 'none');
})

$('#close').on('click', function() {
	$('.modal').css('display', 'none');
})

// 검색
/*function addressSearch(page) {
  $('#tbody').empty();
  let searchType = $('#type').val();
  let searchKeyword = $('#searchKeyword').val();

console.log(searchType);
console.log(searchKeyword);
  $.ajax({
    url: 'draftDocList',
    data: {
      searchOption: searchType,
      dosearch: searchKeyword
    }
  })
  .done(result => {
  	if(result) {
		console.log(result);
	}
  })
  .fail(err => console.log(err))	
}
*/







