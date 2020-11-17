<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Popup</title>
<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="/js/common.js" ></script>
</head>
<body>

	<div class="">
		<div class="acc_info">
			<p class="product_name" data-name="${res_data.product_name}">계좌 상품명: ${res_data.product_name}</p>
			<p>계좌 은행: ${res_data.bank_name}</p>
			<p id="fintech_acc" data-fin="${res_data.fintech_use_num}">핀테크 계좌: ${res_data.fintech_use_num}</p>
			<p class="balance" data-bal="${res_data.balance_amt}">총 잔액: ${res_data.balance_amt}</p>
			<p>사용가능 잔액: ${res_data.available_amt}</p>
			<a href="javaScript:void(0);" class ="acc_detail" style="text-decoration:none; float:right;">거래 내역 조회</a>
			<br/>
			<hr >
			<div class="acc_detail_list"></div>
		</div>
	</div>

	<script>
		$(document).ready(function(){
			if(!$(".product_name").attr("data-name")){
				$(".acc_info").empty();
				$(".acc_info").html("<p>계좌 정보가 없습니다.</p>");
			}
		});
		$(".acc_detail").on("click", function(){
			$.ajax({
				url:"/coin/pop/list",
				data:{
					"fintech_use_num" : $("#fintech_acc").attr("data-fin")
				},
				type:"POST",
				success: function(jdata){
					var list = JSON.parse(jdata);
					var optionSet = [
						{ index : 'inout_type', value : '출금', target: 'body', style : 'color:red;'},
						{ index : 'inout_type', value : '입금', target: 'body', style : 'color:green;'}
					];
					console.log(list);
					var data = {
						header: {
							index: [ "inout_type", "tran_date", "print_content", "tran_amt", "after_balance_amt"],
							name:  [ "거래분류"    , "거래일자"   , "거래명", "거래금액", "잔액"],
							hidden:[0, 0, 0]
						},
						body: list.res_list,
						option: optionSet
					}

					fn_resize();
					tbl_create(data, "test", "acc_detail_list", "class");

				},
				error: function(e){
					alert(e);
				}
			});

		});

		function fn_resize(){
			window.resizeTo(518,700);
		}
	</script>
</body>
</html>