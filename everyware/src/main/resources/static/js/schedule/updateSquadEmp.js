/**
 * 
 */
	clickEmp();
	empAjax();
	xBtnClick();
	
	//하단표 작성
	
	$("#tbody").empty();	
	for(let i = 1 ; i <= squadNum; i++){
		tableHtml = '<tr data-squadNo='+ i + '>'
		tableHtml += '<td>' + i + '</td>'
		tableHtml += '<td class="empTd" id="'+ i + 'squad"></td>'
		tableHtml += '</tr>'
		$("#tbody").append(tableHtml);
		
	};
	//조편성 테이블 데이터넣기

	squadList.forEach(function(info){
		let empId = info.empId;
		let empName = info.empName +' '+info.empPosition;
		let squadNum = info.squadNo
		let html ='<span data-empId="'+ empId + '">'+ empName +' x</span>';
		
		$(".empTd:eq("+ (squadNum - 1)+ ")").append(html)
		xBtnClick()
		
	})
	
	//체크박스 작성
	$("#makeCheckBox").empty();
	for(let i = 1 ; i <= squadNum; i++){
		checkHtml = '<label for="'+i+'squad"><input type="radio" name="squadCheck" value=' + i + ' id="' +i+'squad">' + i + '조</label>'
		$("#makeCheckBox").append(checkHtml);
	};
	
	

 	//직원표 클릭 이벤트
  	function clickEmp(){
		$(".squadAddEmp").click(function(e){
		
		
		let empId = parseInt($(e.target).closest('tr').attr('data-empId')) ;
		let empName = $(e.target).text();
		let squadNum = $('#makeCheckBox').find('input[name=squadCheck]:checked').val();
		let tableHtml ='<span data-empId="'+ empId + '">'+ empName +' x</span>';
		
		if(squadNum == null){
			alert('조를 선택하여 주세요')
			return;
		}
		console.log(empId)
		checkedEmp.push(empId)
		console.log(checkedEmp)

		$(".empTd:eq("+ (squadNum - 1)+ ")").append(tableHtml)
		empAjax()
		

		
	}) 
	}////두번째 직원표 클릭 이벤트 끝
	
	//두번째 직원목록 아작스처리
 	function empAjax(){
		$.ajax({
			url:'/makeUpdSquad',
			type : 'POST',
			data : {
				empIdList : checkedEmp
			}
		})
		.done(function(res){
			$('#prodEmp').replaceWith(res);
			clickEmp();
			xBtnClick()
		})
	}
	
	
	//두번째 하단표 직원 x버튼 클릭
	function xBtnClick(){
		$("#tbody").find('span').click(function(e){
		
		let filtered = checkedEmp.filter(function(ele){
				if (ele !== parseInt($(e.target).attr('data-empId')) ) {
					return ele;
				}; 
			});
		checkedEmp = filtered
		$(e.target).remove();
		console.log(checkedEmp)
		
		empAjax()
	})
	}
	
	//완료버튼 클릭
	$("#updateBtn").click(function(){
		let squadEmp = []
		let trNum = $("#tbody").children('tr').length;
		
		for(let i = 1 ; i <= trNum ; i++){
			let spanNum = $("#tbody").children("tr:eq(" + (i-1) + ")").find('span').length;
			for(let k = 0 ; k < spanNum ; k++){
				let spanEmpId = $("#tbody").children("tr:eq(" + (i-1) + ")").find('span:eq(' + k + ')').attr('data-empId')
				console.log(spanEmpId);
				squadEmp.push({squadNo : i, empId :spanEmpId})
			}
		}
		
	
		//근무조 편성
		$.ajax({
			url:'/insSquEmp',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(squadEmp)
		})
		.done(function(res){
			alert('변경 완료')
		})
	})
	