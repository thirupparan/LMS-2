package com.invicta.lms.dto;

import com.invicta.lms.enums.LeaveProcessType;

public class LeaveManagerTypeDto {
	private LeaveProcessType leaveProcessType;
	private Double days;
	
	public LeaveProcessType getLeaveProcessType() {
		return leaveProcessType;
	}
	public void setLeaveProcessType(LeaveProcessType leaveProcessType) {
		this.leaveProcessType = leaveProcessType;
	}
	public Double getDays() {
		return days;
	}
	public void setDays(Double days) {
		this.days = days;
	}
	
	
}
