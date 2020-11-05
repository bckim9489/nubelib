package com.winitech.katechSys.module.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.model.response.MailDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;
import com.winitech.katechSys.module.web.service.MailService;
import com.winitech.katechSys.module.web.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {

	@Resource
	MemberService memberService;

	private final MailService mailService;

	@RequestMapping(value="/mailPage")
	public String mailPage(ModelMap model, HttpSession session) {
		//로그인 인증
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		if(memberDTO != null) {

			model.addAttribute("name", memberDTO.getName());
			model.addAttribute("bbsTitle", "메일");
			return "mailPage";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/mailSend")
	public String execMail(MailDTO mailDTO, HttpSession session) {
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		mailService.mailSend(mailDTO, memberDTO);
		return "redirect:/mailPage";
	}
}
