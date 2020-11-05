//------------------------ XML -> JSON -----------------------------------------
/**
 * xmlData = $(_data).find(_element);
 * dataFrom = {
 * 		dataName : 반환될 JSON 이름,
 * 		dataHeader : data의 Key 값,
 * }
 * option = {
 * 		비교할 Element이름 : 비교값
 * }
 */
function xmlParser(xmlData, dataForm, option){
	if(!option || option=='undefined'){
		option == "";
	}

	var json_xml = {};
	var result = [];
	var dataLength = xmlData.length;
	var dataFormHeader = dataForm.dataHeader;

	if(dataLength){
		$(xmlData).each(function(){
			var resultRow = {};
			var currentData = $(this);
			if(currentData.find(Object.keys(option)[0]).text() == option[Object.keys(option)]){
				for(var key in dataFormHeader){
						var findKey = dataFormHeader[key];
						var value = currentData.find(findKey).text();
						resultRow[findKey] = value;
					}
				result.push(resultRow);
			}
		});

		json_xml[dataForm.dataName] = result;
	}

	return json_xml;
}
//------------------------ XML -> JSON -----------------------------------------
//------------------------페이지네이션 테스트----------------------------------------
/**
 * data {
 * 	"rowTotalCnt"  : 데이터 COUNT(Row)
 *  "rowLimitCnt" : 한페이지에 나타낼 Row 수
 *  "apperPageCnt" : 총 페이지를 페이징 하는 페이지 수
 * }
 */
let __dataSet;
let __urlSet;
let __pageSet;
let __callback;

function bc_Paging(data){

	var dataSet = data.dataSet;
	var urlSet = data.urlSet;

	var pageSet = data.pageSet
	var callback= data.callback;
	var start = 1;
	if(data.start != "" && data.start != null){
		start = data.start;
	}
	getPaging(urlSet, dataSet, pageSet, start, callback);
}

function pagination(data, currentPage, target, callback){
	var rowTotalCnt = ""; //데이터 총 Row
	var apperRowPerPageCnt = ""; //한페이지에 나타낼 Row 수
	var apperPageCnt = ""; //한번에 보여줄 페이지

	var pageTotalCnt = ""; //총 페이지

	var startPage = ""; //시작 페이지
	var endPage = ""; //끝페이지

	rowTotalCnt = data.rowTotalCnt;
	apperRowPerPageCnt = data.rowLimitCnt;
	apperPageCnt = data.apperPageCnt;

	pageTotalCnt = Math.ceil(rowTotalCnt / apperRowPerPageCnt);

	startPage = 1+(apperPageCnt*parseInt((currentPage-1)/apperPageCnt));
	endPage = apperPageCnt*(parseInt((currentPage-1)/apperPageCnt)+1);
	if(endPage > pageTotalCnt){
		endPage = pageTotalCnt;
	}
	var result = {
		"startPage" : startPage,
		"endPage" : endPage,
		"currentPage" : currentPage,
		"apperPage" : apperPageCnt,
		"pageTotalCnt" : pageTotalCnt
	}

	setPaging(result, target, callback);

}

function setPaging(data, target, callback){
	var startPage = data.startPage;
	var endPage = data.endPage;
	var currentPage = data.currentPage;
	var apperPage = data.apperPage;
	var pageTotalCnt = data.pageTotalCnt;
	var pageClick = callback;

	var card ="";
	if(0 > apperPage-currentPage){
		var pervPage = startPage - 1;
		card += "<a href='javaScript:void(0);' class = 'newPage' name='' value='" + pervPage + "'  onclick='"+pageClick+"("+pervPage+");'> 이전 </a>";
	}
	for(var i =startPage; i<endPage+1; i++){
		card += "<a href='javaScript:void(0);' class = 'newPage "+ (currentPage == i?" cur_page'":"'") +"' name='' value='"+i+"'  onclick='"+pageClick+"("+i+");'> "+i+" </a>";
	}

	if(pageTotalCnt != endPage){
		var nextPage = endPage + 1;
		card += "<a href='javaScript:void(0);' class = 'newPage' name='' value='"+nextPage+"' onclick='"+pageClick+"("+nextPage+");'> 다음 </a>";
	}
	$(target).html(card);

}

function getPaging(urlSet, dataSet, pageSet, start, callback){
	__callback = callback;
	__dataSet = dataSet;
	__pageSet = pageSet;
	__urlSet = urlSet;

	$.ajax({
		url: urlSet,
		data: dataSet,
		type:"POST",
		success: function (data){
			var pageData = {
				"rowTotalCnt" : data,
				"rowLimitCnt" : pageSet.limit,
				"apperPageCnt" : pageSet.apperPage
			};

			pagination(pageData, start, pageSet.target, callback);
		}
	});

}
$(document).on('click','.newPage', function() {
	var page = $(this).attr('value');

	getPaging(__urlSet, __dataSet, __pageSet, page, __callback);
});

//-------------------------------페이지네이션 끝-----------------------------------

//-------------------------------그리드 테스트------------------------------------
function jq_targetType(target,type){
	var target_type = ""
	switch(type){
		case 'id':
			target_type = "#"+target;
			break;
		case 'class':
			jq_type = "."+target;
			break;
		case 'name':
			jq_type= "input[name="+target+"]";
			break;
		default :
			target_type = "#"+target;
			break;
	}
	return target_type;
}

function tbl_head_create(head){
	var card = "";
	return card;
}

function tbl_body_create(body){
	var card = "";
	return card;
}

function tbl_colgroup_create(colgroup){
	var card = "";
	return card;
}

function tbl_create(tblName, target, type){
	var card = "";
	var target_type = jq_targetType(target, type);

	card += '<div class="">';
	card += '<table class="'+tblName+'" border="1" style="margin-left: auto; margin-right: auto;">';

	card += tbl_colgroup_create();
	card += tbl_head_create();
	card += tbl_body_create();

	card += '</table>';
	card += '</div>';


	$(target_type).html(card);
}