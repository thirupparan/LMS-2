package com.invicta.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "recuitmentType",schema="leave_system")
public class RecuitmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 30)
	@Column(unique = true)
	private String recuitmentTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecuitmentTypeName() {
		return recuitmentTypeName;
	}

	public void setRecuitmentTypeName(String recuitmentTypeName) {
		this.recuitmentTypeName = recuitmentTypeName;
	}

	

}
