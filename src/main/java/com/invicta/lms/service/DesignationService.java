package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.Designation;

public interface DesignationService {
	Designation addDesignation(Designation designation);

	List<Designation> viewAllDesignation();

	Long deleteDesignation(Long id);

	Designation updateDesignation(Long id, Designation designation);

	Designation findDesignationById(Long id);

	Boolean existsByDesignation(String designation);

}
