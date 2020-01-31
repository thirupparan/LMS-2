package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.Role;

public interface RoleService {
	Role addRole(Role role);
	List<Role> viewAllRole();
	Long deleteRole(Long id);
	Role updateRole(Long id,Role role);
	Role findRoleById(Long id);
	Boolean existsByRole(String roleName);
	Boolean existsByRoleLockId(Long id,String roleName);
}
