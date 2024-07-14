/**
 * 
 */
dataCommonList(1);

//자료실 category
function dataCommonList(page) {
    dataList(page, 'common', '공통');
}

function dataDeptList(page) {
    dataList(page, 'dept', '부서');
}

function dataPrivateList(page) {
    dataList(page, 'private', '개인');
}

//ajax
function dataList(page, category, remarks) {
    $('#tbody').empty();
    $('.pagination').empty();

    $.ajax({
        url: 'dataList',
        data: {
            page: page,
            cnt: 10,
            category: category,
            remarks: remarks
        }
    })
    .done(result => {
        console.log(result.pg);
        console.log(result.datas);
        let rowCount = 0;
        rowCount = resultList(result);
        
        for (let i = rowCount; i < 10; i++) {
	      let emptyRow = `<tr>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                        <td>&nbsp;</td>
	                      </tr>`;
	      $('#tbody').append(emptyRow);
	     }
        updatePagination(result.pg, category, remarks, page);
    });
}

// tr 출력
function resultList(result){
	let rowCount = 0;
	$(result.datas).each(function(i, obj){
			 	let registrateDate = new Date(obj.registrateDate);
	            let formattedDate = registrateDate.toLocaleDateString('ko-KR', {
	                year: 'numeric',
	                month: '2-digit',
	                day: '2-digit'
	            });
	            
			let tr = `<tr>
	                    	<td>
		                        <div class="icheck-primary" >
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
			 rowCount++;
		 });//each
	return rowCount;
}

//페이징
function updatePagination(pg, category, remarks, currentPage) {
    let paginationHtml = '';
    if (pg.prev) {
        paginationHtml += `<li class="page-item">
                             <a class="page-link" href="#" onclick="dataList(${pg.startPage - 1}, '${category}', '${remarks}')" aria-label="Previous">
                               <span aria-hidden="true">&laquo;</span>
                             </a>
                           </li>`;
    }
    for (let i = pg.startPage; i <= pg.endPage; i++) {
        paginationHtml += `<li class="page-item ${currentPage === i ? 'active' : ''}">
                             <a class="page-link" href="#" onclick="dataList(${i}, '${category}', '${remarks}')">${i}</a>
                           </li>`;
    }
    if (pg.next) {
        paginationHtml += `<li class="page-item">
                             <a class="page-link" href="#" onclick="dataList(${pg.endPage + 1}, '${category}', '${remarks}')" aria-label="Next">
                               <span aria-hidden="true">&raquo;</span>
                             </a>
                           </li>`;
    }
    $('.pagination').html(paginationHtml);
}

 
 
 