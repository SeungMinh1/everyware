/**
 * 
 */
$(function () {
	//에디터(summernote) 설정
   $('#summernote').summernote({
	    		height: 300
	    		, lang: 'ko-KR'
	    		, callbacks: {
		        	onImageUpload: function(files, editor, welEditable) {
		        		for(var i = files.length -1; i>=0; i--) {
		        			sendFile(files[i], this);
		        		}
		        	}
		        }
	  });
	  
	  const input = document.querySelector('input[name=recipient]');
	  let tagify = new Tagify(input, {
	    	pattern  :/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
	  }); 	
	  
	  const input1 = document.querySelector('input[name=cc]');
	  let tagify1 = new Tagify(input1, {
	    	pattern  :/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
	  }); 	
	  
	  tagify.on('add', function() {
      	console.log(tagify.value); // 입력된 태그 정보 객체
  	  })
  	  
 	$('.moveTrashInfoBtn').on('click', function(){
		$.ajax({
            	url: 'moveTrashMailInfo',
                type: 'POST',
                data: JSON.stringify({mailId : mailId}),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('휴지통으로 이동되었습니다.')
					let url = '/mailboxInfo?mailboxId=d1';
					location.href=url;
				}else{
					alert("이동되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
		});
		
  /*================
    input value 저장
  ==================*/
  function getMailInfo(){
	//recipient 의 value를 push => recipData에 담음.
	//tagify.value == 받는사람 이메일
	var recipData = [];
    $.each(tagify.value, (idx, input) => {
			var data = input.value;
	  		recipData.push(data);
	});
	
	var ccData = [];
    $.each(tagify1.value, (idx, input) => {
			var data = input.value;
	  		ccData.push(data);
	});
	
    //각 input에 value를 배열로 data1 저장 후 return
	var data1 = {            
		mailId : mailId,
		sender     : $('#sender').val(),           
		recipList  : recipData,  
		ccList 	   : ccData,          
		title      :  $('#title').val(),            
		content    : $('#summernote').summernote('code')//code로 저장
	};
	
	return data1;
  };
});