package com.winitech.katechSys.module.web.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.winitech.katechSys.common.web.util.ObjectMapperUtils;
import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.model.response.MemberListDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;
import com.winitech.katechSys.module.web.mybatis.entity.bcMember;
import com.winitech.katechSys.module.web.mybatis.entity.bcMemberRepository;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	bcMemberRepository repository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public MemberDTO selectUserList(LoginDTO loginDTO) {
		String id =loginDTO.getId();
		String password =loginDTO.getPassword();
		MemberDTO memberDTO = modelMapper.map(repository.findByIdAndPassword(id, password), MemberDTO.class);

		return memberDTO;

	}

	@Override
	public int insertUserList(MemberDTO memberDTO) {
		bcMember result = repository.save(memberDTO.toEntity());

		if(ObjectUtils.isEmpty(result)) {
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public List<MemberListDTO> selectUserListAll() {
		List<bcMember> memberEntityList = repository.findAll();
		List<MemberListDTO> memberDTOList = ObjectMapperUtils.mapAll(memberEntityList, MemberListDTO.class);

		return memberDTOList;
	}

	@Override
	public int updateUserList(LoginDTO loginDTO) {

		return 0;
	}

	@Override
	public int deleteUserList(LoginDTO loginDTO) {

		return 0;
	}

}
