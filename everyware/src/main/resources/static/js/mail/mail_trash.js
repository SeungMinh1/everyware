/**
 * mail_trash.js
 */
$(function () {
	/*===================
	    휴지통 이동(여러개)
	=====================*/
    $('.moveTrashBtn').on('click', function() {
        // 선택된 체크박스들의 mail_id를 찾기
        let selectedMailIds = [];
        $('.oneCheckbox:checked').each(function() {
            selectedMailIds.push($(this).val());
        });
		console.log(selectedMailIds);
        if (selectedMailIds.length > 0) {
            // AJAX 요청으로 메일 이동
            $.ajax({
            	url: 'moveTrashMail',
                type: 'POST',
                data: JSON.stringify(selectedMailIds),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('휴지통으로 이동되었습니다.')
					window.location.reload();
				}else{
					alert("이동되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
        } else {
            alert('선택된 항목이 없습니다.');
        }
      });
  	/*===================
	    휴지통 이동(단건)
	=====================*/
 	$('.moveTrashInfoBtn').on('click', function(){
		console.log($(this).val());
		var mailId = $(this).val();
		$.ajax({
            	url: 'moveTrashMailInfo',
                type: 'POST',
                data: JSON.stringify(mailId),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('휴지통으로 이동되었습니다.')
					let url = '/mailboxInfo?mailboxId=d1';
					location.href=url;
				}else{
					alert("휴지통으로 이동되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
	});
	
   /*==================
	   완전 삭제 (여러개)
	===================*/
	 $('.deleteBtn').on('click', function() {
        // 선택된 체크박스들의 mail_id를 찾기
        let selectedMailIds = [];
        $('.oneCheckbox:checked').each(function() {
            selectedMailIds.push($(this).val());
        });
		console.log(selectedMailIds);
        if (selectedMailIds.length > 0) {
            $.ajax({
            	url: 'mailDelete',
            	type : 'post',
                data: JSON.stringify(selectedMailIds),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('삭제되었습니다.')
					window.location.reload();
				}else{
					alert("이동되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
        } else {
            alert('선택된 항목이 없습니다.');
        }
      });
    /*==================
	    완전 삭제 (단건)
	===================*/
	$('.deleteInfoBtn').on('click', function(){
		console.log('휴지통'+ mailId);
		alert('삭제되었습니다.')
	});
	
  /*===================
	    휴지통에서 복구(여러개)
	=====================*/
    $('.moveRestoreBtn').on('click', function() {
        // 선택된 체크박스들의 mail_id를 찾기
        let selectedMailIds = [];
        $('.oneCheckbox:checked').each(function() {
            selectedMailIds.push($(this).val());
        });
		console.log(selectedMailIds);
		var data = {                    
			mailIdList  : selectedMailIds			
		};
        if (selectedMailIds.length > 0) {
            $.ajax({
            	url: 'restoreMailUpdate',
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json',
            })
            .done(result=>{
				if(result){
					alert('복구')
					window.location.reload();
				}else{
					alert("복구되지 않았습니다. \n 데이터를 확인해주세요.")
				}
				console.log(result);
			})
        } else {
            alert('선택된 항목이 없습니다.');
        }
      });
});