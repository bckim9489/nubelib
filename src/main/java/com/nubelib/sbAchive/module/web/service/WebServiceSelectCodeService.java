package com.nubelib.sbAchive.module.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubelib.sbAchive.module.web.model.response.GetSelectCodeListResponseDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.GetSelectCodeListEntity;
import com.nubelib.sbAchive.module.web.mybatis.mapper.SelectCodeMapper;

@Service
public class WebServiceSelectCodeService implements WebServiceSelectCodeServiceImp {

	@Autowired
	SelectCodeMapper mapper;
	public List<GetSelectCodeListResponseDTO> selectCode(String code, String useat) {
		GetSelectCodeListEntity entity = new GetSelectCodeListEntity(code, useat);
		return mapper.selectCode(entity);
	}
}
