package com.invicta.lms.converter.dto;

import java.util.List;

import com.invicta.lms.enums.LeaveRequestAction;

public class LeaveRequestWorkFlow {

	private Long userId;
	private List<LeaveRequestAction> leaveRequestActions;
	private boolean isComplete;
	
	public LeaveRequestWorkFlow(Long userId, List<LeaveRequestAction> leaveRequestActions, boolean isComplete) {
		super();
		this.userId = userId;
		this.leaveRequestActions = leaveRequestActions;
		this.isComplete = isComplete;
	}
	
	public LeaveRequestWorkFlow() {
		
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<LeaveRequestAction> getLeaveRequestActions() {
		return leaveRequestActions;
	}
	public void setLeaveRequestActions(List<LeaveRequestAction> leaveRequestActions) {
		this.leaveRequestActions = leaveRequestActions;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
}
