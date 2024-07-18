/**
 *  mail_update.js
 * 	====임시저장====
 */
var tagify;
var tagify1;

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
	  tagify = new Tagify(input, {
	    	pattern  :/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
	  }); 	
	  
	  const input1 = document.querySelector('input[name=cc]');
	  tagify1 = new Tagify(input1, {
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
	  let info =  getMailInfo2();
	  	console.log(info)
	  	console.log(mailId);
	  	//받는사람, 제목 유효성 검사
	    if(tagify.value == ''){
			Swal.fire('받는사람 이메일을 입력해 주세요.');
			 input.focus(); 
			return;
		};
		if($('#title').val() == ''){
			Swal.fire('제목을 입력해주세요.');
			$('#title').focus();
			return;
		};
		
	    if(fileList.length > 0){
			info.attachmentGroupId = fileList[0].attachmentGroupId; 
		}
		
	  //메일 등록 ajax
		$.ajax('mailInsert',{
			type: 'post',
			contentType : 'application/JSON',
			data : JSON.stringify(info)
		})
		.done(result=>{
			if(result){
				Swal.fire({
						title: '메일을 성공적으로 발송하였습니다.',
						icon: "success"
				})
				.then((result)=>{
					if (result.isConfirmed) {
						let url = '/mailboxInfo?mailboxId=d2';
						location.href=url;
						let info = {mailId : mailId};
						$.ajax({
								type: "POST",
				                url: "deleteDraftMailInfo",
				                contentType : 'application/JSON',
								data : JSON.stringify(info)
						})
						.done(result=>{
							console.log(result);
						})
					}
				});
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
			
			if(fileList.length > 0){
				info.attachmentGroupId = fileList[0].attachmentGroupId; 
			}
		
			//2)AJAX
			$.ajax('draftMailUpdate',{
				type: 'post',
				contentType : 'application/JSON',
				data : JSON.stringify(info)
			})
			.done(result=>{
				if(result){
					Swal.fire({
						title: '정상적으로 수정되었습니다.',
						icon: "success"
					})
					.then((result)=>{
						if (result.isConfirmed) {
							let url = '/mailboxInfo?mailboxId=d3';
							location.href=url;
						}
					})
				}else{
					Swal.fire("수정 실패하였습니다.")
				}
			})
			.fail(err=>console.log(err));
		};
			
/*=============== [ getMailInfo ] ===============
    임시저장 후 ====> 다시 임시저장하는 input value 저장
===============================================*/
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
		content    : $('#summernote').summernote('code'),//code로 저장
		attachList : fileList
	};
	
	return data1;
  };
	//draftBtn 누르면 수정한거 저장

/*=============== [ getMailInfo ] ===============
    임시저장 후 ====> 메일 보내는 input value 저장
===============================================*/
  function getMailInfo2(){
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
		sender     : $('#sender').val(),           
		recipList  : recipData,  
		ccList 	   : ccData,          
		title      :  $('#title').val(),            
		content    : $('#summernote').summernote('code'),//code로 저장
		empId	   : empId,
		attachList : fileList
	};
	
	return data1;
  };
})






//주소록 모달 =====================
//tagify.addTags(["banana@dc.com", "orange@cd.com", "apple@dc.com"]);
//체크박스 배열로 받아서 tagify.addTags에 배열로 넣기

	$('.addressInRecipBtn').on('click', function() {  
			let selectedMail = [];
			$('.oneCheckbox:checked').each(function() {
			    selectedMail.push($(this).val());
			});
			tagify.addTags(selectedMail);
			$('#exampleModal').modal('hide');
	});
	
	$('.addressInCCBtn').on('click', function() {  
			let selectedMail = [];
			$('.oneCheckbox:checked').each(function() {
			    selectedMail.push($(this).val());
			});
			tagify1.addTags(selectedMail);
			$('#exampleModal').modal('hide');
	});
	
function addressSearch(page) {
  $('#tbody').empty();
  let searchType = $('#searchType').val();
  let searchKeyword = $('#searchKeyword').val();

console.log(searchType);
console.log(searchKeyword);
  $.ajax({
    url: 'empSearch',
    data: {
      page: page,
      cnt: 8,
      searchOption: searchType,
      dosearch: searchKeyword
    }
  })
  .done(result => {
	console.log(result.pg)
	console.log(result.empList)
	
	let rowCount = 0;
	
    $(result.empList).each(function(i, obj) {
      let tr = `<tr>
      				<td>
	                     <div class="icheck-primary">
	                       <input type="checkbox" value="${obj.mail}" id="${obj.empId}" class="oneCheckbox">
	                       <label for="${obj.empId}"></label>
	                     </div>
	                </td>
                    <td>${obj.empName}</td>
                    <td>${obj.mail}</td>
                    <td>${obj.departmentName}</td>
                </tr>`;
      $('#tbody').append(tr);
      rowCount++;
    });

	for (let i = rowCount; i < 8; i++) {
	      let emptyRow = `<tr>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                      </tr>`;
	      $('#tbody').append(emptyRow);
	}
    // 페이징 처리
    let pg = result.pg;
    let paginationHtml = '';
    if (pg.prev) {
      paginationHtml += `<li class="page-item">
                           <a class="page-link" href="#" onclick="addressSearch(${pg.startPage - 1})" aria-label="Previous">
                             <span aria-hidden="true">&laquo;</span>
                           </a>
                         </li>`;
    }
    for (let i = pg.startPage; i <= pg.endPage; i++) {
      paginationHtml += `<li class="page-item ${page === i ? 'active' : ''}">
                           <a class="page-link" href="#" onclick="addressSearch(${i})">${i}</a>
                         </li>`;
    }
    if (pg.next) {
      paginationHtml += `<li class="page-item">
                           <a class="page-link" href="#" onclick="addressSearch(${pg.endPage + 1})" aria-label="Next">
                             <span aria-hidden="true">&raquo;</span>
                           </a>
                         </li>`;
    }
    $('.pagination').html(paginationHtml);
  });
}