/**
 *  data_delete
 */

//dataroom.html 
 $('.deleteBtn').on('click', function() {
        // 선택된 체크박스들의 data_id를 찾기
        let selectedDataIds = [];
        $('.oneCheckbox:checked').each(function() {
            selectedDataIds.push($(this).val());
        });
		console.log(selectedDataIds);
		
		
	 if (selectedDataIds.length > 0) {
   			 // 첨부파일 삭제 AJAX 요청
		    deleteFiles(selectedDataIds)
		    .done(fileResult => {
		        if (fileResult) {
		            // 파일 삭제 성공 시 데이터 삭제 AJAX 요청
		            $.ajax({
		                url: 'deleteData',
		                type: 'post',
		                data: JSON.stringify(selectedDataIds),
		                contentType: 'application/json',
		            })
		            .done(dataResult => {
		                if (dataResult) {
		                    alert('데이터 및 파일이 삭제되었습니다.');
		                    window.location.reload();
		                } else {
		                    alert("데이터가 삭제되지 않았습니다. \n 데이터를 확인해주세요.");
		                }
		                console.log(dataResult);
		            });
		        } else {
		            alert("파일이 삭제되지 않았습니다. \n 데이터를 확인해주세요.");
		        }
		        console.log(fileResult);
		    });
		} else {
		    alert('선택된 항목이 없습니다.');
		}
        
	// 첨부파일 삭제 함수
	function deleteFiles(selectedDataIds) {
	    return $.ajax({
	        url: 'deleteGroupIdFiles',
	        type: 'post',
	        data: JSON.stringify(selectedDataIds),
	        contentType: 'application/json',
	    });
	} 

});