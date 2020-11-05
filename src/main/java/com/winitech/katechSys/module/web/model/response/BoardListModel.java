package com.winitech.katechSys.module.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardListModel {
	private String bbsId;

    private String title;
    private String contents;
    private String wrterName;
    private String noticeYn;
//    private String ip;
    private String regDtime;
}
