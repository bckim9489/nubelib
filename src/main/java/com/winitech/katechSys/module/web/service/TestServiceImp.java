package com.winitech.katechSys.module.web.service;

import java.util.List;

import com.winitech.katechSys.module.web.model.response.TestResponseDTO;

public interface TestServiceImp {

	public List<TestResponseDTO> mapReqTest(List<String> list);

}
