package com.invicta.lms.dto;

import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.enums.LeaveRequestAction;

public class LeaveProcessDtoResponse {

	private Long id;
	private LeaveRequest leaveRequest;
	private LeaveRequestAction leaveRequestAction;
	private String reason;
	private UserInfoDtoResponse userInfo;
	private LeaveManagerDtoResponse leaveManager;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}
	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	public LeaveManagerDtoResponse getLeaveManager() {
		return leaveManager;
	}
	public void setLeaveManager(LeaveManagerDtoResponse leaveManager) {
		this.leaveManager = leaveManager;
	}
	public LeaveRequestAction getLeaveRequestAction() {
		return leaveRequestAction;
	}
	public void setLeaveRequestAction(LeaveRequestAction leaveRequestAction) {
		this.leaveRequestAction = leaveRequestAction;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public UserInfoDtoResponse getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoDtoResponse userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
