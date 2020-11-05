package com.winitech.katechSys.module.web.mybatis.mapper;

import java.util.List;

import com.winitech.katechSys.module.web.model.response.GetSelectCodeListResponseDTO;
import com.winitech.katechSys.module.web.mybatis.entity.GetSelectCodeListEntity;

public interface SelectCodeMapper {

	List<GetSelectCodeListResponseDTO> selectCode(GetSelectCodeListEntity entity);

}
