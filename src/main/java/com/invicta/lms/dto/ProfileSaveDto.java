package com.invicta.lms.dto;

public class ProfileSaveDto {
	private Long id;
	private Long user;
	private String firstName;
	private String lastName;
	private String address;
	private Integer telephoneNo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(Integer telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	
}
