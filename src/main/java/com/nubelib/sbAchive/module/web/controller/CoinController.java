package com.nubelib.sbAchive.module.web.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.nubelib.sbAchive.module.web.model.response.LoginDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.MemberDTO;
import com.nubelib.sbAchive.module.web.service.MemberService;

@Controller
public class CoinController {
	@Resource
	MemberService memberService;

	@RequestMapping(value="/coin")
	public String coin(ModelMap model, HttpSession session) {
		//로그인 인증
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		if(memberDTO != null) {

			model.addAttribute("name", memberDTO.getName());
			model.addAttribute("bbsTitle", "코인/계좌");
			return "coin";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value="/coin/callback")
	public String auth_coin(ModelMap model, HttpServletRequest request, HttpSession session) throws IOException {


		String result = "";

		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);
		model.addAttribute("name", memberDTO.getName());
		model.addAttribute("bbsTitle", "코인/계좌");

		result = request.getParameter("code");

		/*

		URL url = new URL("https://testapi.openbanking.or.kr/oauth/2.0/token");
		HttpURLConnection conn = null;
		conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(100000);
		conn.setReadTimeout(100000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);

		try {
			HashMap<String, Object> authParams = new HashMap<>();
		    authParams.put("code", authCode);
		    authParams.put("client_id", "MFaFJ3UTLzQ5TQRMhQPWQ2aBqVjXJbPnH7Sgpddv");
		    authParams.put("client_secret", "gFbZnEIXtBo8I81Wl0dOJveti9yyX0m7ny9b3th3");
		    authParams.put("grant_type", "authorization_code");
		    authParams.put("redirect_uri", "http://localhost:8080/coin/token");

			JSONObject json = new JSONObject(authParams);

			System.out.println(json.toString());

			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());

			wr.flush();


			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			while((line = rd.readLine()) != null) {
				result += line;
			}
		} catch (Exception e){
			System.out.println(e.toString());
		} finally {
			try {
				if(wr != null) {
					wr.close();
				}
				if(rd != null) {
					rd.close();
				}

			} catch (IOException ioe) {
				System.out.println(ioe.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
*/
		model.addAttribute("auth_code", result);

		return "coin";
		/*
		HashMap<String, String> authHeaders = new HashMap<>();
	    //authHeaders.put(,  charset=UTF-8");




	    JSONObject authRes = HttpUtils.httpPost("https://testapi.openbanking.or.kr/oauth/2.0/token", authHeaders, authParams);
	    String accessToken = (String) authRes.get("access_token");
	    String refreshToken = (String) authRes.get("refresh_token");
	    String userSeqNo = (String) authRes.get("user_seq_no");
	    String scope = (String) authRes.get("scope");
	    String tokenType = (String) authRes.get("token_type");

		*/
	}

	@RequestMapping(value="/coin/list")
	public @ResponseBody String list() throws UnirestException {

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get("https://testapi.openbanking.or.kr/v2.0/user/me?user_seq_no=1100765336")
		  .header("Accept", "application/json")
		  .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY1MzM2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTI3NjMyMDQsImp0aSI6IjY0OTAzZDkyLWVlNDctNGNjOC1iOGZlLTY3OTRiM2I5OGY2YiJ9.d0Q8XT5C1M3wfPQqOhwMxO6UbJTd4mw5dDKRYyxe2aw")
		  .asString();

		return response.getBody();

	}

	@RequestMapping(value="/coin/pop")
	public String pop(ModelMap model, HttpServletRequest request) throws UnirestException, ParseException{
		SimpleDateFormat timeForm = new SimpleDateFormat ( "yyyyMMddHHmmss");
		SimpleDateFormat miliForm = new SimpleDateFormat ( "HHmmssSSS");
		Date time = new Date();
		String fintechNum = request.getParameter("fintech_use_num");
		String userId = "T991669280";
		String bankTranId = miliForm.format(time);

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get("https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?bank_tran_id="+userId+"U"+bankTranId+"&fintech_use_num="+fintechNum+"&tran_dtime="+timeForm.format(time))
		  .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY1MzM2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTI3NjMyMDQsImp0aSI6IjY0OTAzZDkyLWVlNDctNGNjOC1iOGZlLTY3OTRiM2I5OGY2YiJ9.d0Q8XT5C1M3wfPQqOhwMxO6UbJTd4mw5dDKRYyxe2aw")
		  .asString();

		String result = response.getBody();
		JSONParser psr = new JSONParser();
		Object obj = psr.parse(result);
		JSONObject jsonObj = (JSONObject)obj;
		model.addAttribute("res_data", jsonObj);
		return "pop";
	}

	@RequestMapping(value="/coin/pop/list")
	public @ResponseBody String poplist(HttpServletRequest request) throws UnirestException {
		SimpleDateFormat timeForm = new SimpleDateFormat ( "yyyyMMddHHmmss");
		SimpleDateFormat fromForm = new SimpleDateFormat ( "yyyyMMdd");
		SimpleDateFormat miliForm = new SimpleDateFormat ( "HHmmssSSS");
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date agoTime = cal.getTime();

		String fintechNum = request.getParameter("fintech_use_num");
		String fromDate = fromForm.format(agoTime);
		String toDate = fromForm.format(time);
		String bankTranId = miliForm.format(time);

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991669280U"+bankTranId+"&fintech_use_num="+fintechNum+"&inquiry_type=A&inquiry_base=D&from_date="+fromDate+"&to_date="+toDate+"&sort_order=D&tran_dtime="+timeForm.format(time))
		  .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY1MzM2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTI3NjMyMDQsImp0aSI6IjY0OTAzZDkyLWVlNDctNGNjOC1iOGZlLTY3OTRiM2I5OGY2YiJ9.d0Q8XT5C1M3wfPQqOhwMxO6UbJTd4mw5dDKRYyxe2aw")
		  .asString();


		return response.getBody();

	}

}
