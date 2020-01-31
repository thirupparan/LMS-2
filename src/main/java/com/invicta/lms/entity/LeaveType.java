package com.invicta.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(schema="leave_system", name="leaveType")
public class LeaveType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3583633575130527535L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated product ID")
	private Long id;
	
	 @NotBlank(message ="Leave type is requried")
	 @Size(max = 40)
	 @Column(unique = true)
	 @ApiModelProperty(notes = "Leave type name")
	private String leaveTypeName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	
}
