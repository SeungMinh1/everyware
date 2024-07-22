/**
 * 근태
 */

$(document).ready(function() {
	// 퇴근 버튼 클릭 시 모달 창 표시
	$("#gohome").click(function() {
		$("#modal1").modal("show");
		let nowTime = new Date();
		let hours = ('0' + nowTime.getHours()).slice(-2);
		let minutes = ('0' + nowTime.getMinutes()).slice(-2);
		let seconds = ('0' + nowTime.getSeconds()).slice(-2);

		let timeString = '현재시각은 ' + hours + '시 ' + minutes + '분 ' + seconds + '초 입니다.';
		$("#modal1p1").text(timeString);
		$("#modal1p2").text("퇴근하시겠습니까?");
	});

	// 출근 버튼 클릭 시 모달 창 표시
	$("#goworkBtn").click(function() {
		$("#modal2").modal("show");
		let nowTime = new Date();
		let hours = ('0' + nowTime.getHours()).slice(-2);
		let minutes = ('0' + nowTime.getMinutes()).slice(-2);
		let seconds = ('0' + nowTime.getSeconds()).slice(-2);

		let timeString = '현재시각은 ' + hours + '시 ' + minutes + '분 ' + seconds + '초 입니다.';

		$("#modal2p1").text(timeString);
		$("#modal2p2").text("출근하시겠습니까?");
	});

});

 (function() {
        if('geolocation' in navigator) {
            /* 위치정보 사용 가능 */
        } else {
            /* 위치정보 사용 불가능 */
        }

        const currentGeoLocation = document.getElementById("currentGeoLocation");
		
        currentGeoLocation.onclick = function() {
        	$("#goworkBtn").prop("disabled", true);
        	$("#modal2").hide();
        	$("#modal2").modal('hide');
        	
        	document.getElementById("goworkBtn").disabled = true;
        	document.getElementById("gohome").disabled = false;
            var startPos;
            var geoOptions = {
                timeout: 10 * 1000
            };
            

            var geoSuccess = function (position) {

                // Do magic with location
                startPos = position;
                
                data1 = {             
            		xxx : startPos.coords.latitude,    
            		yyy : startPos.coords.longitude
            		
            	};
                $.ajax({
                    url: "/findgps", 
                    type: 'post',
					contentType : 'application/JSON',
					data : JSON.stringify(data1)
                })
                .done(function(distance) {
                	document.getElementById('dis').innerHTML =  (distance * 1000).toFixed() + 'm';
                	
                	if(distance > 0.1){
                		alert("출근 실패");
                	}else{
                		let nowTime = new Date();
                		workdata = {             
                			attendType : "출근",
                			goTime : nowTime
		            	};
		            	
                		$.ajax({
                   		url: "/gowork", 
	                    type: 'post',
						contentType : 'application/JSON',
						data : JSON.stringify(workdata)
                	})
                	.done(function(result){
                		//alert("출근 성공")
                		
                		//출근시간조회
               			$.ajax({
                   		url: "/findattend", 
	                    type: 'post',

	                	})
	                	.done(function(result){
	                		console.log(result.goTime);
	                		
	                		let hours = ('0' + nowTime.getHours()).slice(-2); 
	                		let minutes = ('0' + nowTime.getMinutes()).slice(-2);
	                		let seconds = ('0' + nowTime.getSeconds()).slice(-2); 

	                		let timeString = hours + ':' + minutes  + ':' + seconds;

	                		console.log(timeString);
	                		document.getElementById('startTime').innerHTML = timeString;
	                		
	                	})
	                	.fail(function(result){
	                		console.log("err");
	                	})
                	
                		
                	})
                	.fail(function(result){
                		console.log("err");
                	})
                	
                	
                	}
                })
                .fail(function(xhr, status, errorThrown) {
                    alert("error");
                })
                
            };
            var geoError = function (error) {
                console.log('Error occurred. Error code: ' + error.code);
                // error.code can be:
                //   0: unknown error
                //   1: permission denied
                //   2: position unavailable (error response from location provider)
                //   3: timed out
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                         alert("error");
                        break;
                    case error.POSITION_UNAVAILABLE:
                         alert("error");
                        break;
                    case error.TIMEOUT:
                         alert("error");
                        break;
                };
            };

            navigator.geolocation.getCurrentPosition(geoSuccess, geoError, geoOptions);
        };
        
        //퇴근버튼
        const currentGeoLocation2 = document.getElementById("currentGeoLocation2");

        currentGeoLocation2.onclick = function() {
        	$("#gohome").prop("disabled", true);
        	$("#modal1").hide();
        	$("#modal1").modal('hide');
            var startPos;
            var geoOptions = {
                timeout: 10 * 1000
            };

            var geoSuccess = function (position) {

                // Do magic with location
                startPos = position;
                
                data1 = {             
            		xxx : startPos.coords.latitude,    
            		yyy : startPos.coords.longitude
            	};
                $.ajax({
                    url: "/findgps", 
                    type: 'post',
					contentType : 'application/JSON',
					data : JSON.stringify(data1)
                })
                .done(function(distance) {
                	document.getElementById('dis').innerHTML =  (distance * 1000).toFixed() + 'm';
                	if(distance > 0.1){
                		alert("퇴근 실패");
                	}else{
                		let nowTime2 = new Date();
                		workdata2 = {             
                			attendType : "퇴근",
                			leaveTime : nowTime2
		            	};
		            	
                		$.ajax({
                       		url: "/endwork", 
                            type: 'post',
        					contentType : 'application/JSON',
        					data : JSON.stringify(workdata2)
        					
                    	})
                    	.done(function(result){
                    		//alert("퇴근 성공")
                    		
                    		let hours = ('0' + nowTime2.getHours()).slice(-2); 
                    		let minutes = ('0' + nowTime2.getMinutes()).slice(-2);
                    		let seconds = ('0' + nowTime2.getSeconds()).slice(-2); 

                    		let timeString = hours + ':' + minutes  + ':' + seconds;

	                		console.log(timeString); //endtimeli
	                		//$('#endTime').innerHTML = timeString;
	                		$("#endtimeli").append("<a class='float-right'>" + timeString +"</a>");
                    		
	                		$.ajax({
	                       		url: "/exwork", 
	                            type: 'post',
	                    	})
	                    	.done(function(result){
	                    		console.log("성공");
	                    	})
	                		
	                		
                    	})
                    	.fail(function(result){
                    		console.log("err");
                    	})
                    	
                		
                	}
                })
                .fail(function(xhr, status, errorThrown) {
                    alert("error");
                })
                
            };
            var geoError = function (error) {
                console.log('Error occurred. Error code: ' + error.code);
                // error.code can be:
                //   0: unknown error
                //   1: permission denied
                //   2: position unavailable (error response from location provider)
                //   3: timed out
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                         alert("error");
                        break;
                    case error.POSITION_UNAVAILABLE:
                         alert("error");
                        break;
                    case error.TIMEOUT:
                         alert("error");
                        break;
                };
            };

            navigator.geolocation.getCurrentPosition(geoSuccess, geoError, geoOptions);
        };
        
        
    })();



