package com.winitech.katechSys.module.web.service;

import java.util.List;

import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.model.response.MemberListDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;

public interface MemberService {

	//회원 조회
	public MemberDTO selectUserList(LoginDTO loginDTO);
	//회원 전체 조회
	public List<MemberListDTO> selectUserListAll();
	//회원 등록
	public int insertUserList(MemberDTO memberDTO);
	//회원 수정
	public int updateUserList(LoginDTO loginDTO);
	//회원 삭제
	public int deleteUserList(LoginDTO loginDTO);


}
