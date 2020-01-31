package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.RecuitmentType;

public interface RecuitmentTypeRepository extends JpaRepository<RecuitmentType, Long> {
	RecuitmentType findRecuitmentTypeById(Long id);
	Boolean existsByRecuitmentTypeName(String recuitmentTypeName);

}
