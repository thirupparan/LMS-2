package com.invicta.lms.service;

import javax.mail.MessagingException;

import com.invicta.lms.dto.EmailDto;

public interface EmailService {
	public String sendSimpleMessage(EmailDto emailDto) throws MessagingException;
}
