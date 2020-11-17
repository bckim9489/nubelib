package com.nubelib.sbAchive.module.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nubelib.sbAchive.module.web.model.response.LoginDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.MemberDTO;
import com.nubelib.sbAchive.module.web.service.MemberService;

@Controller
public class BookingController {

	@Resource
	MemberService memberService;

	@RequestMapping("/booking")
	public String booking(ModelMap model, HttpSession session) {
		//로그인 인증
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		if(memberDTO != null) {

			model.addAttribute("name", memberDTO.getName());
			model.addAttribute("bbsTitle", "예매");
			return "booking";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/bookStart")
	public @ResponseBody String bookstart(ModelMap model, HttpSession session) throws IOException {
		String url = "http://www.letskorail.com/ebizprd/EbizPrdTicketpr21100W_pr21110.do";

		Connection.Response response;

		response = Jsoup.connect(url).method(Connection.Method.GET).execute();

		Document naverDocument = response.parse();
		Element elem = naverDocument.select("html").first();

		String result = elem.toString();
		return result;
	}
}
