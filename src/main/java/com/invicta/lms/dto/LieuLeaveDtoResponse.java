package com.invicta.lms.dto;

import java.time.Instant;
import java.util.Date;

public class LieuLeaveDtoResponse {
	private Long id;
	private UserInfoDtoResponse userInfo;
	private Date startDate;
	private Date endDate;
	private Double workedHours;
	private String reason;
	private Instant appliedOn;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfoDtoResponse getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDtoResponse userInfo) {
		this.userInfo = userInfo;
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

	public Double getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(Double workedHours) {
		this.workedHours = workedHours;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Instant getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Instant appliedOn) {
		this.appliedOn = appliedOn;
	}

	
	
}
