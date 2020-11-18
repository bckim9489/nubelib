<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>
<div class="targeter"></div>

<iframe id="ifrm" src="http://www.letskorail.com/ebizprd/EbizPrdTicketpr21100W_pr21110.do" title="abc" style="width:1200px; height:600px;">

</iframe>
<input type="button" id="btn_start" value="start"/>
<script>
	$(document).on('ready', function (){

	});

	$("#btn_start").on('click', function(){
		setBook();
	});

	function setBook(){
		var ifrm = $('#ifrm')[0].contentWindow;
		ifrm.find("input[name='txtGoStart']").val("인천");
		//출발지
		$("#start").val('대구');
		//목적지
		$("#get").val('부산');
		//년
		$("#s_year").val('2021');
		//월
		$("#s_month").val('01');
		//일
		$("#s_day").val('01');
		//조회하기
		$("img[src='/images/btn_inq_tick.gif']").trigger('click');
	}

	function ajaxCrawling3(){
		$.ajax({
			url:"/bookStart",
			type:"POST",
			success: function(data){
				console.log(data);
				$(".targeter").html(data);
			}
		});
	}

</script>
<%@include file="./common/footer.jsp" %>