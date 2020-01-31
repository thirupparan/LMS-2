package com.invicta.lms.dto;

public class NotificationDtoRequest {

	private Long user;
	private String message;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
