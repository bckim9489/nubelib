package com.winitech.katechSys.module.web.service;

import java.util.List;

import com.winitech.katechSys.module.web.model.response.GetSelectCodeListResponseDTO;

public interface WebServiceSelectCodeServiceImp {
	public List<GetSelectCodeListResponseDTO> selectCode(String code, String useat);
}
