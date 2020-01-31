package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long>{
	Designation findDesignationById(Long id);

	Boolean existsByDesignation(String designation);
}
