package com.winitech.katechSys.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardDetailEntity {
	private String bbsId;
	private int bbscttSn;
}
