/**
 * 
 */

let month = parseInt($("#calMonth").attr("data-month"));
let year = parseInt($("#calYear").attr("data-year"));
let startDate = $("#scheduleCal").attr("data-startDate");
let rotateCircle = $("#scheduleCal").attr("data-rotateCircle");// 교대주기
let rotateNum = $("#scheduleCal").attr("data-rotateNum"); //하루교대수




 setCal();
 makeTable();
 clickEvent();
 
 
//달력 처리
 function setCal(){
	 //달력 년도 처리
	 $("#calYear").text($("#calYear").attr("data-year"));
	 
	 //달력 월 처리
	 $("#calMonth").text($("#calMonth").attr("data-month")); 
 }
 

 //달력 다음버튼
 $("#nextMonth").click(function(){
	month = parseInt($("#calMonth").attr("data-month"));
	year = parseInt($("#calYear").attr("data-year"));
	let nextMonth = ("00"+(month+1).toString()).slice(-2)
	if(nextMonth == "13"){
		nextMonth = "01"
		$("#calYear").attr("data-year", year+1)
	}

	$("#calMonth").attr("data-month", nextMonth)
	setCal();
	makeTable();

 })
 //달력 이전버튼
 $("#prevMonth").click(function(){
	month = parseInt($("#calMonth").attr("data-month"));
	year = parseInt($("#calYear").attr("data-year"));
	let prevMonth = ("00"+(month-1).toString()).slice(-2)
	if(prevMonth == "00"){
		prevMonth = "12"
		$("#calYear").attr("data-year", year-1)
	}

	$("#calMonth").attr("data-month", prevMonth)
	setCal();
	makeTable();
 })
 
 //달력테이블 생성
 function makeTable(){
	$("#calTable").find("thead").find("tr").html("<th style='width:5%'></th>");
	$("#calTbody").html("");
	//날짜 처리
	month = parseInt($("#calMonth").attr("data-month"));
	year = parseInt($("#calYear").attr("data-year"));
	const date = new Date(year, month, 0)
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
	
	var zeroMonth = ("00"+(month).toString()).slice(-2)
	
	
	//몇조인지
	let squadNum = $("#calTbody").attr("data-squadNum");
	
	//테이블 thead
	 for(let i = 1 ; i <=date.getDate()  ; i ++){
		var day = new Date(year, month-1, i).getDay();
		var days = week[day];
		var thHtml = null;
		//일요일 빨간색 배경
		if(days == "일요일"){
			thHtml = "<th style='background-color:red'>" + i + "</th>"
			
		}else{			
			thHtml = "<th>" + i + "</th>"
		}
		
		$("#calTable").find("thead").find("tr").append(thHtml)
 	}
 	//테이블 tbody
 	for(let i = 1 ; i <= squadNum ; i++){
		var html = "<tr>"
		html += "<th>"+ i + "조</th>"
		for(let j = 1 ; j <= date.getDate() ; j++){
			var zeroDay = ("00"+(j).toString()).slice(-2)
			html += "<td data-date=" + year+ "-"+ zeroMonth+"-"+zeroDay + " data-squadNum = " + i + "></td>"
		}
		html += "</tr>"
		
		$("#calTbody").append(html)
	}
 
 	//근무표시
 	for(let i = 1 ; i <= date.getDate(); i++){
		$("#calTbody").find('td:eq('+ i +')').css("backgroud-color" , 'red')
	}
 	
 	
 	clickEvent();
 
 } //makeTable end
 	
 	//테이블 td클릭 이벤트
 	function clickEvent(){
		$("#calTable").find("td").click(function(e){
		let date = new Date($(e.target).attr("data-date"));
		let standard = new Date(startDate);
		
		console.log((date - standard) / (1000 * 60 * 60 * 24));
		
	})
	}
 	
 
 
