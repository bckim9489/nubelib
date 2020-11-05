<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>
	<div id="container">
    	<div id="content">
			<input type="hidden" id="bbs_id" name="bbs_id" value="bbs_test1"/>
            <form name="frmS" id="frmS" method="post" enctype="multipart/form-data" action="">

                <div class="searchzone">
                    <table class="tbl_search" border="1" style="margin-left: auto; margin-right: auto;">
                        <colgroup>
                            <col width="120" />
                            <col width="240" />
                            <col width="120" />
                            <col width="240" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th><label>제목</label></th>
                                <td>
                                    <input type="text" id="srch_title" name="srch_title" maxlength="14" class="i_text" style="width: 90%;" />
                                </td>
                                <th><label>내용</label></th>
                                <td>
                                    <input type="text" id="srch_context" name="srch_context" maxlength="14" class="i_text" style="width: 90%;" />
                                </td>

                            </tr>
                        </tbody>
                    </table>
                    <button type="button" name="search_btn" id="" class="" onclick="btn_action('search')" style="margin-left:71%;">검색</button>
                </div>
            </form>

            <div class="tbl_01"></div>
            <div id="contentList">
                <table id="grid_dataList" border="1" style="margin-left: auto; margin-right: auto;">
                	<colgroup>
                			<col width="120" />
                            <col width="100" />
                            <col width="120" />
					</colgroup>
					<thead>
						<tr>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody id="tbdy">
					</tbody>
                </table>
                <button type="button" name="search_btn" id="" class="" onclick="btn_action('write')" style="margin-left: 71%;">글쓰기</button>
            </div>
            <div id ="pagination" style="margin-left: 50%;"></div>
         </div>
    </div>

    <script>
    	var bbsId = $("#bbs_id").val();

    	$(document).ready(function(){
			fn_GetList(1);
			fn_GetPaging(1);

    	});

   		$(document.body).delegate('#tbdy tr', 'click', function() {
   			var td = $(this).children();
   			var tdArr = new Array();
   			td.each(function(i){
   		        tdArr.push(td.eq(i).text());
   		    });
   			var index = tdArr[0];
   			fn_goDetail(index, bbsId);
   		});

 		function fn_goDetail(index, bbsId){

   			var bbscttSn = index;
			var newForm = $('<form></form>');

            newForm.attr("name", "frmD");
            newForm.attr("method", "post");
            newForm.attr("action", "./boardDetail");
            newForm.append($("<input type='hidden' value='"+bbsId+"' name='bbsId'>"));
            newForm.append($("<input type='hidden' value='"+bbscttSn+"' name='bbscttSn'>"));
            newForm.appendTo('body');
            newForm.submit();
   		}

   		function fn_GetPaging(start){
   			var bbsId = $("#bbs_id").val();
    		var searchTitle = $('#srch_title').val();
    		var searchContents = $('#srch_context').val();
			$.ajax({
				url: "/listCnt",
    			data:{
    				"bbsId" : bbsId,
    				"title" : searchTitle,
    				"contents" : searchContents,
    			},
    			type:"POST",
    			success: function (data){
    				var result = fn_SetPaging(data, 10);
    				fn_Pagination(start, result, 3);
    			}
			});
   		}

   		function fn_SetPaging(value, total){
   			var pages = Math.ceil(value/total);
   			var result = pages;
   			return result;
   		}

   		function fn_Pagination(current, total, pageSize){
   			var result = "";

   			var block = Math.ceil(current/pageSize);
   			var start = (block-1)*pageSize+1;
   			var end = block*pageSize;
   			var leftPage = total - (start-1);
   			if(start != 1 && pageSize < start){
   				var perv = start-1;
   				result += '<a href="javaScript:void(0);" class="perv" value='+perv+'>이전  </a>';
   			}
			if(end > total){
				end = total;
			}
   			for(var i = start; i<= end; i++){
   				result += '<a href="javaScript:void(0);" id="page_'+i+'" class="page" value='+i+'> '+i+' </a>'
   			}
   			if(pageSize < total && pageSize < leftPage){
   				var next = pageSize + start;
   				result += '<a href="javaScript:void(0);" class="next" value='+next+'> 다음 </a>'
   			}
   			$("#pagination").html(result);
   		}

   		$(document.body).delegate('.page', 'click', function() {
   			var page = $(this).attr('value');
   			fn_GetList(page);
   		});

   		$(document.body).delegate('.next', 'click', function() {
   			var page = $(this).attr('value');
   			fn_GetList(page);
   			fn_GetPaging(page);
   		});
   		$(document.body).delegate('.perv', 'click', function() {
   			var page = $(this).attr('value');
   			fn_GetList(page);
   			fn_GetPaging(page);
   		});

    	function fn_GetList(page){
    		var bbsId = $("#bbs_id").val();
    		var searchTitle = $('#srch_title').val();
    		var searchContents = $('#srch_context').val();
    		$.ajax({
    			url: "/list",
    			data:{
    				"bbsId" : bbsId,
    				"title" : searchTitle,
    				"contents" : searchContents,
    				"page" : page
    			},
    			type:"GET",
    			contentType:"application/json; charset=UTF-8",
    			dataType: "JSON",
    			success: function (data){
    				fn_SetList(data);
    			},
    			error: function(error){
    				alert("error!");
    				console.log(error);
    			}
    		});
    	}
    	function fn_SetList(data){
			var card = "";
			for(key in data){
				card += '<tr>';
				card += '<td class="t_index" style="display:none;">'+data[key].bbscttSn+'</td>'
				if(data[key].noticeYn == 'Y' && key<3){
						card += '<td style="color:red;"><div style="min-width:400px; ">[공지사항] '+data[key].title+'</div></td>'
						card += '<td><div style="min-width:100px;">'+data[key].wrterName+'</div></td>'
						card += '<td><div style="min-width:200px;">'+data[key].regDtime+'</div></td>'
						card += '</tr>'
				}else {
					card += '<td><div style="min-width:300px;">'+data[key].title+'</div></td>'
					card += '<td><div style="min-width:100px;">'+data[key].wrterName+'</div></td>'
					card += '<td><div style="min-width:200px;">'+data[key].regDtime+'</div></td>'
					card += '</tr>'
				}
			}
			$("#tbdy").empty();
			$("#tbdy").append(card);
    	}

    	function btn_action(action){
			if(action == 'search'){
				fn_GetList(1);
				fn_GetPaging(1);
			}
			if(action == 'write'){
				var bbsId = $("#bbs_id").val();
				var newForm = $('<form></form>');
				newForm.attr("name", "frmW");
	            newForm.attr("method", "post");
	            newForm.attr("action", "./boardWrite");
	            newForm.append($("<input type='hidden' value='"+bbsId+"' name='bbs_id'>"));
	            newForm.appendTo('body');
	            newForm.submit();
			}
		}

    </script>
<%@include file="./common/footer.jsp" %>