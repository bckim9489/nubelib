<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>
<style>
	div.div_left{
		width: 120px;
		height: 700px;
		float:left
	}
</style>
<div class="container">
	<div class="member_list div_left">
		<select name="list_box" id="list_box" size= '13'>
			<option value=""></option>
		</select>
		<a href="javaScript:void(0);" id="btn_select"  style="display: block;">추가</a>
		<a href="javaScript:void(0);" id="btn_cancel"  style="display: inline-block;">제거</a>
	</div>
	<div class="div_center">
	<form action="/mailSend" method="post">
		<div align="center">
      		받을사람: <input type="text" id="mail_name" size="120" style="width:70%" class="form-control" readonly>
      		<input type="hidden" id="mail_address" name="address" size="120" style="width:70%;" class="form-control" >
    	</div>
    	<div align="center">
      		제목: <input type="text" name="title" size="120" style="width:70%" class="form-control" >
    	</div>
    	<p>
    		<div align="center">
      			<textarea name="message" cols="120" rows="12" style="width:70%; resize:none" class="form-control"></textarea>
    		</div>
    	<p>
    	<div align="center">
      		<input type="submit" value="메일 보내기" class="btn btn-warning">
    	</div>
  	</form>
  	</div>
</div>

<script>
	$(function(){
		$.ajax({
			url: "/memberList",
			success: function(data) {
				if(data !=""){
					var card = "";
					for(var key in data){
						card += "<option value='"+data[key].mid+"' data-email='"+data[key].email+"'>"+data[key].name+"</option>";
					}
					$("#list_box").html(card);
				} else {
					alert("DB데이터 에러");
				}
			},
			error: function() {
				alert("통신에러");
			}
		});
	});

	$("#btn_select").on('click', function(){
		var selectOption = $("#list_box option:selected");
		var email = selectOption.data("email");
		var name = selectOption.text();
		var curMailAddress = $("#mail_address").val();
		var curMailName = $("#mail_name").val();
		if(curMailAddress == ""){
			$("#mail_address").val(email);
			$("#mail_name").val(name);
		} else {
			$("#mail_address").val(curMailAddress+", "+email);
			$("#mail_name").val(curMailName+", "+name);
		}
	});

</script>
<%@include file="./common/footer.jsp" %>