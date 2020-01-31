package com.invicta.lms.dto;

public class PermissionRequestDto {

	private String roleId;
	private boolean checkstatus;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public boolean isCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(boolean checkstatus) {
		this.checkstatus = checkstatus;
	}
	
	
	
}
