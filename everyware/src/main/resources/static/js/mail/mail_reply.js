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
  	  
 
});