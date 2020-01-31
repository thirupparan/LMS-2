package com.invicta.lms.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="profile",schema="leave_system")
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "user")
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@NotBlank
    @Size(max = 40)
	private String firstName;
	
	@NotBlank
    @Size(max = 40)
	private String lastName;
	
	@NotBlank
    @Size(max = 40)
	private String address;
	
	@NotBlank
    @Size(max = 10)
	private Integer telephoneNo;
	
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
