package com.invicta.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.invicta.lms.dto.PasswordResetDto;
import com.invicta.lms.entity.PasswordResetToken;
import com.invicta.lms.entity.User;
import com.invicta.lms.repository.PasswordResetTokenRepository;
import com.invicta.lms.service.UserService;

@RestController
@RequestMapping("/reset-password")
public class PasswordResetController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("passwordResetForm")
	public PasswordResetDto passwordReset() {
		return new PasswordResetDto();
	}

	@GetMapping
	public ResponseEntity<?> verifytoken(@RequestParam(required = false) String token) {
		Map<String, String> errors = new HashMap<>();
		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		if (resetToken == null) {
			errors.put("error", "Could not find password reset token.");
		} else if (resetToken.isExpired()) {
			errors.put("error", "Token has expired, please request a new password reset.");
		} else {
			errors.put("token", resetToken.getToken());
			// return ResponseEntity.status(HttpStatus.FOUND).body(errors);
			return new ResponseEntity<>("reset-password", HttpStatus.ACCEPTED);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

	}

	@PostMapping
	@Transactional
	public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
			redirectAttributes.addFlashAttribute("passwordResetForm", form);
			return "redirect:/reset-password?token=" + form.getToken();
		}
		PasswordResetToken token = tokenRepository.findByToken(form.getToken());
		User user = token.getUser();
		String updatedPassword = passwordEncoder.encode(form.getPassword());
		userService.updatePassword(updatedPassword, user.getId());
		tokenRepository.delete(token);
		return "redirect:/login?resetSuccess";

	}
}
