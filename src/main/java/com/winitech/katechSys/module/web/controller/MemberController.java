package com.winitech.katechSys.module.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.model.response.MemberListDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;
import com.winitech.katechSys.module.web.service.MemberService;

@Controller
public class MemberController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Resource
	MemberService memberService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/proc")
	public String proc(HttpServletRequest request, ModelMap model) {

		LoginDTO loginDTO = new LoginDTO();

		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");

		loginDTO.setId(id);
		loginDTO.setPassword(pw);

		HttpSession session = request.getSession();
		session.setAttribute("userInfo", loginDTO);

		return "redirect:/board";
	}
	@RequestMapping("/memberList")
	public @ResponseBody List<MemberListDTO> memberList() {
		List<MemberListDTO> resultList = memberService.selectUserListAll();

		return resultList;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		session.invalidate();

		return "redirect:/login";
	}

	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public @ResponseBody int insertMember(MemberDTO memberDTO) {
		int result = memberService.insertUserList(memberDTO);

		return result;
	}
}
