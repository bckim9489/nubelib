<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="modal">
	<div class="modal-con modal">
  	<p class="title">회원 가입</p>
    	<div class="con">
	    	<form id="frm" name="frm">
				<table style="border-collapse: separate; border-spacing: 0 10px;">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" id="sign_id"/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="password" id="sign_pw"/></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" id="sign_name"/></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="date" name="age" id="sign_date"/></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" name="email" id="sign_email"/></td>
					</tr>
					<tr>
						<td>주소</td>
						<tr>
							<td><input type="text" id="addr_1" readonly/><a href="javaScript:void(0)">검색</a></td>
						<tr>
						<tr>
							<td><input type="text" id="addr_2" placeholder="상세주소"/></td>
						<tr>
					</tr>
				</table>
			</form>
		</div>
		<div class="modal_btn">
			<a href="javaScript:void(0);" class="sign_btn">가입</a>
			<a href="javascript:void(0);" class="cancel_btn">취소</a>
		</div>
	</div>
</div>
<script>
	$(".cancel_btn").on("click", function(){
		$("#modal").hide();
		$("."+modalname).hide();
	});

	$(".sign_btn").on("click", function(){
		if(frmchk()){
			var addr = "";
			addr += $("#addr_1").val();
			addr += " ";
			addr += $("#addr_2").val();
			var card = "<input type='hidden' name='address'>"+addr+"</input>"
			$("#frm").append(card);
			var params = $("#frm").serialize();
			console.log(params);
			$.ajax({
				url: "/signUp",
				type: "POST",
				data: params,
				success: function(data) {
					if(data != ""){
						alert("가입되었습니다!");
						$("#modal").hide();
						$("."+modalname).hide();
					} else {
						alert("오류");
					}
				},
				error: function() {
					alert("통신 오류");
				}
			});

		}
	});

	function frmchk(){
		//이메일 정규식
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		var id = $("#sign_id").val();
		var pw = $("#sign_pw").val();
		var name = $("#sign_name").val();
		var email = $("sign_email").val();

		if(id ==""){
			alert("아이디를 입력해 주세요");
			return false;
		}
		if(pw ==""){
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if(name=""){
			alert("이름을 입력해 주세요");
			return false;
		}
		if(email != "" && regExp.test(email) == false){
			alert("이메일 형식이 맞지 않습니다.");
			return false;
		}
		return true;
	}
</script>