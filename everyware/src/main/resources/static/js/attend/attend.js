/**
 * 
 */

//
	//$("#lastMonth").on('click', goLastMonth);
	$(document).ready(showdata)


	function showdata(){
		let data = {
				empId : '1'
		}
		$.ajax({
       		url: "/lastWeekAll", 
       		type : 'post'
       		, contentType : 'application/JSON'
       		, data : JSON.stringify(data)
			
		})
  		.done(function(result){
    		console.log(result);
    		//$("#mainSector").innerHTML = '';
    		document.getElementById('mainSector').innerHTML = '';
    		let html = "";
		    html +=	'<div><h2>heheheh</h2></div>';
		    

          	
          	
		    $('#mainSector').append();	
		
    		
    	})
    	.fail(function(result){
    		console.log("err");
    	})
    	
	}


