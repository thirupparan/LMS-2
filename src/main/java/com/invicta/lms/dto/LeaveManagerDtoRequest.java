package com.invicta.lms.dto;

import java.sql.Date;

import com.invicta.lms.enums.LeaveProcessType;

public class LeaveManagerDtoRequest {

	private Long leaveTypeId; 
	private LeaveProcessType leaveProcessType;
	private Double Days;
	private Date DateOfProcess;

	public Long getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public LeaveProcessType getLeaveProcessType() {
		return leaveProcessType;
	}

	public void setLeaveProcessType(LeaveProcessType leaveProcessType) {
		this.leaveProcessType = leaveProcessType;
	}

	public Double getDays() {
		return Days;
	}

	public void setDays(Double days) {
		Days = days;
	}

	public Date getDateOfProcess() {
		return DateOfProcess;
	}

	public void setDateOfProcess(Date dateOfProcess) {
		DateOfProcess = dateOfProcess;
	}

}
