/**
 * 
 */


var searchForm = $("#searchForm");
   
$(".searchFormBtn").on("click", function(e){
   	if(!searchForm.find("option:selected").val()){
		Swal.fire("검색 종류를 선택하세요");
   		return false;
   	};
   	
   	if(!searchForm.find("input[name='keyword']").val()){
		Swal.fire("검색어를 입력하세요");
   		return false;
   	}
   	
   	searchForm.find("input[name='pageNum']").val("1");
   	e.preventDefault();
   	
   	searchForm.submit();
});