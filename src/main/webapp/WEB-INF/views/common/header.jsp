<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Board</title>
<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="/js/common.js" ></script>
<script>
	$(document).on('scroll',function(e){
		var wheel = $(window).scrollTop();
		var linePosition = $(".line_position").offset().top;

		if(wheel < linePosition){
			$(".naver_crawling2").fadeIn("fast");
			$(".naver_crawling").fadeOut("fast");

		} else {
			$(".naver_crawling2").fadeOut("fast");
			$(".naver_crawling").fadeIn("fast");
		}
	});
</script>
<style>
	html,
	body {
	   margin:0;
	   padding:0;
	   height:100%;
	}
	.container {
	   min-height:100%;
	   position:relative;
	   padding-bottom:100px;/* footer height */
	}
	.footer {
	   width:100%;
	   height:100px;
	   position:absolute;
	   bottom:0;
	   background:#5eaeff;
	  text-align: center;
	  color: white;
	}
	nav {
		position: relative;
		z-index: 2;
		top: 0;
		left: 0;
		right: 0;
		margin-bottom : 20px;
		padding: 0;
		position: fixed;
	}
	.nav ul:not(.list_air) {
		background-color: #FFDAB9;
		list-style-type: none;
		margin: 0;
		padding: 0;
		overflow: hidden;

	}
	.nav li.nav_ul_li { float: left; }
	.nav li.user_Info { float: right; }

	.nav li a:not(.naver_crawling) {
		display: block;
		background-color: #FFDAB9;
		color: #000000;
		padding: 8px;
		text-decoration: none;
		text-align: center;
		font-weight: bold;
	}
	.nav li a.current  {
		background-color: #FF6347;
		color: white;
	}

	.nav li a:hover:not(.current) {
		background-color: #CD853F;
		color: white;
	}

	.naver_crawling {
		display: inline-block;
		overflow: hidden;
		border: 7px solid green;
		height: 24px;
	    width: 300px;
	    position: relative;
		z-index: 4;
		left: 39%;
	}

	.naver_crawling #NM_TS_ROLLING_WRAP {

	    height: 42px;
	    width: 300px;
	    padding: 0px;
	    background-color: white;
	    color: black;

	}
	.naver_crawling #NM_TS_ROLLING_WRAP a:link { color: black; text-decoration: none;}
	.naver_crawling #NM_TS_ROLLING_WRAP a:visited { color: black; text-decoration: none;}
	.naver_crawling #NM_TS_ROLLING_WRAP a:hover { color: black; text-decoration: none;}
	.naver_crawling #NM_TS_ROLLING_WRAP a{
		cursor: pointer;
    	text-decoration: none;
	}
	.naver_crawling #NM_TS_ROLLING_WRAP a div{
		display:inline;
	}
	.naver_crawling #NM_TS_ROLLING_WRAP i{
		margin-right: 10px;
		color:black;
		font-weight: bold;
		font-style: normal;
		text-shadow: 1px 1px 1px #000;
	}
	.naver_crawling #NM_TS_ROLLING_WRAP ul{
		list-style: none;
		margin: 0;
		padding: 0;
		display:inline;
	}
	.naver_crawling #NM_TS_ROLLING_WRAP ul li{
		display:inline-block;
	}

	.blind {
		display:none;
	}

	.naver_crawling2 {
		display: block;
		overflow: hidden;
		border: 7px solid green;
		height: 24px;
	    width: 600px;
	    position: relative;
		z-index: 4;
		top: 27px;
		left: 30%;
		blackground-color: white;
	}

	.naver_crawling2 #yna_rolling {
		height: 42px;
		margin: 0;
	    padding: 0px;
	    background-color: white;
	    color: black;
	}
	.naver_crawling2 #yna_rolling div{
		height: 42px;
		margin: 0;
		padding: 0;
		display: inline-block;
	}
	.naver_crawling2 #yna_rolling a {
		margin: 0;
		padding: 0;
		font-size: 19px;
	}
	.naver_crawling2 #yna_rolling a:link { color: black; text-decoration: none;}
	.naver_crawling2 #yna_rolling a:visited { color: black; text-decoration: none;}
	.naver_crawling2 #yna_rolling a:hover { color: black; text-decoration: none;}
	.big_door {

	}
</style>
</head>
<body>
		<div class="big_door" style="height:200px; background-color:#FFDAB9; text-align: center; margin-bottom:20px;" >

			<a class="door_title" style="font-size:100px; position: relative; z-index: 2; top:30px;">${bbsTitle}</a>
			<div class="naver_crawling2"></div>
			<div class="line_position"></div>
			<input type="hidden" id="userName" value="${name}"/>


		</div>


		<nav class="board header">

			<div class = "header nav">
				<ul class ="header nav_ul">
					<li class="header nav_ul_li home "><a href="" class="current">Home</a></li>
					<li class="header nav_ul_li archive"><a href="/board">자료실</a></li>
					<li class="header nav_ul_li menual"><a href="/mailPage">메일</a></li>
					<li class="header nav_ul_li menual"><a href="/mapPage">지도</a></li>
					<li class="header nav_ul_li menual"><a href="/chat">채팅</a></li>
					<li class="header nav_ul_li menual"><a href="/booking">예매</a></li>
					<li class="header nav_ul_li menual"><a href="/booking">코인</a></li>
					<div class="naver_crawling"></div>
					<li class="header user_Info user_logout"><a href="/logout" style="color:gray;">로그아웃</a></li>
					<li class="header user_Info user_info"><a href="" style="color:gray;">회원정보</a></li>
					<li class="header user_Info user_name"><a href="" style="color:white;">${name} 님</a></li>
				</ul>
			</div>

		</nav>
<script>
	ajaxCrawling();
	ajaxCrawling2();
	$(".naver_crawling").hide();
	setInterval(slider,2500);
	setInterval(slider2,2500);

	function ajaxCrawling2(){
		$.ajax({
			url:"/crawling2",
			type:"POST",
			success: function(data){


				$(".naver_crawling2").html(data);
				$("#yna_rolling").find("div").not(":first").hide();
			}
		});
	}

	function ajaxCrawling(){
		$.ajax({
			url:"/crawling",
			type:"POST",
			success: function(data){
				$(".naver_crawling").html(data);
				$("#NM_TS_ROLLING_WRAP").find("a").not(":first").hide();
			}
		});
	}
	function slider2(){
		$("#yna_rolling").find("div").first().detach();
		$("#yna_rolling").find("div").first().show();
		if($("#yna_rolling").find("div").length == 0){
			ajaxCrawling2();
		}
	}

	function slider(){
		$("#NM_TS_ROLLING_WRAP").find("a").first().detach();
		$("#NM_TS_ROLLING_WRAP").find("a").first().show();
		if($("#NM_TS_ROLLING_WRAP").find("a").length == 0){
			ajaxCrawling();
		}
	}
</script>