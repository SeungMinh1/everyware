/**
 * 
 */
	clickEmp();
	
	//두번째 페이지 체크박스 작성
	$("#makeCheckBox").empty();
	for(let i = 1 ; i <= squadNum; i++){
		checkHtml = '<label for="'+i+'squad"><input type="radio" name="squadCheck" value=' + i + ' id="' +i+'squad">' + i + '조</label>'
		$("#makeCheckBox").append(checkHtml);
	};

 	//두번째 직원표 클릭 이벤트
  	function clickEmp(){
		$(".squadAddEmp").click(function(e){
		
		
		let empId = $(e.target).closest('tr').attr('data-empId') ;
		let empName = $(e.target).text();
		let squadNum = $('#makeCheckBox').find('input[name=squadCheck]:checked').val();
		let tableHtml ='<span data-empId="'+ empId + '">'+ empName +' x</span>';
		
		if(squadNum == null){
			alert('조를 선택하여 주세요')
			return;
		}

		checkedEmp.push(empId)

		$(".empTd:eq("+ (squadNum - 1)+ ")").append(tableHtml)
		empAjax()
		

		
	}) 
	}////두번째 직원표 클릭 이벤트 끝
	
	