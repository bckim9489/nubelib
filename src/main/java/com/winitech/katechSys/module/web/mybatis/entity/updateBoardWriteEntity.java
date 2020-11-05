package com.winitech.katechSys.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateBoardWriteEntity {
	private String bbsId;
	private String bbscttSn;
	private String title;
	private String contents;
	private String noticeYn;
}
