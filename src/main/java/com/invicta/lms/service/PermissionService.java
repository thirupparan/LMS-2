package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.dto.MenuResponseDto;
import com.invicta.lms.dto.PermissionResponseDto;

public interface PermissionService {
	public List<MenuResponseDto> getMenuByRole(String roleName);
	
	List<PermissionResponseDto> findAllPermission(Long roleId);

}
