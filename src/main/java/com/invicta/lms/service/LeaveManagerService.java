package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.dto.LeaveManagerDtoRequest;
import com.invicta.lms.dto.LeaveManagerDtoResponse;
import com.invicta.lms.dto.LeaveSummaryResponseDto;


public interface LeaveManagerService {
	
	LeaveManagerDtoResponse addLeaveManager(Long userid,LeaveManagerDtoRequest leaveManagerDtoRequest);
	List<LeaveManagerDtoResponse> findAllLeaveManager();
	LeaveManagerDtoResponse updateLeaveManager(Long id,LeaveManagerDtoRequest leaveManagerDtoRequest);
	LeaveManagerDtoResponse findLeaveManagerById(Long id);
	Long deleteLeaveManagerById(Long id);
	// list all the leaves days for particular user 
	List<LeaveManagerDtoResponse> findLeaveManagerByUserId(Long userId);
	// list all the leaves days for particular user by leave days and leave type
	List<LeaveManagerDtoResponse> findLeaveManagerByUserAndLeaveType(Long userId,Long leaveTypeId);
	// list all the leaves days for particular user 
	LeaveSummaryResponseDto leaveSummaryResponseDto(Long userId,Long leaveTypeId);


}
