package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.LeaveType;

public interface LeaveTypeService {
	LeaveType addLeaveType(LeaveType leaveType);
	List<LeaveType> viewAllLeaveType();
	Long deleteLeaveType(Long id);
	LeaveType updateLeaveType(Long id,LeaveType leaveType);
	LeaveType findLeaveTypeById(Long id);
	Boolean existsByleaveType(String leaveType);
	Boolean existsByleaveTypeLockId(Long id,String leaveTypeName);
}
