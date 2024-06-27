/**
 * 
 */
$(function(){
  $('.mailBoxBtn').on('click', mailBoxInfofunc);
	  function mailBoxInfofunc(e) {
	    e.preventDefault(); // 기본 동작 막기
	    console.log($(this).data('no'));
	
	    $.ajax({
	        url: 'mailboxInfo?mailboxId=' + $(this).data('no') + '&empId=' + empId,
	        type: 'get'
	    })
	    .done(result => {
	        console.log(result);
	    })
	    .fail(err => console.log(err));
	}
 
	
})