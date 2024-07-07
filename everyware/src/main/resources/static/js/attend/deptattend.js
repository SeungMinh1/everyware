/**
 *  부서별 근태관리
 */

 $(document).ready(showdata)
 
 function showdata(){
	
	$.ajax({
       		url: "/selectDept", 
       		type : 'post'
       		, contentType : 'application/JSON'
       		//, data : JSON.stringify(data)
			
		})
  		.done(function(result){
			console.log(result);
				
			$('#mainSector').empty();
			
			
			let html = "";
			
			result.forEach(function(emp, index){
				
			html += '<tr class="text-center">'
			html += '<td>'+ emp.empName +'</td>';
			html += '<td>'+ emp.departmentName +'</td>'
			html += '<td>'+ emp.posName +'</td>';
			if(emp.attendType == null){
				html += '<td>' + '출근안했음'+'</td>'
			}else{
				html += '<td>'+ emp.attendType +'</td>'
			}
			if(emp.goTime2 == null){
				html += '<td>' + '출근시간'+'</td>'
			}else{
				html += '<td>'+ emp.goTime2 +'</td>'
			}
			if(emp.leaveTime == null){
				html += '<td>' + '퇴근시간'+'</td>'
			}else{
				html += '<td>'+ emp.leaveTime +'</td>'
			}
			if(emp.workTime == null){
				html += '<td>' + '일한시간'+'</td>'
			}else{
				html += '<td>'+ emp.workTime +'</td>'
			}
			if(emp.exceedWorkTime == null){
				html += '<td>' + '일한시간'+'</td>'
			}else{
				html += '<td>'+ emp.exceedWorkTime +'</td>'
			}
			if(emp.workDetail== null){
				html += '<td>' + '지각했나'+'</td>'
			}else{
				html += '<td>'+ emp.workDetail +'</td>'
			} 
			
			html += '</tr>'
				
			})
			
			html += '<tr>'
			html += '<td></td>'
			html += '</tr>'
			
			
			
			$('#mainSector').append(html);

    		
    	})
    	.fail(function(result){
    		console.log("err");
    	})
	

	
	
	
 }
 
 
 