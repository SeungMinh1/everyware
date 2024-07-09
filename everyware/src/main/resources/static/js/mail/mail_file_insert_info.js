/**
 * mail_file_info.js
 */

 $(function(){
	var selectFile = [];
	fileList = selectFile;
	
	//mailId로 file찾아서 조회
	$.ajax('selectFileByMailId', {
	      type: 'POST'
	      , contentType : 'application/JSON'
	      , data: JSON.stringify({mailId: mailId})
	      , success: function(data) {
			
			//원래있던 파일을 selectFile에 넣고, 전역변수인 fileList에 넣음.
			//임시저장시 필요 & fileList == 첨부파일
	        selectFile.push(...data);
	       
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
			console.log(obj)
			// 파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);
		    // 2024/7/3 + "/"" + UUID(uploadFileName) + "_" + test1.png
	  
			str += "<li class='li_style'" + ">"
			str += "<a href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</a>"
			str += "<span>" + '(' + obj.fileSize + ')' + "</span>"
			str += "<span data-file=\'" + fileCallPath + "\'data-fileid=\'" + obj.fileId + "\'data-type='file'> x </span>"
			str += "</li>"
			
		});
		uploadResult.append(str);
	};
	
	$(".uploadResult").on('click', 'span', function(){
		var dataFileId = $(this).data("fileid");
		console.log(dataFileId);
		let info = {fileId : dataFileId};
		$.ajax({
				type: "POST",
                url: "deleteDataFileInfo",
                contentType : 'application/JSON',
				data : JSON.stringify(info)
		})
		.done(result=>{
			console.log(result);
		})
	});
	
 })