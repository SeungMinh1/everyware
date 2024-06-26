/**
 *  mail_update.js
 */
$(function(){
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
    
	
	
	//insertBtn 누르면 보내기 등록
  /*================
      메일 보내기
  =================*/
  $('#insertBtn').on('click', mailInsertFunc);
  function mailInsertFunc(e){
	  // value를 담은 data => info
	  let info =  getMailInfo();
	  	
	  	//받는사람, 제목 유효성 검사
	    if(tagify.value == ''){
			alert('받는사람 이메일을 입력해 주세요.');
			/* input.focus(); */
			return;
		};
		if($('#title').val() == ''){
			alert('제목을 입력해주세요.');
			$('#title').focus();
			return;
		};
	
	  //메일 등록 ajax
		$.ajax('mailInsert',{
			type: 'post',
			contentType : 'application/JSON',
			data : JSON.stringify(info)
		})
		.done(result=>{
			if(result){
				alert('성공');
				//성공시 받은메일함으로
				let url = '/mailboxInfo?mailboxId=d1';
				location.href=url;
			}
			console.log(result);
		})
		.fail(err=>console.log(err));
  }; 
   /*================
       임시보관 수정
  =================*/
  $('#updateBtn').on('click', draftMailUpdate);
		function draftMailUpdate(event){
			//1) 보낼 데이터 확인
			let info = getMailInfo();
			($('#sender').val());
			
			//2)AJAX
			$.ajax('draftMailUpdate',{
				type: 'post',
				contentType : 'application/JSON',
				data : JSON.stringify(info)
			})
			.done(result=>{
				if(result){
					alert('정상적으로 수정되었습니다.')
				}else{
					alert("수정되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
			.fail(err=>console.log(err));
		};
			
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
	//draftBtn 누르면 수정한거 저장
})