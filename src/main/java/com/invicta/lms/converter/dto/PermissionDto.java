package com.invicta.lms.converter.dto;

public class PermissionDto {

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public PermissionDto(String role) {
		this.role = role;
	}

	public PermissionDto() {

	}

}
