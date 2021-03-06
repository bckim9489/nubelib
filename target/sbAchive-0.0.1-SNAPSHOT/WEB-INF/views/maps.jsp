<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/header.jsp" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=acc52117f73ac68dcfec6bb8c3e48438&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="js/jquery.ajax-cross-origin.min.js"></script>
<style>

	div.div_left {
		float: left;
		margin-left:10px;
	}
	div.div_right {

		display: inline-block;
		margin-left:10px;
	}
	div#target_station {
		display: inline-block;
	}

</style>
<div class="container">

	<div class="div_left">
		<div id="map" style="width:600px;height:600px;"></div>
	</div>


	<div class="div_right">
	<form action="/mailSend" method="post">
		<div align="left">
      		도착지: <input type="text" id="dist" size="20" class="form-control>"/>
      		<input type="hidden" id="mail_address" name="address" size="120" style="width:70%;" class="form-control" >
			<button type="button" name="search_btn" id="search_btn" class="">찾기</button>
    	</div>
  	</form>
  	<div class="div_right">
  		<p>[정류장 목록]</p>
  		<div align="center" id="target_station">
  		</div>
  	</div>
  	</div>
</div>
<script>
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};
	var map = new kakao.maps.Map(container, options);
	
	$(document).ready(function(){
	
		defaultSet();
		setInterval(defaultSet, 15000);
	});
	
	function defaultSet(){
	
	
		if (navigator.geolocation) {
	
		    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
		    navigator.geolocation.getCurrentPosition(function(position) {
	
		        var lat = position.coords.latitude, // 위도
		            lon = position.coords.longitude; // 경도
	
		        var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
	
		        // 마커와 인포윈도우를 표시합니다
		        displayMarker(locPosition);
		        fn_busStop(lat,lon);
	
		      });
	
		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	
		    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667);
	
		    displayMarker(locPosition);
		}
	}


	// 지도에 마커와 인포윈도우를 표시하는 함수입니다
	function displayMarker(locPosition, setFlag) {
		if(!setFlag || setFlag =='undefined'){
			setFlag = "0";
		}
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: locPosition
	    });

	    // 지도 중심좌표를 접속위치로 변경합니다
	    if(setFlag == "0"){
	    	map.setCenter(locPosition);
	    }
	}

	function fn_search(srch_key){
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(srch_key, function(result, status) {

		    // 정상적으로 검색이 완료됐으면
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });


		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		        //fn_busStop(result[0].y,result[0].x);
		    }
		});
	}

	function sortResults(result, prop, asc) {
	    SortedResult = result.sort(function(a, b) {

	    	 if (asc) {
                 return (parseInt(a[prop]) > parseInt(b[prop])) ? 1 : ((parseInt(a[prop]) < parseInt(b[prop])) ? -1 : 0);
             } else {
                 return (parseInt(b[prop]) > parseInt(a[prop])) ? 1 : ((parseInt(b[prop]) < parseInt(a[prop])) ? -1 : 0);
             }
         });

	    return SortedResult
	}

	function uniqueJson(data, target){
		for(var i = 0; i<data.length; i++){
			var tmp = data[i];
			var tmpName = tmp[target];
			for(var j = i+1; j<data.length; j++){
				if(tmpName != data[j][target]){
					continue;
				} else {
					data.splice(j,1);
				}
			}
		}
		return data;
	}


	function fn_setArrive(jdata){
		var card = "";
		$.each(jdata, function(key, value){
			//card += "<td>";
			for(var i in value){
				if(i=="arrprevstationcnt"){
					var chr ="<td >";
					switch(value[i]){
						case '1':
							chr += "<span style='color:red;'>전";
							break;
						case '2':
							chr += "<span style='color:red;'>전전";
							break;
						default :
							chr += "<span>"+value[i] +"개 ";
							break;
					}
					card += chr+" 정류장</span></td>";
				} else {
					card += "<td>"+value[i]+"</td> ";
				}

			}
			//card +="</td>";
		});
		return card;
	}

	function fn_setStation(jdata){
		var card = "";
		var lat = "";
		var lon = "";
		card += "<table class='station' border=1>";
		$.each(jdata, function(key, value){
			card += "<tr>"
			for(var i in value){

				if(i=='nodenm'){
					card +="<th class='"+i+"'>"+value[i]+"</th>";
				} else {
					if(i=='nodeid'){
						var arrData = fn_busArrive(22, value[i]);
						var sortData = sortResults(arrData.arrive, "arrprevstationcnt", true);

						var unqData = uniqueJson(sortData, "routeno");
						card += fn_setArrive(unqData);
					}
					card +="<input type='hidden' class='"+i+"' value='"+value[i]+"'/>";
				}
				if(i == 'gpslati'){
					lat = value[i];
				}
				if(i == 'gpslong'){
					lon = value[i];
				}

			}
			card +="</tr>";

			var imageSrc = '../img/busMarker.png';
		    var imageSize = new kakao.maps.Size(34, 39);
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
			var locPosition = new kakao.maps.LatLng(lat, lon);

			var marker = new kakao.maps.Marker({
				position : locPosition,
				image : markerImage
			});
			marker.setMap(map);
		});
		card +="</table>";
		$("#target_station").html(card);
	}

	function fn_busStop(x,y){

		var url='http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList';
		var serviceKey = 'YjfGjp0gllE2eZVEJoCjPsIoCfTllyHJcw68Imthn4Q8SGbczqUX8HMhPBOIhXj73x+dkd5ZRAyRYy0Hx5ul+Q==';
		//var token = localStorage.getItem("token");
		$.ajax({
			url: url,
			data: {
				"serviceKey" : serviceKey,
				"gpsLati": x,
				"gpsLong": y
			},
			type:"GET",
//			crossOrigin: true,
//			beforeSend: function(xhr){
//				xhr.setRequestHeader('Access-Control-Allow-Origin', '*' );
//			},

			success: function(data){

				var xmlData = $(data).find("item");

				var dataForm = {
					dataName : "bus",
					dataHeader : ['nodenm', 'gpslati', 'gpslong', 'nodeid']
				};

				var option = {
						'citycode': 22
				};

				var result = xmlParser(xmlData, dataForm, option); //common.js
				console.log(result);
				fn_setStation(result.bus);
			}
		});
	}

	function fn_busArrive(cityCode, nodeId){
		var serviceKey = 'YjfGjp0gllE2eZVEJoCjPsIoCfTllyHJcw68Imthn4Q8SGbczqUX8HMhPBOIhXj73x+dkd5ZRAyRYy0Hx5ul+Q==';
		var result;
		$.ajax({
			url: "http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList",
			async: false,
			data: {
				"serviceKey" : serviceKey,
				"nodeId" : nodeId,
				"cityCode" : cityCode
			},
			type: "GET",
			success: function(data){
				var xmlData = $(data).find("item");
				var dataForm = {
						dataName : "arrive",
						dataHeader : ["routeno", "arrprevstationcnt"]
				};
				result = xmlParser(xmlData, dataForm, "");
			}
		});
		return result;
	}

	$("#search_btn").on("click", function(){
		var srch_key = $("#dist").val();
		fn_search(srch_key);
	});
	
	$(function(){
		
	})
</script>

<%@include file="./common/footer.jsp" %>