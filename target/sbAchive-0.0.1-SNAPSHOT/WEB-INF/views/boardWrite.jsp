<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardWrite</title>

<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="jquery.form.min.js"></script>

</head>
<body>
	<div id="container">
	    <div id="content">
	        <div class="subtitle"><h3>글쓰기</h3>

	        </div>

	        <form name="frmW" id="frmW" method="post" enctype="multipart/form-data" action="">
	            <input type="hidden" id="bbs_id" name="bbs_id" value="${bbsId}"/>
	            <input type="hidden" id="bbsctt_sn" name="bbsctt_sn" value="${bbscttSn}"/>
	            <input type="hidden" id="atchmnfl_id" name="atchmnfl_id" value="" />
	            <!-- <input type="hidden" id="curr_file_url" name="curr_file_url" value=""/> -->
	            <div>
	                <table class="tbl_type02" border="1">
	                    <colgroup>
	                    <col width="120" />
	                    <col width="1100" />
	                    <col width="120" />
	                    </colgroup>
	                <tbody>
	                    <tr>
	                        <th><label for="bbsctt_sj">제목</label></th>
	                        <td>
	                            <input class="i_text" type="text" id = "title" name="title" style="width:98%; height:20px;" />
	                        </td>
	                        <th class="bbsctt_radio"><label for="bbsctt_radio">글 분류</label></th>
	                        <td class="bbsctt_radio" colspan="3">
	                            <input type ="radio" class="bbsctt_radio r_btn i_text" id="bbsctt_nomal" name="bbsctt_radio" value="N" checked>일반</input>
	                            <input type ="radio" class="bbsctt_radio r_btn i_text" id="bbsctt_notice" name="bbsctt_radio" value="Y">공지</input>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="bbsctt_cn">내용</label></th>
	                        <td colspan="5">
	                            <textarea class="i_text" id="contents" name="contents" rows="" cols="" style="width:98%; height:300px;"></textarea>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><label for="file_1">첨부파일</label></th>
	                        <td colspan="3">
	                            <input type="file" id="files" name="files" class="fileBtn"/>
 	                            <table id="tb_fileList" class="form tbFileList"></table>
	                        </td>
	                    </tr>
	                </tbody>
	                </table>
	                <div style="text-align: right; margin-top: 5px;">
	                    <a href="javaScript:void(0);" id="b_cancel" class="btn_rblue b_h30" onclick="app_req('Q');">취소</a>
	                    <a href="javaScript:void(0);" id="b_insert" class="btn_rblue b_h30" onclick="app_req('I');">등록</a>
	                    <a href="javaScript:void(0);" id="b_update" class="btn_rblue b_h30" onclick="app_req('U');" style="display:none;">수정</a>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>
	<script>
		$(function(){
			var bbscttSn = $('#bbsctt_sn').val();
			if(bbscttSn != ''){
				fn_boardDetail();
				$("#b_update").show();
				$("#b_insert").remove();
			}
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
	            alert("서버와 연결을 실패하였습니다.");
	            href.replace="./board";
	        }
	        var detail = jdata;
	        console.log(detail);
	        $("#title").val(detail.title);
	        $("#contents").text(detail.contents);
	        if(detail.noticeYn == 'Y'){
	        	$("#bbsctt_nomal").prop('checked', false);
	        	$("#bbsctt_notice").prop('checked', true);
	        }
	        if(detail.fno){
	        	$("#atchmnfl_id").val(detail.fno);
	        	$("#tb_fileList").append('<td id ="fileName">'+detail.fileOriName+'<a href="javaScript:void(0);" id="atc_cancel" style="font-size:3px;"> 지우기</a></td>');
	        }
	    }
	    $(document.body).delegate('#atc_cancel', 'click', function() {
			$(this).parent('td').remove();
			$("#atchmnfl_id").val("");
   		});

		function app_req(QIU){
			var bbId = $("#bbId").val();
			if(QIU == 'Q'){
				if(confirm("글쓰기를 취소하시겠습니까?")){
					history.go(-1);
				}
			}
			if(QIU == 'I'){
				if(confirm("글을 등록하시겠습니까?")){
					fn_insertWrite();
				}
			}
			if(QIU == 'U'){
				if(confirm("글을 수정하시겠습니까?")){
					fn_updateWrite();
				}
			}
		}
		function fn_insertWrite(){
			if(formchk()){
				$.ajax({
		            url : '/insertBoardWrite',
		            type : "POST",
		            data : new FormData($('#frmW')[0]),
					processData: false,
					contentType: false,
					cache: false,
		            success : function(){
	            		alert("저장되었습니다.");
	                	location.href="/board";
		            },
		            error : function(){
               			alert("저장이 실패했습니다.");
		            }
		        });
			}
		}

		function fn_updateWrite(){
			if(formchk()){
				$.ajax({
		            url : '/updateBoardWrite',
		            type : "POST",
		            data : new FormData($('#frmW')[0]),
					processData: false,
					contentType: false,
					cache: false,
		            success : function(){
	            		alert("저장되었습니다.");
	                	location.href="/board";
		            },
		            error : function(){
               			alert("저장이 실패했습니다.");
		            }
		        });
			}
		}

		function formchk(){
			if($('#title').val() == ''){
				alert("제목을 입력하세요");
				return false;
			}
			return true;
		}
	</script>

</body>
</html>