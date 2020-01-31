package com.invicta.lms.dto;

import java.util.List;

public class LeaveSummaryResponseDto {
	private String leaveType;
	private Double remainingDays;
	private List<LeaveManagerTypeDto> leaveManagerType;
	
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Double getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(Double remainingDays) {
		this.remainingDays = remainingDays;
	}
	public List<LeaveManagerTypeDto> getLeaveManagerType() {
		return leaveManagerType;
	}
	public void setLeaveManagerType(List<LeaveManagerTypeDto> leaveManagerType) {
		this.leaveManagerType = leaveManagerType;
	}
	

	
	
}
