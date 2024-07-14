/**
 * mail_list.js
 */
mailList('d1');
function mailList(mailboxId){
			$('#mailTbody').empty();
			$('.deleteGroup').empty();
			 $.ajax({
			 url: 'mailboxInfo',
			 data: {
				 mailboxId: mailboxId
				 }
			 })
			 .done(result=>{ 
				 if(result.info.length == 0){
					 appendEmptyMailList();
				 } else {
					 resultMailList(result, mailboxId);
				 }
				 
				 
				 mailDeleteBtn(mailboxId);
			 })
	}	
	
// 메일이 없을 경우
function appendEmptyMailList(){
	let tr = ` <tr>
                 <td colspan="4" class="text-center">메일이 없습니다.</td>
                </tr>`
     $('#mailTbody').append(tr);
}	 

// tr 출력
function resultMailList(result, mailboxId){
	$(result.info).each(function(i, obj){
		let registrateDate = new Date(obj.sendDate);
	   	let formattedDate = registrateDate.toLocaleDateString('ko-KR', {
	        year: 'numeric',
	        month: '2-digit',
	        day: '2-digit'
	    });
    
		let trMailList = `<tr>
		               <td>
		                 <div class="icheck-primary">
		                   <input type="checkbox" value="${obj.mailId}" id="${obj.mailId}" class="oneCheckbox">
		                   <label for="${obj.mailId}"></label>
		                 </div>
		               </td>
		               <td class="mailbox-name">${obj.sender}</td>
		               <!-- d3일때 임시저장수정 / d3이외 단건 조회 -->
		               <td>
		               ${mailboxId === 'd3' 
		                      ? `<a href="/draftMailUpdate?mailId=${obj.mailId}" class="mailInfoLink">${obj.title}</a>` 
		                      : `<a href="/mailInfo?mailId=${obj.mailId}" class="mailInfoLink">${obj.title}</a>`}
		                
		               </td>
		                 	<a href="/mailInfo(mailId=${obj.mailId})" class="mailInfoLink">${obj.title}</a>
		               <td class="mailbox-date">${formattedDate}</td>
		           </tr>`
               
      	$('#mailTbody').append(trMailList);
   })
}

// mailDeleteBtn
function mailDeleteBtn(mailboxId){
	 let deleteGroup = `${mailboxId === 'd5' 
			      		? `<button type="button" class="btn btn-default btn-sm deleteBtn">
			      				<i class="far fa-trash-alt"></i>
                 		   </button>`
                 		:`<button type="button" class="btn btn-default btn-sm moveTrashBtn">
                    		    <i class="far fa-trash-alt"></i>
                          </button>`                 
                 	    }`
          $('.deleteGroup').append(deleteGroup);
}