/**
 *  부서별 근태관리
 */

 $(document).ready(showdata)
 
 function showdata(){
	let date1 = {
		newdate : ''
	}
	
	$.ajax({
       		url: "/selectDept", 
       		type : 'post'
       		, contentType : 'application/JSON'
       		, data : JSON.stringify(date1)
			
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
				html += '<td>' + '출근전'+'</td>'
			}else{
				html += '<td>'+ emp.attendType +'</td>'
			}
			if(emp.goTime2 == null){
				html += '<td>'+'</td>'
			}else{
				html += '<td>'+ emp.goTime2 +'</td>'
			}
			if(emp.leaveTime2 == null){
				html += '<td>'+'</td>'
			}else{
				html += '<td>'+ emp.leaveTime2 +'</td>'
			}
			if(emp.workTime == 0){
				html += '<td></td>'
			}else if(emp.workTime < 60){
				html += '<td>'+ emp.workTime +'분</td>'
			}else{
				html += '<td>'+Math.floor(emp.workTime/60) + '시간'+emp.workTime%60+'분' +'</td>'
			}
			if(emp.exceedWorkTime == 0){
				html += '<td></td>'
			}else if(emp.exceedWorkTime < 60){
				html += '<td>'+ emp.exceedWorkTime +'분</td>'
			}else{
				html += '<td>'+Math.floor(emp.exceedWorkTime/60) + '시간'+emp.exceedWorkTime%60+'분' +'</td>'
			}
			if(emp.workDetail == "normal"){
				html += '<td>정상출근</td>'
			}else if(emp.workDetail == 'late'){
				html += '<td>지각</td>'
			} else{
				html += '<td></td>'
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
 

$(function() {
  $('input[name="daterange"]').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    minYear: 1901,
    maxYear: parseInt(moment().format('YYYY'),10)
  }, function(start, end, label) {
	
	let newdate = start.format('YYYYMMDD')
	let date1 = {
		newdate : newdate
	}
     console.log("date: " + newdate);
     
     	$.ajax({
       		url: "/selectDept", 
       		type : 'post'
       		, contentType : 'application/JSON'
       		, data : JSON.stringify(date1)
			
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
				html += '<td>' + '출근전'+'</td>'
			}else{
				html += '<td>'+ emp.attendType +'</td>'
			}
			if(emp.goTime2 == null){
				html += '<td>'+'</td>'
			}else{
				html += '<td>'+ emp.goTime2 +'</td>'
			}
			if(emp.leaveTime2 == null){
				html += '<td>'+'</td>'
			}else{
				html += '<td>'+ emp.leaveTime2 +'</td>'
			}
			if(emp.workTime == 0){
				html += '<td></td>'
			}else if(emp.workTime < 60){
				html += '<td>'+ emp.workTime +'분</td>'
			}else{
				html += '<td>'+Math.floor(emp.workTime/60) + '시간'+emp.workTime%60+'분' +'</td>'
			}
			if(emp.exceedWorkTime == 0){
				html += '<td></td>'
			}else if(emp.exceedWorkTime < 60){
				html += '<td>'+ emp.exceedWorkTime +'분</td>'
			}else{
				html += '<td>'+Math.floor(emp.exceedWorkTime/60) + '시간'+emp.exceedWorkTime%60+'분' +'</td>'
			}
			if(emp.workdetail == "normal"){
				html += '<td>정상출근</td>'
			}else if(emp.workdetail == "late"){
				html += '<td>지각</td>'
			} else{
				html += '<td></td>'
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

     
     
  });
});
 
 
 
 
 
 
 
 
 
 