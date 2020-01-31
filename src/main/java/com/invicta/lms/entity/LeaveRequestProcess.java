package com.invicta.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.invicta.lms.entity.audit.DateAudit;
import com.invicta.lms.enums.LeaveRequestAction;

@Entity
@Table(name="Leave_request_process",schema="leave_system")
public class LeaveRequestProcess extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private LeaveRequestAction leaveRequestAction;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "leaveRequest_id")
	private LeaveRequest leaveRequest;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "process_by")
	private User processUser;
	
	@JoinColumn(name="leave_manager_id")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private LeaveManager leaveManager;
	
	private String reason;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public LeaveRequestAction getLeaveRequestAction() {
		return leaveRequestAction;
	}
	public void setLeaveRequestAction(LeaveRequestAction leaveRequestAction) {
		this.leaveRequestAction = leaveRequestAction;
	}
	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}
	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	public User getProcessUser() {
		return processUser;
	}
	public void setProcessUser(User processUser) {
		this.processUser = processUser;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LeaveManager getLeaveManager() {
		return leaveManager;
	}
	public void setLeaveManager(LeaveManager leaveManager) {
		this.leaveManager = leaveManager;
	}
	
}
