package com.winitech.katechSys.module.web.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.InternetAddress;


public class MailHandler {
	private JavaMailSender sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	//생성자
	public MailHandler(JavaMailSender jSender) throws MessagingException {
		this.sender = jSender;
		message = jSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}
	//보낸 쪽
	public void setFrom(String fromAddress) throws MessagingException {
		messageHelper.setFrom(fromAddress);
	}
	//받는 쪽 여러명
	public void setTo(String[] toAddr) throws MessagingException {
		messageHelper.setTo(toAddr);
	}
	//받는 쪽 한명
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}

	//title
	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}
	//contents
	public void setText(String text, boolean useHtml) throws MessagingException {
		messageHelper.setText(text, useHtml);
	}
	//attachment
	public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToAttachment).getFile();
		FileSystemResource fsr = new FileSystemResource(file);

		messageHelper.addAttachment(displayFileName, fsr);
	}
	//image
	public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToInline).getFile();
		FileSystemResource fsr = new FileSystemResource(file);

		messageHelper.addInline(contentId, fsr);
	}

	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
