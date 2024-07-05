/**
 * 
 */

//조편성 직접입력
 $("#squadNum").change(function(){
	if($("#squadNum").val() == 'direct'){
		$("#squadDirectNum").show();
	}else{
		$("#squadDirectNum").hide();
	}
 });
 
  $("#rotationNum").change(function(){
	if($("#rotationNum").val() == 'direct'){
		$("#rotationDirectNum").show();
	}else{
		$("#rotationDirectNum").hide();
	}
 })
 
 
 
 
 //첫번째 다음버튼 클릭이벤트
  $("#firstNext").click(function(){
	var squadNum = 0;
	var rotationNum = 0;
	//얼럿 초기화
	$(".alertGroup").hide();
	//직접입력값 처리
	if($("#squadNum").val() == 'direct'){
		if($("#squadDirectNum").val() == ''){
			$("#firstAlert").show();
			return
		}
		squadNum = $("#squadDirectNum").val()
	}else{
		squadNum = $("#squadNum").val()
	}
	
	//직접입력값 처리 && 직접입력 값 없을 시 얼럿 
	if($("#rotationNum").val() == 'direct'){
		if($("#rotationDirectNum").val() == ''){
			$("#firstAlert").show();
			return
		}
		rotationNum = $("#rotationDirectNum").val()
	}else{
		rotationNum = $("#rotationNum").val()
	}
	
	//교대주기 값 없을 시 얼럿
	if($("#rotaDay").val() == ''){
		$("#firstAlert").show();
		return
	}
	
	
	
		
	console.log(squadNum,rotationNum )
	
	let tableHtml ;
	let checkHtml ;
	//두번째 페이지 하단표 작성
	
	$("#tbody").empty();	
	for(let i = 1 ; i <= squadNum; i++){
		tableHtml = '<tr data-squadNo='+ i + '>'
		tableHtml += '<td>' + i + '</td>'
		tableHtml += '<td class="empTd" id="'+ i + 'squad"></td>'
		tableHtml += '</tr>'
		$("#tbody").append(tableHtml);
	};
	
	//두번째 페이지 체크박스 작성
	$("#makeCheckBox").empty();
	for(let i = 1 ; i <= squadNum; i++){
		checkHtml = '<label for="'+i+'squad"><input type="radio" name="squadCheck" value=' + i + ' id="' +i+'squad">' + i + '조</label>'
		$("#makeCheckBox").append(checkHtml);
	};
	
	$(".firstStep").hide();
	$(".secondStep").show();
	checkedEmp = [999999]
	empAjax()

  }) //첫번째 다음버튼 클릭이벤트 끝
  
  //첫번째 달력 오늘날짜
  //오늘날짜 구하는 함수
  function getTodayDate(){
		var curDate = new Date();
		var y = curDate.getFullYear();
		var m = curDate.getMonth() + 1;
		var d = curDate.getDate();
		
		var today = y+"-"+(("00"+m.toString()).slice(-2))+"-"+(("00"+d.toString()).slice(-2));
		
		return today;
	}
  //오늘날짜 달력에 입력
  
  $("#startDate").val(getTodayDate());
  
  
 	//두번째 직원목록 아작스처리
 	function empAjax(){
		$.ajax({
			url:'/makeSquad',
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
  
  	let empCount = 0;
  	let checkedEmp = [99999]
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
		empCount += 1;
		console.log(tableHtml)
		checkedEmp.push(empId)
		console.log(checkedEmp)
		$(".empTd:eq("+ (squadNum - 1)+ ")").append(tableHtml)
		empAjax()
		
		console.log(checkedEmp)
		
	}) 
	}////두번째 직원표 클릭 이벤트 끝
	
	//두번째 하단표 직원 x버튼 클릭
	function xBtnClick(){
		$("#tbody").find('span').click(function(e){
		
		let filtered = checkedEmp.filter(function(ele){
				if (ele !== $(e.target).attr('data-empId') ) {
					return ele;
				}; 
			});
		checkedEmp = filtered
		$(e.target).remove();
		
		empAjax()
	})
	}
	
	//두번째 페이지 이전버튼 클릭
	$("#secondPrev").click(function(){
		
		$(".firstStep").show();
		$(".secondStep").hide();
		
	})
	
	
	//두번째 페이지 다음버튼 클릭
	$("#secondNext").click(function(e){
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
		$.ajax({
			url:'/insSquEmp',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(squadEmp)
		})
		.done(function(res){
			
		})
		
	})
  	 
	
	$(function(){
		clickEmp();
		xBtnClick();
		//tagify
		/*const empTd = document.querySelector('input[name=recipient]');
		let tagify = new Tagify(empTd);
		
		tagify.on('add', function() {
		      console.log(tagify.value); // 입력된 태그 정보 객체
		  })*/
		

	})	//$(function) end
  
  