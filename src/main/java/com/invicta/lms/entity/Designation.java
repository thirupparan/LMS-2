package com.invicta.lms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="designation",schema="leave_system")
@Entity
public class Designation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1674627642598594123L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String designation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
