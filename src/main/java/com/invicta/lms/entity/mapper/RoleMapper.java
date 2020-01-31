package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.entity.Role;

public class RoleMapper {
	public static RoleDto mapRoleToRoleDto(Role role) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setRoleName(role.getRoleName());
		
		return roleDto;
	}
	public static List<RoleDto> mapRoleListToRoleDtoList(List<Role> roleList){
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		if(roleList != null) {
			for(Role role:roleList) {
				roleDtoList.add(mapRoleToRoleDto(role));	
			}
		}
		return roleDtoList;
		
	}
}