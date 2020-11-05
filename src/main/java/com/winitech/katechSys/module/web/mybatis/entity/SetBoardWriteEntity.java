package com.winitech.katechSys.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetBoardWriteEntity {
	private String bbsId;
	private String title;
	private String contents;
	private String noticeYn;
	private String ip;
}
