package com.winitech.katechSys.module.web.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardListDTO {
	private String bbsId;
	private int bbscttSn;
    private String title;
    //private String contents;
    private String wrterName;
    private String noticeYn;
//    private String ip;
    private String regDtime;
}
