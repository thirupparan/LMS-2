package com.invicta.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.MenuResponseDto;
import com.invicta.lms.dto.PermissionResponseDto;
import com.invicta.lms.dto.mapper.PermissionMapper;
import com.invicta.lms.entity.Permission;
import com.invicta.lms.repository.PermissionRepository;
import com.invicta.lms.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<MenuResponseDto> getMenuByRole(String roleId) {
		List<Permission> permissionList = permissionRepository.findAll();
		List<MenuResponseDto> newPermissionList = new ArrayList<>();
		for (Permission permissionObj : permissionList) {
			if (permissionObj.getPermission().contains(roleId)) {
				MenuResponseDto menuResponseDto = new MenuResponseDto();
				menuResponseDto.setMenuName(permissionObj.getMenuName());
				menuResponseDto.setMenuLink(permissionObj.getMenuLink());
				menuResponseDto.setIcon(permissionObj.getIcon());
				menuResponseDto.setSubMenu(permissionObj.getSubMenu());
				newPermissionList.add(menuResponseDto);
			}
		}
		return newPermissionList;
	}

	@Override
	public List<PermissionResponseDto> findAllPermission(Long roleId) {
		// TODO Auto-generated method stub
		return permissionMapper.mapEntityToDtoList(permissionRepository.findAll(),roleId);
	}
}
