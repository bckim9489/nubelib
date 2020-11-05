<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>
	<style>
		*{
			margin:0;
			padding:0;
		}
		.container{
			width: 500px;
			margin: 0 auto;
			padding: 25px
		}
		.container h1{
			text-align: left;
			padding: 5px 5px 5px 15px;
			color: #FFBB00;
			border-left: 3px solid #FFBB00;
			margin-bottom: 20px;
		}
		.chating{
			background-color: #000;
			width: 500px;
			height: 500px;
			overflow: auto;
		}
		.chating p{
			color: #fff;
			text-align: left;
		}
		input{
			width: 330px;
			height: 25px;
		}
		p.me{
			text-align: right;
		}
	</style>
<div id="container" class="container">
	<h1>채팅</h1>
	<input type="hidden" id="sessionId" value="">
	<div id="chating" class="chating">
	</div>

	<div id="yourMsg">
		<table class="inputTable">
			<tr>
				<th>메시지</th>
				<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
				<th><button onclick="send();" id="sendBtn">보내기</button></th>
			</tr>
		</table>
	</div>
</div>

<script>
	var ws;
	$(document).ready(function (){
		wsOpen();
	});

	function wsOpen(){
		ws = new WebSocket("ws://"+location.host+"/chating");
		wsEvt();
	}

	function wsEvt(){
		ws.onopen = function (data){

		}

		ws.onmessage = function (data){
			var msg = data.data;
			if(msg != null && msg.trim() != ""){
				var msgData = JSON.parse(msg);
				if(msgData.type == "getId"){
					var si = msgData.sessionId != null ? msgData.sessionId : "";
					if(si != ''){
						$("#sessionId").val(si);
					}
				}else if(msgData.type == "message"){
					if(msgData.sessionId == $("#sessionId").val()){
						$("#chating").append("<p class='me'>나 :" + msgData.msg + "</p>");
					}else{
						$("#chating").append("<p class='others'>" + msgData.userName + " :" + msgData.msg + "</p>");
					}

				}
			}
		}
		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){
				send();
			}
		});
	}


	function send() {
		var option = {
				type : 'message',
				sessionId : $("#sessionId").val(),
				userName : $("#userName").val(),
				msg: $("#chatting").val()
		}
		ws.send(JSON.stringify(option));
		$('#chatting').val("");
	}
</script>
<%@include file="./common/footer.jsp" %>