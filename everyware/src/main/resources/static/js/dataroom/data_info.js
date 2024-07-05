/**
 *  data_info.js
 */
$(function(){
	$('tr').click(function() {
		var dataId = $(this).attr('id');
     	console.log(dataId);

		/*   
		 $.ajax('selectData', {
	      type: 'get'
	      , contentType : 'application/JSON'
	      , data: { dataId: dataId }
	      , success: function(data) {
	        alert('성공:', data);
	        console.log('성공:', data);
	      },
	      error: function(error) {
	        console.log('에러:', error);
	      }
	    });
	    
	    */
  
  
  });

})
