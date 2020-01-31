package com.invicta.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.PermissionRequestDto;
import com.invicta.lms.dto.PermissionResponseDto;
import com.invicta.lms.entity.Permission;
import com.invicta.lms.repository.PermissionRepository;
import com.invicta.lms.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionContoller {
	
	@Autowired
	PermissionRepository permissionRepository ;
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("roleid/{roleid}")
	public ResponseEntity<List<PermissionResponseDto>> getPermission(@PathVariable Long roleid) {
		return new ResponseEntity<>(permissionService.findAllPermission(roleid), HttpStatus.OK);
		
	}
	
	@PutMapping("permissionid/{permissionid}")
	public Permission editPermission(@RequestBody PermissionRequestDto permissionRequestDto,@PathVariable Long permissionid) {
		Permission permission=permissionRepository.findPermissionById(permissionid);
		List<String> permissionlist=permission.getPermission();
		if(permissionRequestDto.isCheckstatus()==true) {
			
			permissionlist.add(permissionRequestDto.getRoleId());
			permission.setPermission(permissionlist);
			
		}if(permissionRequestDto.isCheckstatus()==false) {
			
			permissionlist.remove(permissionRequestDto.getRoleId());
			permission.setPermission(permissionlist);
		}
		
		return permissionRepository.save(permission);
	}
	
}
