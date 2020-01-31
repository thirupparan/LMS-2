package com.invicta.lms.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invicta.lms.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission findPermissionById (long id);
	

}
