/**
 * 
 */

/*$('.mailInsert').on('click', function(){
	let recipient = $('.recipient').val();
	//let cc = $('.sender2').val();
	let title = $('.title').val();
	let content = $('#summernote').summernote('code');

    // AJAX 요청으로 메일 이동
    $.ajax({
    	url: 'mailInsert',
        type: 'POST',
        data: JSON.stringify(),
        contentType: 'application/x-www-form-urlencoded',
        body: 'recipient=' + recipient + '&title=' + title + '&content=' + content 
    })
    .done(result=>{
		if(result.result){
			alert('정상적으로 등록되었습니다.')
		}else{
			alert("등록되지 않았습니다. \n 데이터를 확인해주세요.")
		}
		console.log(result);
	})
	
	console.log($('.recipient').val());
	console.log($('.title').val());
	console.log($('#summernote').summernote('code'));
	});*/
 
 