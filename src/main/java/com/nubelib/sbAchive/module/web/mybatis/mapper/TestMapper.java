package com.nubelib.sbAchive.module.web.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.nubelib.sbAchive.module.web.model.response.GetBoardListDTO;
import com.nubelib.sbAchive.module.web.model.response.TestResponseDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.GetBoardListEntity;
import com.nubelib.sbAchive.module.web.mybatis.entity.TestEntity;

@Service
@Mapper
public interface TestMapper {

	List<TestResponseDTO> mapReqTest(TestEntity entity);


}
