package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.RecuitmentType;

public interface RecuitmentTypeService {
	RecuitmentType addRecuitmentType(RecuitmentType recuitmentType);
	List<RecuitmentType> viewAllRecuitmentType();
	Long deleteRecuitmentType(Long id);
	RecuitmentType updateRecuitmentType(Long id,RecuitmentType recuitmentType);
	RecuitmentType findRecuitmentTypeById(Long id);
	Boolean existsByRecuitmentType(String recuitmentType);
}
