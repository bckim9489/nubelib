package com.winitech.katechSys.module.web.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardDetailDTO {
//	private String bbsId;
//	private int bbscttSn;
    private String title;
    private String contents;
    private String wrterName;
    private String noticeYn;
    private String regDtime;
    private int pageNext;
    private int pagePerv;
    private String pageNextSj;
    private String pagePervSj;
    private String fileName;
    private int fno;
    private String fileOriName;
    private String fileUrl;
}
