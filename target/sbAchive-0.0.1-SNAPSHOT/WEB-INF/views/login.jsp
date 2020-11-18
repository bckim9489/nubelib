<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
<style>
	#mask {
		position:absolute;
	  	left:0;
	  	top:0;
	  	z-index:9000;
	  	background-color:#000;
	  	display:none;
	}
	#modal{
	  display:none;
	  position:fixed;
	  width:100%; height:100%;
	  top:0; left:0;
	  background:rgba(0,0,0,0.3);
	}
	.modal-con{
	  display:none;
	  position:fixed;
	  top:50%; left:50%;
	  transform: translate(-50%,-50%);
	  max-width: 60%;
	  min-height: 30%;
	  background:#fff;
	}
	.modal-con .title{
	  font-size:20px;
	  padding: 0px;
	  background : green;
	  color:white;
	}
	.modal-con .con{
	  font-size:15px; line-height:1.3;
	  padding: 30px;
	}
	.modal-con .close{
	  display:block;
	  position:absolute;
	  width:30px; height:30px;
	  border-radius:50%;
	  border: 3px solid #000;
	  text-align:center; line-height: 30px;
	  text-decoration:none;
	  color:#000; font-size:15px; font-weight: bold;
	  right:10px; top:10px;
	}

</style>
<title>Login</title>
</head>
<body>
	<div id="login_wrapper" style="width: 310px; margin:auto;">
		<div id="content">
			<form id="frmL" name="frmL" method="post" action="">
				<input type="hidden" name="" value=""/>
				<table style="display:inline-block;">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td>아이디: </td>
							<td><input type="text" id="member_id" class="login_frm" name="member_id" value=""></td>
						</tr>
						<tr>
							<td>비밀번호: </td>
							<td><input type="password" id="member_pw" class="login_frm" name="member_pw" value=""></td>
						</tr>
					</tbody>
				</table>
				<a href="javaScript:void(0);" id="btn_login" style="display:inline-block;"><b>로그인</b></a>
			</form>
			<a href="javascript:openModal('modal');" id="btn_sign_up" style="display:inline-block;"><b>회원가입</b></a>
		</div>
		<jsp:include page="./modal.jsp"/>
	</div>
	<script>

		$("#btn_login").on('click', function(){
			var frmL = $("#frmL");
			frmL.attr('action', '/proc');
			if(fn_formchk()){
				frmL.submit();
			}
		});

		$(".login_frm").keypress(function(key){
			if(key.which == 13){
				var frmL = $("#frmL");
				frmL.attr('action', '/proc');
				if(fn_formchk()){
					frmL.submit();
				}
			}
		});

		function fn_formchk(){
			var memberId = $("#member_id");
			var memberPw = $("#member_pw");
			if(!memberId.val()){
				alert("아이디를 입력하세요");
				memberId.focus();
				return false;
			}
			if(!memberPw.val()){
				memberPw.focus();
				alert("비밀번호를 입력하세요");
				return false;
			}
			return true;
		}

		function openModal(modalname){
			  document.get
			  $("#modal").fadeIn(300);
			  $("."+modalname).fadeIn(300);
		}

	</script>
</body>
</html>