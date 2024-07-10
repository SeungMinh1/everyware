/**
 * 
 */

let month = parseInt($("#calMonth").attr("data-month"));
let year = parseInt($("#calYear").attr("data-year"));
let startDate = $("#scheduleCal").attr("data-startDate");
let rotateCircle = $("#scheduleCal").attr("data-rotateCircle");// 교대주기
let rotateNum = $("#scheduleCal").attr("data-rotateNum"); //하루교대수
let squadNum = parseInt($("#calTbody").attr("data-squadNum")); //총 조 수




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
	 squadNum = parseInt($("#calTbody").attr("data-squadNum"));
	
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
			html += "<td data-date=" + year+ "-"+ zeroMonth+"-"+zeroDay + " data-squNum = " + i + "></td>"
		}
		html += "</tr>"
		
		$("#calTbody").append(html)
	}
 	
	let standard = new Date(startDate);
 	//근무표시
 	/*if((((new Date($("#calTable").find("td:eq(9)").attr("data-date")) - standard)/(1000 * 60 * 60 * 24))%squadNum )-$("#calTable").find("td:eq(9)").attr("data-squadNum") == -1){
		
		console.log(((new Date($("#calTable").find("td:eq(9)").attr("data-date")) - standard)));
	}
 	
 	if($("#calTable").find("td").attr("data-squadNum") == 1){
		$(this).attr("class", "rotate1")
	}*/
	
	
	for(let i = 0 ; i < date.getDate()*squadNum ; i++){
		let dataDate = $("#calTable").find("td:eq("+ i +")").attr("data-date");
		let dataSquadNum =  $("#calTable").find("td:eq("+ i +")").attr("data-squNum")
		let diff = Math.floor(((new Date(dataDate) - standard)/ (1000 * 60 * 60 * 24))/rotateCircle);
		let result = (diff % squadNum) - dataSquadNum
		if(diff >= 0){	
			if(rotateNum == 2){
				if(result == -1){
					$("#calTable").find("td:eq("+ i +")").attr("class", "rotate1")
					let rotate1Squad = parseInt($("#calTable").find("td:eq("+ i +")").attr("data-squNum"));
					
					let rotate2Squad = (rotate1Squad - 1) <= 0 ?  ((rotate1Squad - 1) + squadNum) : (rotate1Squad - 1)
					console.log(rotate2Squad)
					$("#calTable").find("[data-squNum="+rotate2Squad+"][data-date="+ dataDate+"]").attr("class", "rotate2")
					
				}
		
				$(".rotate1").text("주간")
				$(".rotate2").text("야간")
				
			}else if(rotateNum == 3){
				if(result == -1){
					$("#calTable").find("td:eq("+ i +")").attr("class", "rotate1")
					
					let rotate1Squad = parseInt($("#calTable").find("td:eq("+ i +")").attr("data-squNum"));
					
					let rotate2Squad = (rotate1Squad - 1) <= 0 ?  (rotate1Squad - 1) +squadNum : (rotate1Squad - 1)
					$("#calTable").find("[data-squNum="+rotate2Squad+"][data-date="+ dataDate+"]").attr("class", "rotate2")
					
					let rotate3Squad = (rotate1Squad - 2) <= 0 ?  (rotate1Squad - 2)+ squadNum : (rotate1Squad - 2)
					$("#calTable").find("[data-squNum="+rotate3Squad+"][data-date="+ dataDate+"]").attr("class", "rotate3")
					
					
				}
				
				$(".rotate1").text("오전")
				$(".rotate2").text("오후")
				$(".rotate3").text("야간")
			}
		}
			
	}
 	
 	clickEvent();	
 
 } //makeTable end
 	
 	//테이블 td클릭 이벤트
 	function clickEvent(){
		$("#calTable").find("td").click(function(e){
		date = new Date($(e.target).attr("data-date"));
		standard = new Date(startDate);
		
		console.log((date - standard) / (1000 * 60 * 60 * 24));
		
		
	})
	}
	
	$(document).ready(function(){
		$('#savePdf').click(function() { // pdf저장 button id
		
	    html2canvas($('#calTable')[0]).then(function(canvas) { //저장 영역 div id
	    // 캔버스를 이미지로 변환
	    //canvas.setAttribute("width", "800px");

	    console.log(canvas)
	    var imgData = canvas.toDataURL('image/png');
	    
		     
	    var imgWidth = 190; // 출력 페이지 세로 길이 계산 A4 기준
	    var imgHeight = canvas.height * imgWidth / canvas.width;
	    
	    var pageHeight = imgWidth * 1.414;  // 이미지 가로 길이(mm) / A4 기준 210mm
	    var heightLeft = imgHeight;
	    var margin = 1; // 출력 페이지 여백설정
	    var doc = new jsPDF('l', 'mm');
	    var position = 1;
	    
	    //스케일
	    /*doc.scale(0.5, 1);*/
	    // 첫 페이지 출력
	    doc.addImage(imgData, 'PNG', 10, 10,  200, 200);
	    
	    
	   
	 
	    // 파일 저장
	    doc.save('file-name.pdf');

		  
		});
		
		/*html2canvas($("#calTable"), {
			onrendered: function (canvas) {
				// create intermediate canvas
		    var rotCanvas = document.createElement("canvas");
		
		    // swap width and height
		    rotCanvas.width = canvas.height;
		    rotCanvas.height = canvas.width;
		
		    // get context
		    var rctx = rotCanvas.getContext("2d");
		
		    // translate to center (rotation pivot)
		    rctx.translate(rotCanvas.width * 0.5, rotCanvas.height * 0.5);
		
		    // rotate -90° (CCW)
		    rctx.rotate(-Math.PI * 0.5);
		
		    // draw image offset so center of image is on top of pivot
		    rctx.drawImage(canvas, -canvas.width * 0.5, -canvas.height * 0.5);
		
		    // extract image from rotate canvas
		    var data = rotCanvas.toDataURL('image/png');
		
		    var image = new Image();
		    image.src = data;
			}
			
		}*/
			
		
		
		});
	})
 	
 
 
