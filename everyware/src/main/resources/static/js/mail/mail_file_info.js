/**
 * mail_file_info.js
 */

 $(function(){
	console.log(mailId);
	
	
	$.ajax('selectFileByMailId', {
	      type: 'POST'
	      , contentType : 'application/JSON'
	      , data: JSON.stringify({mailId: mailId})
	      , success: function(data) {
	        console.log(data);
	        $(data).each(function(i, obj){
				showUploadFile(obj);
			})
	      },
	      error: function(error) {
	        console.log('에러:', error);
	      }
	    });
	    
	    
	    
	 var uploadResult = $(".uploadResult ul");
	 function showUploadFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			// 파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);
		    // 2024/7/3 + "/"" + UUID(uploadFileName) + "_" + test1.png
	  
			str += "<li class='li_style'" + ">"
			str += "<a href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</a>"
			str += "<span>" + '(' + obj.fileSize + ')' + "</span>"
			str += "<span data-file=\'" + fileCallPath + "\'data-type='file'> x </span>"
			str += "</li>"
			
		});
		uploadResult.append(str);
	};
 })