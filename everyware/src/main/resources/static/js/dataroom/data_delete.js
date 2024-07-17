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
		
	   if (selectedDataIds.length > 0) {
			Swal.fire({
			  text: "선택된 자료가 완전히 삭제됩니다. 삭제하시겠습니까?",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "삭제",
			  cancelButtonText: "취소"
			}).then((result) => {
				//삭제버튼 클릭시 실행
				if (result.isConfirmed) {
					deleteDataFileAjax(selectedDataIds);	 
				//취소버튼 클릭시 실행	
			    } else {
				    result.dismiss === Swal.DismissReason.cancel
				}
			});//Swal.fire .then ==============
		} else {
		    alert('선택된 항목이 없습니다.');
		}
        


});

// ======= delete Ajax =======
function deleteDataFileAjax(selectedDataIds){
	$.ajax({
    url: 'deleteGroupIdFiles',
    type: 'post',
    data: JSON.stringify(selectedDataIds),
    contentType: 'application/json',
	})
	.done(fileResult => {
		//삭제 성공
	    if (fileResult) {
	        $.ajax({
	            url: 'deleteData',
	            type: 'post',
	            data: JSON.stringify(selectedDataIds),
	            contentType: 'application/json',
	        })
	        .done(dataResult => {
	            if (dataResult) {
	                swalSuccess();
				    dataList(1, 'common', '공통', '공통자료실');
	            } else {
	                alert("삭제되지 않았습니다.");
	            }
	            console.log(dataResult);
	        });
	    //삭제 실패
	    } else {
	        alert("삭제되지 않았습니다.");
	    }
	    console.log(fileResult);
	});
}


//====== alert =======
function swalSuccess(){
	return Swal.fire({
			      title: "삭제되었습니다.",
			      icon: "success"
			});
}