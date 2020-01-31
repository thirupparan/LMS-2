package com.invicta.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.dto.mapper.RoleDtoMapper;
import com.invicta.lms.entity.mapper.RoleMapper;
import com.invicta.lms.service.RoleService;
import com.invicta.lms.validation.RoleValidation;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	RoleValidation roleValidation;

	@GetMapping
	public ResponseEntity<List<RoleDto>> getRole() {
		return new ResponseEntity<>(RoleMapper.mapRoleListToRoleDtoList(roleService.viewAllRole()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
		if (roleService.findRoleById(id) != null) {
			return new ResponseEntity<>(RoleMapper.mapRoleToRoleDto(roleService.findRoleById(id)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {

		roleValidation.validateRole(roleDto);
		if (!roleValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(roleValidation.getErrors(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(
				RoleMapper.mapRoleToRoleDto(roleService.addRole(RoleDtoMapper.mapRoleDtoToRole(roleDto))),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@RequestBody RoleDto roleDto, @PathVariable Long id) {
		roleValidation.validateRole(roleDto);
		if(!roleValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(roleValidation.getErrors(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(roleService.updateRole(id, RoleDtoMapper.mapRoleDtoToRole(roleDto)), HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		return new ResponseEntity<>(roleService.deleteRole(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/checkAvailability")
	public ResponseEntity<?> roleAvailability(@RequestParam(value = "role") String role) {
		Map<String, String> errors = new HashMap<>();

		errors.clear();
		if (roleService.existsByRole(role)) {
			errors.put("Role", "Role Already exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}

		errors.put("Role", "Not exist");

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(errors);

	}
}
