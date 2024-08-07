/**
 * 	file_upload.js
 */

$(function () {
//=========== 크기&확장자 제한 ============
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 1048576; // 5MB

    function checkExtentsion(fileName, fileSize){
        if (fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없습니다.")
            return false;
        }
        return true;
    };
	
// ====== 파일첨부 input 복사 ( 초기화 목적 ) ==========
	var cloneInputFileDiv = $(".uploadDiv").clone();
	
// ====== 미리보기 ( ul 태그 밑에 li 생성 ) ======
	var uploadResult = $(".uploadResult ul");
	
	//ajax -> result 받아와서 -> each 
	function showUploadFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			// 파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);
		    // 2024/7/3 + "/"" + UUID(uploadFileName) + "_" + test1.png
			
			// 미리보기 li생성
			str += "<li>" + obj.originFileName + "<span data-file=\'"+ fileCallPath +"\'data-type='file'> x </span></li>";
			
			// 다운로드 li생성 
			str += "<li><a href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</a>"+//
			 	   "<span data-file=\'" + fileCallPath + "\'data-type='file'> x </span></li>";
		});
		uploadResult.append(str);
	};

	
// ============ 등록 =============
   $('#uploadBtn').on('click', function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		console.log("=== 1 ===");
		console.log(files);
		console.log(files.length);
		
		//업로드 된 파일이 있을때
		if(files.length != 0){
			// formData에 데이터 넣기
			for(var i = 0; i < files.length; i++){
				if(!checkExtentsion(files[i].name, files[i].size)){
					return false;
				}
				if(files[i].type.indexOf('audio') != -1){
					alert("오디오 파일이 포함되어 있습니다.")
					return false;
				}
				formData.append("uploadFile", files[i]);
		    }
		};
		
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
		}).fail(err => console.log(err));	
	});
	
// ============= 삭제 ==============
	$(".uploadResult").on('click', 'span', function(){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		console.log(type);
		var li = $(this).parent();
		
		$.ajax({
			url: '/deleteFile',
			data : {fileName: targetFile, type: type},
			dataType:'text',
			type : 'POST',
			success: function(result){
				alert(result);
				li.remove();
			}
		});//$.ajax
		
	});
	
	
	
	
	
});