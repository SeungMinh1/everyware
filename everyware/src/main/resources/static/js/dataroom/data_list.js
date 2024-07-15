/**
 * 
 */
dataList(1, 'common', '공통', '공통자료실');

let currentCategory = 'common';
let currentTitle = '공통자료실';

function setCategory(category, title) {
    currentCategory = category;
    currentTitle = title;
    if (category === 'common') {
        dataCommonList(1);
    } else if (category === 'dept') {
        dataDeptList(1);
    } else if (category === 'private') {
        dataPrivateList(1);
    }
}

function searchData() {
    let searchType = $('#searchType').val();
    let searchKeyword = $('#searchKeyword').val();
    if (currentCategory === 'common') {
        dataCommonList(1, searchType, searchKeyword);
    } else if (currentCategory === 'dept') {
        dataDeptList(1, searchType, searchKeyword);
    } else if (currentCategory === 'private') {
        dataPrivateList(1, searchType, searchKeyword);
    }
}
//자료실 category
function dataCommonList(page, searchType = '', searchKeyword = '') {
    dataList(page, 'common', '공통', currentTitle, searchType, searchKeyword);
}

function dataDeptList(page, searchType = '', searchKeyword = '') {
    dataList(page, 'dept', '부서', currentTitle, searchType, searchKeyword);
}

function dataPrivateList(page, searchType = '', searchKeyword = '') {
    dataList(page, 'private', '개인', currentTitle, searchType, searchKeyword);
}

//ajax
function dataList(page, category, remarks, title, searchType = '', searchKeyword = '') {
    $('#thead').empty();
    $('#tbody').empty();
    $('.pagination').empty();
	$('.appendSearch').empty();
	$('.folderAppend').hide();
	
	
	$('.groupFileTitle').text(title);
    $.ajax({
        url: 'dataList',
        data: {
            page: page,
            cnt: 10,
            category: category,
            remarks: remarks,
            searchType: searchType,
            searchKeyword: searchKeyword
        }
    })
    .done(result => {
        console.log(result.pg);
        console.log(result.datas);
        
        appendSearchInput();
        appendTableHeader();
        $('.dataBtnGroup').show();
        if(result.datas.length == 0){
			appendEmptyData();
        } else {
            let rowCount = resultList(result);
             appendRows(rowCount);
            
        }
        updatePagination(result.pg, category, remarks, page);
    });
}

//검색 인풋 html
function appendSearchInput() {
    let appendSearch = `
        <div class="input-group input-group-sm">
            <select class="form-select-sm" id="searchType">
                <option value="T">제목</option>
                <option value="F">파일이름</option>
            </select>
            <input type="search" class="form-control-sm" id="searchKeyword" placeholder="검색">
            <div class="input-group-append">
                <div class="btn btn-primary" onclick="searchData(1)">
                    <i class="fa fa-search"></i>
                </div>
            </div>
        </div>`;
    $('.appendSearch').append(appendSearch);
}

//자료실 table header
function appendTableHeader() {
    let theadTr = `
        <tr>
            <th style="width: 15%;"></th>
            <th style="width: 55%;"><i class="fa-solid fa-folder"></i> 제목</th>
            <th style="width: 30%;">등록날짜</th>
        </tr>`;
    $('#thead').append(theadTr);
}

//등록된 파일이 없을 경우 html
function appendEmptyData() {
    let emptyData = `
        <tr>
            <td colspan="4" class="text-center">등록된 파일이 없습니다.</td>
        </tr>`;
    $('#tbody').append(emptyData);
}

//table 10칸중 빈칸
function appendRows(rowCount) {
    for (let i = rowCount; i < 10; i++) {
        let emptyRow = `
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>`;
        $('#tbody').append(emptyRow);
    }
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
		                    <td class="title"><a class="titleDataId" data-id=${obj.dataId}>${obj.title}</a></td>
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


//자료 단건 조회 => 파일 리스트 출력
$(document).ready(function() {
	$(document).on('click', '.titleDataId', function dataListInfo() {
		console.log($(this).data('id'));
		let dataId = $(this).data('id');
		
		$.ajax({
			url: 'selectData',
			data: {dataId: dataId}
			
		})
		.done(result=>{
			$('#thead').empty();
			$('#tbody').empty();
			$('.pagination').empty();
			$('.appendSearch').empty();
			$('.dataBtnGroup').hide();
			$('.folderAppend').show();
			
			let groupFileTitle = `<i class="fas fa-folder-open"></i> ${result[0].title}`
			$('.groupFileTitle').html(groupFileTitle);
						
			let theadTr = `<tr>
	                  		 <th width="5%"></th>
	                  		 <th width="55%">파일이름</th>
	                  		 <th width="20%">파일크기</th>
	                  		 <th width="20%">등록날짜</th>
	                  	   </tr>`
        	$('#thead').append(theadTr);
			
			resultFileList(result);
		})//selectData ajax.done
		
		$('.folderDelete').on('click', function(){
			$.ajax({
				url: 'deleteDataFolder',
				type: 'POST', 
				contentType : 'application/JSON',
	            data: JSON.stringify({dataId : dataId}),
	            success : function(){
					alert('폴더 삭제 성공');
					let url = '/dataroom';
					location.href=url;
				}
			})
		})
		
		
		
		
	})//document.onclick;
	
	
	function resultFileList(result){
		$(result).each(function(i, d){
			let registrateDate = new Date(d.registrateDate);
	            let formattedDate = registrateDate.toLocaleDateString('ko-KR', {
	                year: 'numeric',
	                month: '2-digit',
	                day: '2-digit'
	            });
			let tbodyTr = `<tr>
							<td></td>
		                    <td><a class="fileName" id="${d.fileId}">${d.originFileName}</a></td>
		                    <td class="size">${d.fileSize}</td>
		                    <td class="date">${formattedDate}
		                    <input type="hidden" value="${d.attachmentGroupId}">
		                    </td>
	                    </tr>`
	         $('#tbody').append(tbodyTr);
		})
	}
	
	//파일 다운로드 ajax
	$(document).on('click', '.fileName', function() {
		var fileId = $(this).attr('id');
		console.log(fileId);
		let data1 = {
			 fileId: fileId 
		}
		$.ajax('selectDataFileInfo', {
	      type: 'POST'
	      , contentType : 'application/JSON'
	      , data: JSON.stringify(data1)
	      , success: function(data) {
	        console.log(data);
	        var fileCallPath = encodeURIComponent(//
				data.uploadPath  +"/"+ data.uploadFileName + "_" + data.originFileName);
	        window.location.href= "downloadData?fileName=" + fileCallPath;
	      },
	      error: function(error) {
	        console.log('에러:', error);
	      }
	    });//ajax;
	});//document.onclick.fileName;
	
	
})//document.ready;
