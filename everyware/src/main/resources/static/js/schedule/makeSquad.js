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
	
	if($("#rotationNum").val() == 'direct'){
		if($("#rotationDirectNum").val() == ''){
			$("#firstAlert").show();
			return
		}
		rotationNum = $("#rotationDirectNum").val()
	}else{
		rotationNum = $("#rotationNum").val()
	}
	
	console.log(squadNum,rotationNum )
	
	let tableHtml ;
	let checkHtml ;
	//두번째 페이지 상단표 작성
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
		checkHtml = '<input type="radio" name="squadCheck" value=' + i + ' id="' +i+'squad"><label for="'+i+'squad">' + i + '조'
		$("#makeCheckBox").append(checkHtml);
	};
	
  }) //첫번째 다음버튼 클릭이벤트 끝
  
  
  	let empCount = 0;
  	let checkedEmp = [0]
  	//두번째 직원표 클릭 이벤트
  	function clickEmp(){
		$(".squadAddEmp").click(function(e){
		
		
		let empId = $(e.target).closest('tr').attr('data-empId') ;
		let empName = $(e.target).text();
		let squadNum = $('#makeCheckBox').find('input[name=squadCheck]:checked').val();
		let tableHtml ='<span data-empId="'+ empId + '">'+ empName +' <i class="fa-solid fa-xmark xButton"></i></span>';
		
		if(squadNum == null){
			alert('조를 선택하여 주세요')
			return;
		}
		empCount += 1;
		console.log(tableHtml)
		checkedEmp.push(empId)
		console.log(checkedEmp)
		$(".empTd:eq("+ (squadNum - 1)+ ")").append(tableHtml)
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
			
		})
		
		
		
	}) 
	}////두번째 직원표 클릭 이벤트 끝
	
	//두번째 상단표 직원 x버튼 클릭
	function xBtnClick(){
		$("#tbody").find('span').click(function(e){
		console.log(e.target)
	})
	}
	
  	 
	
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
  
  