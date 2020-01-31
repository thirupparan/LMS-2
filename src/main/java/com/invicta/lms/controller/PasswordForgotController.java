package com.invicta.lms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.EmailDto;
import com.invicta.lms.entity.PasswordResetToken;
import com.invicta.lms.entity.User;
import com.invicta.lms.repository.PasswordResetTokenRepository;
import com.invicta.lms.service.EmailService;
import com.invicta.lms.service.UserService;

@RestController
@RequestMapping("/forgot-password")
public class PasswordForgotController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;

	@PostMapping
	public ResponseEntity<?> processForgotPasswordForm(@RequestParam(value = "email") String userEmail,
			HttpServletRequest request) throws MessagingException {
		Map<String, String> errors = new HashMap<>();

		User user = userService.findUserByEmail(userEmail);
		if (user == null) {
			errors.put("forgot-password", "We could not find an account for that e-mail address.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);
		EmailDto mail = new EmailDto();
		mail.setFrom("no-reply@memorynotfound.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Password reset request");

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user.getUserName());
		model.put("signature", "https://thirutech.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendSimpleMessage(mail);
		return new ResponseEntity<>("Sucessfully send email", HttpStatus.CREATED);

	}

}
