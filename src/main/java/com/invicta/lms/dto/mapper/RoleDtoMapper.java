package com.invicta.lms.dto.mapper;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.entity.Role;

public class RoleDtoMapper {
	public static Role mapRoleDtoToRole(RoleDto roleDto)
	{
		Role role =new Role();
		role.setId(roleDto.getId());
		role.setRoleName(roleDto.getRoleName());
		return role;
	}
}
