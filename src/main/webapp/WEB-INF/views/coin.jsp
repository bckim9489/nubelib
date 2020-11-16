<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>

<form id="authorizeFrm" name="authorizeFrm" method="get" action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" target="_authForm">
    <input type="hidden" name="client_id" 	  value="MFaFJ3UTLzQ5TQRMhQPWQ2aBqVjXJbPnH7Sgpddv"/> <!-- API key -->
    <input type="hidden" name="scope" 		  value="login inquiry transfer"/>
    <input type="hidden" name="redirect_uri"  value="http://localhost:8080/coin/callback"/>
    <input type="hidden" name="auth_type" 	  value="0"/>
    <input type="hidden" name="response_type" value="code"/>
    <input type="hidden" name="state" 		  value="12345678901234567890123456789012"/>

	<input type="button" id="btn_submit" value="start"/>
</form>
<input type="text" id="auth_code" value="${auth_code}" readonly/>

<form id="tokenFrm" name="tokenFrm">
    <input type="text" name="client_id" 	value="MFaFJ3UTLzQ5TQRMhQPWQ2aBqVjXJbPnH7Sgpddv" readonly/> <!-- API key -->
    <input type="text" name="code" 		  	value="${auth_code}" />
    <input type="text" name="redirect_uri"  value="http://localhost:8080/coin/callback" readonly/>
    <input type="text" name="client_secret" value="gFbZnEIXtBo8I81Wl0dOJveti9yyX0m7ny9b3th3" readonly/>
    <input type="text" name="grant_type"    value="authorization_code" readonly/>

	<input type="button" id="btn_submit_token" value="start"/>
</form>
<div class="target"></div>

<div class="main">
	<input type="button" id="btn_acc_list" value="계좌 조회"/>
	<div class="cont">
		<table id="acc_list" name="acc_list" border=1>
			<thead>
				<tr>
					<th>계좌번호</th>
					<th>계좌 구분</th>
					<th>출금기관</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>
<script>

$(document).ready(function(){

});
$("#btn_acc_list").on('click', function(){
	$(".acc_list tbody").empty();
	fn_setAccList();
});
$('#btn_submit_token').on('click', function(){
	var frm = $("#tokenFrm");
	var formData = frm.serialize();
	$.ajax({
		url: "https://testapi.openbanking.or.kr/oauth/2.0/token",
		type:"POST",
		data: formData,
		success: function(data){
			console.log(data);
			var card = "";
			card += "<input type='textarea' id = 'access_token'  value ='"+data.access_token+"'  readonly/>";
			card += "<input type='textarea' id = 'refresh_token' value ='"+data.refresh_token+"' readonly/>";
			card += "<input type='textarea' id = 'user_seq_no'   value ='"+data.user_seq_no+"'   readonly/>";
			$(".target").html(card);
		},
		error: function(e){
			alert(e);
		}
	});
});

function fn_setAccList(){
	$.ajax({
		url: "./coin/list",
		success: function(data){

			var list = JSON.parse(data)
			console.log(list);
			var card = "";
			for(var i in list.res_list){
				card += "<tr class='tbl_tr'>";
				card += "<td>"+list.res_list[i].account_num_masked+"</td>";
				card += "<td>"+(list.res_list[i].account_holder_type == 'P'? "개인":"기타")+"</td>";
				card += "<td>"+list.res_list[i].bank_name+"</td>";
				card += "<td style='border:0;'><button type='button' class='tbl_btn' target='_blank' onclick='openPop(\""+list.res_list[i].fintech_use_num+"\")'>상세조회</button></td>";
				card += "</tr>";
			}

			$("#acc_list tbody").html(card);
		},
		error: function(e){
			alert(e);
		}
	});
}

function openPop(fintech){
	var popup = window.open('/coin/pop?fintech_use_num='+fintech, 'popOpen', 'width=500px,height=300px,scrollbars=yes');
};

$('#btn_submit').on('click', function(){
	var chkCode = $('#auth_code').val();
	if(chkCode == "" || !chkCode){
		var authorizeFrm = $('#authorizeFrm');
		window.open('','_authForm');
		authorizeFrm.submit();
	} else {
		alert("이미 발급된 코드가 있습니다.");
	}
});


</script>
<%@include file="./common/footer.jsp" %>