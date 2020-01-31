package com.invicta.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.invicta.lms.converter.LeaveRequestWorkFlowListConvertor;
import com.invicta.lms.converter.dto.LeaveRequestWorkFlow;
import com.invicta.lms.entity.audit.DateAudit;
import com.invicta.lms.enums.LeaveRequestStatus;

@Entity
@Table(name="leave_request",schema="leave_system")
public class LeaveRequest extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3759420037566938271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable =false)
	private User requestedUser;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="leave_type_id", nullable =false)
	private LeaveType leaveType;
	
	private Date startDate;
	private Date endDate;
	private Double noOfDays;
	private String reason;
//	private Date requestedDate;
	
	@Column(name = "leave_request_status")
	private LeaveRequestStatus leaveRequestStatus;
	
	@Convert(converter = LeaveRequestWorkFlowListConvertor.class)
	private List<LeaveRequestWorkFlow> leaveRequestWorkFlows;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public User getRequestedUser() {
		return requestedUser;
	}
	public void setRequestedUser(User requestedUser) {
		this.requestedUser = requestedUser;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public LeaveRequestStatus getLeaveRequestStatus() {
		return leaveRequestStatus;
	}
	public void setLeaveRequestStatus(LeaveRequestStatus leaveRequestStatus) {
		this.leaveRequestStatus = leaveRequestStatus;
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
	public List<LeaveRequestWorkFlow> getLeaveRequestWorkFlows() {
		return leaveRequestWorkFlows;
	}
	public void setLeaveRequestWorkFlows(List<LeaveRequestWorkFlow> leaveRequestWorkFlows) {
		this.leaveRequestWorkFlows = leaveRequestWorkFlows;
	}
	
//	public Date getRequestedDate() {
//		return requestedDate;
//	}
//	public void setRequestedDate(Date requestedDate) {
//		this.requestedDate = requestedDate;
//	}	

}
