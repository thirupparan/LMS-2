package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.DesignationDto;
import com.invicta.lms.service.DesignationService;

@Service
public class DesignationValidation {
	@Autowired
	DesignationService designationService;

	Map<String, String> errors = new HashMap<String, String>();

	public void validateDesignation(DesignationDto designationDto) {
		
		errors.clear();

		if (designationDto.getDesignation() == null) {
			errors.put("designation", "designation cannot be null");
		}

		if (designationDto.getDesignation() == "") {
			errors.put("designation", "designation cannot be Empty");
		}

		if (designationService.existsByDesignation(designationDto.getDesignation())) {
			errors.put("designation", "Designation Already exist");
		}
//		if(designationService.findDesignationById(designationDto.getId())) {
//			errors.put("designation", "Id Exist");
//		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
