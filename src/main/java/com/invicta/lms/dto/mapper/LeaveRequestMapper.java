package com.invicta.lms.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invicta.lms.dto.LeaveDtoRequest;
import com.invicta.lms.dto.LeaveDtoResponse;
import com.invicta.lms.dto.UserInfoDtoResponse;
import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.repository.LeaveTypeRepository;

@Service
public class LeaveRequestMapper {

	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	public LeaveRequest maptDtoToEnity(LeaveDtoRequest leaveDtoRequest, LeaveRequest leaveRequest) {
		leaveRequest.setLeaveType(leaveTypeRepository.findLeaveTypeById(leaveDtoRequest.getLeaveType()));
		leaveRequest.setStartDate(leaveDtoRequest.getStartDate());
		leaveRequest.setEndDate(leaveDtoRequest.getEndDate());
		leaveRequest.setNoOfDays(leaveDtoRequest.getNoOfDays());
		leaveRequest.setReason(leaveDtoRequest.getReason());
		return leaveRequest;
	}

	public LeaveDtoResponse mapEntityToDto(LeaveRequest leaveRequest) {
		LeaveDtoResponse leaveDtoResponse = new LeaveDtoResponse();
		leaveDtoResponse.setId(leaveRequest.getId());
		leaveDtoResponse.setStartDate(leaveRequest.getStartDate());
		leaveDtoResponse.setEndDate(leaveRequest.getEndDate());
		leaveDtoResponse.setNoOfDays(leaveRequest.getNoOfDays());
		leaveDtoResponse.setReason(leaveRequest.getReason());
		leaveDtoResponse.setLeaveStatus(leaveRequest.getLeaveRequestStatus().name());
		leaveDtoResponse.setLeaveType(leaveRequest.getLeaveType().getLeaveTypeName());

		UserInfoDtoResponse userInfoDtoResponse = new UserInfoDtoResponse();
		userInfoDtoResponse.setUserId(leaveRequest.getRequestedUser().getId());
		userInfoDtoResponse.setUserName(leaveRequest.getRequestedUser().getUserName());
		leaveDtoResponse.setRequesteduser(userInfoDtoResponse);

		return leaveDtoResponse;
	}

	public List<LeaveDtoResponse> mapEntityListToDtoList(List<LeaveRequest> leaveRequestList) {
		List<LeaveDtoResponse> leaveDtoResponseList = new ArrayList<LeaveDtoResponse>();
		if (leaveRequestList != null) {
			for (LeaveRequest leaveRequest : leaveRequestList) {
				leaveDtoResponseList.add(mapEntityToDto(leaveRequest));
			}
		}
		return leaveDtoResponseList;
	}

}
