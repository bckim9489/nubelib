package com.nubelib.sbAchive.module.web.mybatis.mapper;

import java.util.List;

import com.nubelib.sbAchive.module.web.model.response.GetSelectCodeListResponseDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.GetSelectCodeListEntity;

public interface SelectCodeMapper {

	List<GetSelectCodeListResponseDTO> selectCode(GetSelectCodeListEntity entity);

}
