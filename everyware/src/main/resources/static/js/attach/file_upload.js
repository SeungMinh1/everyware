/**
 * 
 */
$(function () {
	/*=============
	   첨부파일 업로드
	===============*/
	
	//파일첨부 input 복사 ( 초기화 목적 )
	var cloneInputFileDiv = $(".uploadDiv").clone();
	
	//업로드 한 파일이름 출력 -> ul 태그 밑에 li 생성
	var uploadResult = $(".uploadResult ul");

	function showUploadFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			//파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);

			//li생성
			str += "<li><a href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</li>";
			
			
		});
		uploadResult.append(str);
	}
	
	//업로드 버튼 클릭 -> uploadsAjax
	$('#uploadBtn').on('click', function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log("=== 1 ===");
		console.log(files);
		
		for(var i=0; i < files.length; i++){
			formData.append("uploadFile", files[i]);
			//console.log(formData.get("uploadFile"));
		}
		
		
		$.ajax('/uploadAjax', { 
			processData : false,
			contentType : false,
			data : formData,
			type : 'post',
			success: function(result){
				alert("Upload");
				console.log("=== 2 ===");
				console.log(result);
				
				//업로드된 파일 li로 보여줌
				showUploadFile(result);
				
				//input 파일선택 초기화
				$(".uploadDiv").html(cloneInputFileDiv.html());
			}
		})
		.fail(err => console.log(err));	
		
		
	});
	
	
});