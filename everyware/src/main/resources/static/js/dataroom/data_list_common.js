/**
 * 
 */
$(document).ready(function(){
        if (window.location.href === "https://192.168.0.13:8100/dataroom") {
            dataCommonList(1);
        }
});

function dataCommonList(page){
	 $.ajax({
		url: 'dataCommon',
		data: {
	      page: page,
	      cnt: 10
	    }
	 })
	 .done(result => {
		 console.log(result.pg)
		 console.log(result.datas)
		 $(result.datas).each(function(i, obj){
			 let registrateDate = new Date(obj.registrateDate);
	            let formattedDate = registrateDate.toLocaleDateString('ko-KR', {
	                year: 'numeric',
	                month: '2-digit',
	                day: '2-digit'
	            });
	            
			let tr = `<tr>
	                    	<td>
		                        <div class="icheck-primary">
		                           <input type="checkbox" value="${obj.dataId}" id="${obj.dataId}" class="oneCheckbox">
		                           <label for="${obj.dataId}"></label>
		                        </div>
		                    </td>
		                    <td class="title"><a href="#">${obj.title}</a></td>
		                    <td class="date">${formattedDate}
		                    <input type="hidden" value="">
		                    </td>
		               </tr>`
			 $('#tbody').append(tr);
		 });//.done
	 });//$.ajax
}
 
 
 
 
 
 