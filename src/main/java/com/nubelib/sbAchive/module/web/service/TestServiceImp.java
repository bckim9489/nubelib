package com.nubelib.sbAchive.module.web.service;

import java.util.List;

import com.nubelib.sbAchive.module.web.model.response.TestResponseDTO;

public interface TestServiceImp {

	public List<TestResponseDTO> mapReqTest(List<String> list);

}
