package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.dto.LeaveDtoRequest;
import com.invicta.lms.dto.LeaveDtoResponse;
import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.enums.LeaveRequestAction;

public interface LeaveRequestService {
	
	Boolean createInitialLeaveRequestProcess(LeaveDtoRequest leaveDtoRequest,Long requestUserId);
	List<LeaveDtoResponse> findAllLeaveRequest();
	List<LeaveDtoResponse> findLeaveRequestByUserId(Long userId);
	LeaveDtoResponse findLeaveRequestById(Long id);
	Long deleteLeaveRequest(Long id);
	LeaveRequest modifyLeaveRequestWorkflow(LeaveRequest leaveRequest, Long requestUserId,LeaveRequestAction action);
	Boolean validateLeaveRequestWorkflow(Long leaveRequestId, Long requestUserId);
	
	
}
