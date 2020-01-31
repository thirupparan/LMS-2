package com.invicta.lms.dto;

import java.util.Date;

public class LeaveDtoResponse {
	private Long id;
	private UserInfoDtoResponse requesteduser;
	private String leaveType;
	private Date startDate;
	private Date endDate;
	private Double noOfDays;
	private String reason;
	private String leaveStatus;
	private Date requestedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserInfoDtoResponse getRequesteduser() {
		return requesteduser;
	}
	public void setRequesteduser(UserInfoDtoResponse requesteduser) {
		this.requesteduser = requesteduser;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Double noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	
	
}
