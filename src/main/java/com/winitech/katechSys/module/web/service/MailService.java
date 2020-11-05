package com.winitech.katechSys.module.web.service;

import com.winitech.katechSys.module.web.model.response.MailDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;

public interface MailService {

	void mailSend(MailDTO mailDTO, MemberDTO memberDTO);

}
