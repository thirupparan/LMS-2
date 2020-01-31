package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.invicta.lms.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
	LeaveType findLeaveTypeById(Long id);
	
	 Boolean existsByleaveTypeName(String leaveType);
	 
	@Query("SELECT count(leaveTypeName) FROM LeaveType l WHERE l.id <> ?1 AND l.leaveTypeName= ?2")
	Integer checkWithLeaveTypeLockId(Long id,String leaveTypeName);
		
}
