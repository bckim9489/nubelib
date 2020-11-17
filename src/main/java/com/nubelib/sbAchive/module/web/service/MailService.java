package com.nubelib.sbAchive.module.web.service;

import com.nubelib.sbAchive.module.web.model.response.MailDTO;
import com.nubelib.sbAchive.module.web.mybatis.entity.MemberDTO;

public interface MailService {

	void mailSend(MailDTO mailDTO, MemberDTO memberDTO);

}
