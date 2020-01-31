package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.dto.LeaveProcessDtoResponse;
import com.invicta.lms.enums.LeaveRequestAction;


public interface LeaveRequestProcessService {

	LeaveProcessDtoResponse processLeaveRequest(Long leaveRequestId,Long processUserId,LeaveProcessDtoRequest leaveProcessDtoRequest);
	List<LeaveProcessDtoResponse> findProcessLeaveRequestByProcessType(LeaveRequestAction leaveRequestAction);
}
