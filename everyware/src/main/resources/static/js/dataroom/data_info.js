/**
 *  data_info.js
 */
$(function(){
	$('.fileName').on('click',function(){
		console.log('클릭')
		var fileId = $(this).attr('id');
		console.log(fileId);
		let data1 = {
			 fileId: fileId 
		}
		$.ajax('selectFileInfo', {
	      type: 'POST'
	      , contentType : 'application/JSON'
	      , data: JSON.stringify(data1)
	      , success: function(data) {
	        console.log(data);
	        var fileCallPath = encodeURIComponent(//
				data.uploadPath  +"/"+ data.uploadFileName + "_" + data.originFileName);
	        window.location.href= "download?fileName=" + fileCallPath;
	      },
	      error: function(error) {
	        console.log('에러:', error);
	      }
	    });
	});

})
