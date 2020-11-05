package com.winitech.katechSys.module.web.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.winitech.katechSys.module.web.model.response.GetBoardListDTO;
import com.winitech.katechSys.module.web.model.response.TestResponseDTO;
import com.winitech.katechSys.module.web.mybatis.entity.GetBoardListEntity;
import com.winitech.katechSys.module.web.mybatis.entity.TestEntity;

@Service
@Mapper
public interface TestMapper {

	List<TestResponseDTO> mapReqTest(TestEntity entity);


}
