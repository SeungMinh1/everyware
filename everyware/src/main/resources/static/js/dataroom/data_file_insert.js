/**
 * 	data_file_insert.js
 */

$(function () {
//=========== 크기&확장자 제한 ============
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 10 * 1024 * 1024; // 10MB

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
    
// 등록한파일의 result값을 담는 변수     
var fileList =[];

//
function uploadFile(){
	var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		
		console.log("=== 1 ===");
		console.log(files);
		
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
				console.log("=== 2 ===");
				console.log(result);
				
				//업로드된 파일 tr로 보여줌
				showUploadFile(result);
				
				/*=================================================
				 result를 fileList에 담음 => fileList는 위에 전역으로 선언함.
				===================================================*/
				fileList.push(...result);
				
				//input 파일선택 초기화
				$(".uploadDiv").html(cloneInputFileDiv.html());
				
				//input 파일을 새로 그려줬기 때문에 다시 change를 걸어줘야함.
				$("input[type='file']").change(function(){
						uploadFile();
				})
	
			}
		}).fail(err => console.log(err));	
}
//====== 파일이 올라갔을때 바로 출력 =======
	$("input[type='file']").change(function(){
		uploadFile();
	})
	
// ====== 파일첨부 input 복사 ( 초기화 목적 ) ==========
	var cloneInputFileDiv = $(".uploadDiv").clone();
	
// ====== 미리보기 ( tbody 태그 밑에 tr td 생성 ) ======
	var uploadResult = $(".uploadResult tbody");
	
	//ajax -> result 받아와서 -> each 
	function showUploadFile(uploadResultArr){
		var str = "";
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			// 파일 경로 ( 다운로드 할때 필요 )
			var fileCallPath = encodeURIComponent(//
				obj.uploadPath +"/"+ obj.uploadFileName + "_" + obj.originFileName);
		    // 2024/7/3 + "/"" + UUID(uploadFileName) + "_" + test1.png
			
			/*			
			// 미리보기 tr td생성
			str += "<tr>"
			str += "<td>" + obj.originFileName 
			str += "</td>"
			str += "<td>" + obj.fileSize + "</td>"
			str += "<td>" + obj.ext + "</td>"
			str += "<td>"+ "<span data-file=\'"+ fileCallPath +"\'data-type='file'> x </span>"+ "</td>"			
			str += "</tr>";
			*/
			
			// 다운로드 tr td 생성 
			str += "<tr>"
			str += "<td>"
			str += "<a href='/download?fileName=" + fileCallPath +"'>"+ obj.originFileName + "</a>"
			str += "</td>"
			str += "<td>" + obj.fileSize + "</td>"
			str += "<td>" + obj.ext + "</td>"
			str += "<td>"+ "<span data-file=\'" + fileCallPath + "\'data-type='file'> x </span>"+ "</td>"
			str += "</tr>";
		});
		uploadResult.append(str);
	};

/*===============
     자료실 등록
================*/
$('.insertBtn').on('click', function(){
		$('#fileTitle').val();
		console.log($('.dataSelect option:selected').val());
		console.log($('#fileTitle').val());
		
		var insertData = {
			title : $('#fileTitle').val(),
			remarks : $('.dataSelect option:selected').val(),
			attachList : fileList
		}
		
		if(fileList.length > 0){
			insertData.attachmentGroupId = fileList[0].attachmentGroupId; 
		}
		
		$.ajax('insertData',{
			type: 'post',
			contentType : 'application/JSON',
			data : JSON.stringify(insertData)
		})
		.done(result=>{
			if(result){
				alert('등록');
			}
			console.log(result);
			modalEmpty();
			
		})
		.fail(err=>console.log(err));
	})	
// ============= 삭제 ==============
	$(".uploadResult").on('click', 'span', function(){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		console.log(type);
		var tr = $(this).parent().parent();
		
		$.ajax({
			url: '/deleteFile',
			data : {fileName: targetFile, type: type},
			dataType:'text',
			type : 'POST',
			success: function(result){
				alert(result);
				tr.remove();
			}
		});//$.ajax
		
	});

//===모달 닫을때 초기화===
$('.closeModal').on('click',function(){
	modalEmpty();
});

function modalEmpty(){
	$('#fileTitle').val('');
	$('select[name="dataSelect"]').val("개인");
	$('.fileTable tbody').empty();
};
	
	
});