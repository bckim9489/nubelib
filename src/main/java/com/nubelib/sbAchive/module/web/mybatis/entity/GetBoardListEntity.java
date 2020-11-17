package com.nubelib.sbAchive.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardListEntity {
	private String bbsId;
	private String title;
	private String contents;
	private int page;
}
