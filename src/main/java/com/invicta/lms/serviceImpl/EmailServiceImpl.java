package com.invicta.lms.serviceImpl;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.invicta.lms.dto.EmailDto;
import com.invicta.lms.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public String sendSimpleMessage(EmailDto emailDto) throws MessagingException {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(emailDto.getModel());
			String html = templateEngine.process("email/email-template", context);
			helper.setTo(emailDto.getTo());
			helper.setText(html, true);
			helper.setSubject(emailDto.getSubject());
			helper.setFrom(emailDto.getFrom());
			emailSender.send(message);
			return "email sent successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}

	}

}
