package com.winitech.katechSys.module.web.service;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.winitech.katechSys.module.web.model.response.MailDTO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

	private JavaMailSender mailSender;

    public void mailSend(MailDTO mailDTO, MemberDTO memberDTO) {
    	try {
    		MailHandler mailHandler = new MailHandler(mailSender);

//			mailHandler.setTo(mailDTO.getAddress());
    	    String mailAddress = mailDTO.getAddress();
    	    String[] mailAddressArry = null;
    	    if(mailAddress.contains(",")) {
    	    	 mailAddressArry = mailAddress.split(",");
    	    	 mailHandler.setTo(mailAddressArry);
    	    } else {
    	    	mailHandler.setTo(mailAddress);
    	    }
    	    mailHandler.setFrom(memberDTO.getEmail()); //보낸 회원 이메일 주소
    	    mailHandler.setSubject(mailDTO.getTitle());

    	    String htmlContent = mailDTO.getMessage(); //내용
    	    System.out.println(htmlContent);
    	    //회원정보
    	    htmlContent += "사번 : " + memberDTO.getMid();
    	    htmlContent += "아이디 : " + memberDTO.getId();
    	    htmlContent += "이름 : " + memberDTO.getName();


    	    mailHandler.setText(htmlContent, false);
//    	    mailHandler.setAttach("",""); //첨부파일
//    	    mailHandler.setInline("",""); //이미지
    	    mailHandler.send();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    }

}
