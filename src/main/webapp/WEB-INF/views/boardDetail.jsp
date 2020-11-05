<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>

<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
</head>
<body>
	<div id="container">
    	<div id="content">
            <div class="subtitle"><h3>상세보기</h3><a href="./board">홈으로</a></div>
                <form name="frmD" id="frmD" method="post" enctype="multipart/form-data" action="">
                    <input type="hidden" id="bbs_id" name="bbs_id" value="${bbsId}"/>
                    <input type="hidden" id="bbsctt_sn" name="bbsctt_sn" value="${bbscttSn}"/>
                    <input type="hidden" id="atchmnfl_id" name="atchmnfl_id" value="" />
                </form>
                <div>
                	<table class="tbl_type02" border="1">
                    	<colgroup>
                            <col width="120" />
                            <col width="" />
                            <col width="120" />
                            <col width="250" />
                        </colgroup>
                    <tbody>
                    	<tr>
	                        <th><label for="bbsctt_sj">제목</label></th>
	                        <td>
	                        	<div style="min-width:500px;">
	                            	<p id="bsct_sj"></p>
	                            </div>
	                        </td>
	                        <th ><label for="wrter_nm">작성자</label></th>
	                        <td>
	                            <p id="wrter_nm"></p>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="bbsctt_cn">내용</label></th>
	                        <td colspan="3">
	                            <div style="min-height:350px;">
	                                <p id="bsct_cn"></p>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="file_1">첨부파일</label></th>
	                        <td>
	                            <p><a href="/fileDown/${files.bbsId}/${files.bno}">${files.fileOriName}</a></p>
	                        </td>

	                        <th><label for="last_upd_dt">작성일</label></th>
	                        <td>
	                            <p id="last_upd_dt"></p>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="write_next">다음 글</label></th>
	                        <td colspan="3">
	                            <a href="javaScript:void(0);" id="write_next" class="write_action go_next"></a>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="write_prev">이전 글</label></th>
	                        <td colspan="3">
	                            <a href="javaScript:void(0);" id="write_perv" class="write_action go_perv"></a>
	                        </td>
	                    </tr>
                    </tbody>
                </table>
                <div style="text-align: right; margin-top: 5px;">
	            	<a href="javaScript:void(0);" id="b_update" class="btn_rblue b_h30" onclick="app_req('U');">수정</a>
	                <a href="javaScript:void(0);" id="b_delete" class="btn_rblue b_h30" onclick="app_req('D');">삭제</a>
                </div>
            </div>
        </div>
    </div>
	<script>
	$(function(){
    	fn_boardDetail();
    });


    function fn_boardDetail(){
    	var bbsId = $("#bbs_id").val();
    	var bbscttSn = $("#bbsctt_sn").val();

    	$.ajax({
			url: "/selectBoardDetail",
			data:{
				"bbsId" : bbsId,
				"bbscttSn" : bbscttSn,
			},
			type:"GET",
			contentType:"application/json; charset=UTF-8",
			dataType: "JSON",
			success: function (data){
				set_boardDetail(data);
			},
			error: function(error){
				alert("error!");
				console.log(error);
			}
		});
    }

    function set_boardDetail(jdata){

    	if (jdata == null) {
            alert("상세조회를 실패하였습니다.");
            href.replace="./board";
        }
        var detail = jdata;
        console.log(detail);
        var str = detail.contents;
        if(str){
        	str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        	$("#bsct_cn").html(str);
        }
        $("#bsct_sj").text(detail.title);
        $("#wrter_nm").text(detail.wrterName);

        $("#last_upd_dt").text(detail.regDtime);

        var pervPageSj = detail.pageNextSj;
        var nextPageSj = detail.pagePervSj;

        $("#atchmnfl_id").val(detail.fno);
        if(!nextPageSj){
        	nextPageSj= "다음 글이 없습니다.";
        }
        if(!pervPageSj){
            pervPageSj= "이전 글이 없습니다.";
        }

        $("#write_next").addClass("num_"+detail.pagePerv);
        $("#write_next").text(nextPageSj);
        $("#write_perv").addClass("num_"+detail.pageNext);
        $("#write_perv").text(pervPageSj);

    }

    $(".write_action").on('click', function(){

    	var bbsctt_sn = $(this).attr('class').split('num_');
    	if(bbsctt_sn[1] == '0') return false;

    	var bbs_id = $("#bbs_id").val();
    	var newForm = $('<form></form>');
        newForm.attr("name", "frmD");
        newForm.attr("method", "post");
        newForm.attr("action", "./boardDetail");
        newForm.append($("<input type='hidden' value='"+bbs_id+"' name='bbsId'>"));
        newForm.append($("<input type='hidden' value='"+bbsctt_sn[1]+"' name='bbscttSn'>"));
        newForm.appendTo('body');
        newForm.submit();
    });

    function app_req(iud) {

        var c_firm = " 하시겠습니까?";
        if (iud == "D") {
            c_firm = "삭제" + c_firm;
        } else if (iud == "U") {
            c_firm = "수정" + c_firm;
        }
		if(confirm(c_firm)){
        	if (iud == "D") {
        		fn_deleteWrite();
        	}
        	else {
        		var frm = $("#frmD");
        		frm.attr("action","./boardWrite");
        		frm.submit();
        	}
		}
    }
	function fn_deleteWrite(){
		var frmD = $("#frmD");
		var queryString = frmD.serialize();
		$.ajax({
            type : 'post',
            url : '/deleteBoardWrite',
            data : queryString,
            success : function(data){
                if(data == '1'){
                	alert("삭제 되었습니다.");
                	location.href="./board";
                }
                else{
                	alert("삭제를 실패했습니다.");
                }
            }
        });

	}
	</script>
</body>
</html>