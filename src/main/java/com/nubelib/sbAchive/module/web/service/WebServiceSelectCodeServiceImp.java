package com.nubelib.sbAchive.module.web.service;

import java.util.List;

import com.nubelib.sbAchive.module.web.model.response.GetSelectCodeListResponseDTO;

public interface WebServiceSelectCodeServiceImp {
	public List<GetSelectCodeListResponseDTO> selectCode(String code, String useat);
}
