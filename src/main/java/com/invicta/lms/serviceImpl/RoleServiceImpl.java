package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.entity.Role;
import com.invicta.lms.repository.RoleRepository;
import com.invicta.lms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		if (role != null) {
			return roleRepository.save(role);
		}
		return null;
	}

	@Override
	public List<Role> viewAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public Long deleteRole(Long id) {
		if (roleRepository.getOne(id) != null) {
			roleRepository.deleteById(id);
			return id;
		}
		return null;
	}

	@Override
	public Role updateRole(Long id, Role role) {

		role.setId(id);
		return roleRepository.save(role);

	}

	@Override
	public Role findRoleById(Long id) {

		return roleRepository.findRoleById(id);

	}

	@Override
	public Boolean existsByRole(String roleName) {
		return roleRepository.existsByRoleName(roleName);
	}

	@Override
	public Boolean existsByRoleLockId(Long id, String roleName) {
		if(roleRepository.checkWithLockRoleId(id, roleName)>0) {
			return true;
		}
		return false;
	}

}
