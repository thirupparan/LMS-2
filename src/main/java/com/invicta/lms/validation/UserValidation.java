package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.UserDtoRequest;
import com.invicta.lms.service.UserService;

@Service
public class UserValidation {

	@Autowired
	UserService userService;
	Map<String, String> errors = new HashMap<>();

	public void validationUser(UserDtoRequest userDtoRequest) {
		errors.clear();
		if (userDtoRequest.getId() == null) {
			if( userService.existsByUsername(userDtoRequest.getUserName())) {
				errors.put("userName", "User name Alreay exist!");
			}
			if (userDtoRequest.getPassword() == null) {
				errors.put("password", "Password cannot be null");
			}
			if (userDtoRequest.getPassword() == "") {
				errors.put("password", "Password  cannot be Empty");
			}
		}
		
		
		
		if (userDtoRequest.getId() != null
				&& userService.existsByUsernameAndId(userDtoRequest.getId(), userDtoRequest.getUserName())) {
				errors.put("userName", "User name Alreay exist!");
		}
		

		if (userDtoRequest.getUserName() == null) {
			errors.put("userName", "User name cannot be null");
		}
		if (userDtoRequest.getUserName() == "") {
			errors.put("userName", "User name cannot be Empty");
		}

		if (userDtoRequest.getRole() == null) {
			errors.put("role", "Rloe is Required");
		}
		if (userDtoRequest.getEmail() == null) {
			errors.put("mail", "Email cannot be null");
		}
		if (userDtoRequest.getEmail() == "") {
			errors.put("mail", "Email is Required");
		}
	
		if (userDtoRequest.getId() == null && userService.existsByEmail(userDtoRequest.getEmail())) {
			errors.put("mail", "Email Alreay exist!");
		}
		if (userDtoRequest.getId() != null
				&& userService.existsByEmailAndId(userDtoRequest.getId(), userDtoRequest.getEmail())) {
				errors.put("mail", "Email Alreay exist!");
		}

		
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
