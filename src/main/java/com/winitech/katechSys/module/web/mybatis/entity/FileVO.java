package com.winitech.katechSys.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
	private int fno;
	private String bno;
	private String bbsId;
	private String fileName;
	private String fileOriName;
	private String fileUrl;

}
