package com.winitech.katechSys.module.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;
import com.winitech.katechSys.module.web.service.MemberService;

@Controller
public class MapController {
	private static final Logger log = LoggerFactory.getLogger(MapController.class);

//	@Resource
//	MapService mapservice;

	@Resource
	MemberService memberService;

	@RequestMapping(value="/mapPage")
	public String board(ModelMap model, HttpSession session) {
		//로그인 인증
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		if(memberDTO != null) {

			model.addAttribute("name", memberDTO.getName());
			model.addAttribute("bbsTitle", "지도");
			return "maps";
		} else {
			return "redirect:/";
		}
	}
}
