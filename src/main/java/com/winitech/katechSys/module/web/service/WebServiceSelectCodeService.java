package com.winitech.katechSys.module.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winitech.katechSys.module.web.model.response.GetSelectCodeListResponseDTO;
import com.winitech.katechSys.module.web.mybatis.entity.GetSelectCodeListEntity;
import com.winitech.katechSys.module.web.mybatis.mapper.SelectCodeMapper;

@Service
public class WebServiceSelectCodeService implements WebServiceSelectCodeServiceImp {

	@Autowired
	SelectCodeMapper mapper;
	public List<GetSelectCodeListResponseDTO> selectCode(String code, String useat) {
		GetSelectCodeListEntity entity = new GetSelectCodeListEntity(code, useat);
		return mapper.selectCode(entity);
	}
}
