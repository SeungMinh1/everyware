/**
 * mail_insert.js
 * KJM
 */
var tagify;
var tagify1;

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
	    })
 
 	
  //tagify 이메일 유효성 검사
  const input = document.querySelector('input[name=recipient]');
    tagify = new Tagify(input, {
    	pattern  :/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
  }); 	
  
  const input1 = document.querySelector('input[name=cc]');
    tagify1 = new Tagify(input1, {
    	pattern  :/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
  }); 	
  
  //tagify 라이브러리
  //태그추가시(받는사람 입력칸) => 입력된 태그 값 콘솔 출력
  tagify.on('add', function() {
      console.log(tagify.value); // 입력된 태그 정보 객체
  })
    
  /*================
      메일 보내기
  =================*/
  $('#insertBtn').on('click', mailInsertfunc);
  function mailInsertfunc(e){
	  // value를 담은 data => info
	  let info =  getMailInfo();
	  	
	  	//받는사람, 제목 유효성 검사
	    if(tagify.value.length == 0){
			alert('받는사람 이메일을 입력해 주세요.');
			/* input.focus(); */
			return;
		}
		if($('#title').val().trim() == ''){
			alert('제목을 입력해주세요.');
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
				alert('성공');
				//성공시 받은메일함으로
				let url = '/mailboxInfo?mailboxId=d1';
				location.href=url;
			}
			console.log(result);
		})
		.fail(err=>console.log(err));
  }; 
  
 
  
   /*==================
      임시보관 메일 등록
   ====================*/
   $('#draftsBtn').on('click', draftMailInsertfunc);
   function draftMailInsertfunc(){
	  let info =  getMailInfo();
	  
	  if($('#title').val().trim() == ''){
			alert('제목을 입력해주세요.');
			$('#title').focus();
			return;
		};
		
		if(fileList.length > 0){
			info.attachmentGroupId = fileList[0].attachmentGroupId; 
		}
		
	  //메일 등록 ajax
		$.ajax('draftMailInsert',{
			type: 'post',
			contentType : 'application/JSON',
			data : JSON.stringify(info)
		})
		.done(result=>{
			if(result){
				alert('임시저장되었습니다.');
				//성공시 받은메일함으로
				let url = '/mailboxInfo?mailboxId=d1';
				location.href=url;
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
		sender     : $('#sender').val(),           
		recipList  : recipData,  
		ccList 	   : ccData,          
		title      :  $('#title').val(),            
		content    : $('#summernote').summernote('code'),//code로 저장
		empId      : empId,
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
			console.log(selectedMail);
			
			tagify.addTags(selectedMail);
	});
	
	$('.addressInCCBtn').on('click', function() {  
			let selectedMail = [];
			$('.oneCheckbox:checked').each(function() {
			    selectedMail.push($(this).val());
			});
			console.log(selectedMail);
			
			tagify1.addTags(selectedMail);
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
