package com.invicta.lms.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invicta.lms.dto.PermissionResponseDto;
import com.invicta.lms.entity.Permission;

@Service
public class PermissionMapper {

	public Permission mapDtoToEntity(PermissionResponseDto permissionResponseDto, Permission permission) {
		permission.setId(permissionResponseDto.getPermissionId());
		permission.setMenuName(permissionResponseDto.getMenuName());
		return permission;
	}

	public PermissionResponseDto mapEntityToDto(Permission permission,Long roleId) {
		PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
		permissionResponseDto.setPermissionId(permission.getId());
		permissionResponseDto.setMenuName(permission.getMenuName());
		
		if(permission.getPermission().contains(roleId.toString())) {
			permissionResponseDto.setChecked(true);
		}else {
			permissionResponseDto.setChecked(false);
		}

		return permissionResponseDto;

	}

	public List<PermissionResponseDto> mapEntityToDtoList(List<Permission> permissionList,Long roleId) {
		List<PermissionResponseDto> permissionDtoList = new ArrayList<PermissionResponseDto>();
		if (permissionList != null) {
			for (Permission permission : permissionList) {
				permissionDtoList.add(mapEntityToDto(permission,roleId));
			}
		}
		return permissionDtoList;
	}

}
