package com.invicta.lms.entity;

import javax.persistence.Column;
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
import com.invicta.lms.enums.LeaveProcessType;

@Entity
@Table(name="leave_days_processor",schema="leave_system")
public class LeaveManager extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5369083158146933228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable =false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="leave_type_id", nullable =false)
	private LeaveType leaveType;
	
	@Enumerated(EnumType.STRING)
	@Column (name="leaveProcessType")
	private LeaveProcessType leaveProcessType;
	
	private Double days;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
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
