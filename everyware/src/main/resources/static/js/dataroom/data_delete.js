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
        
        
        
        /*if (selectedDataIds.length > 0) {
            $.ajax({
            	url: 'deleteData',
            	type : 'post',
                data: JSON.stringify(selectedDataIds),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('삭제되었습니다.')
					window.location.reload();
				}else{
					alert("삭제되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
        } else {
            alert('선택된 항목이 없습니다.');
        }*/
});