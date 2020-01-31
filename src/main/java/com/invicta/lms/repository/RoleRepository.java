package com.invicta.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.invicta.lms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findRoleById(Long id);
	Optional<Role>findByRoleName(String roleName);
	Boolean existsByRoleName(String roleName);
	
	@Query("SELECT count(roleName) FROM Role r WHERE r.id <> ?1 AND r.roleName= ?2")
	Integer checkWithLockRoleId(Long id,String roleName);
	
}
