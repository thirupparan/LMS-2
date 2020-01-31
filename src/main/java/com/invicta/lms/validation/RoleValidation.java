package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.service.RoleService;

@Service
public class RoleValidation {
	@Autowired
	RoleService roleService;

	Map<String, String> errors = new HashMap<String, String>();

	public void validateRole(RoleDto roleDto) {

		errors.clear();

		if (roleDto.getRoleName() == null) {
			errors.put("roleName", "Role cannot be null");
		}

		if (roleDto.getRoleName() == "") {
			errors.put("roleName", "Role cannot be Empty");
		}

		if (roleDto.getId()==null && roleService.existsByRole(roleDto.getRoleName())) {
			errors.put("roleName", "Role Already exist");
		}
		if (roleDto.getId()!=null && roleService.existsByRoleLockId(roleDto.getId(),roleDto.getRoleName())) {
			errors.put("roleName", "Role Already exist");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
