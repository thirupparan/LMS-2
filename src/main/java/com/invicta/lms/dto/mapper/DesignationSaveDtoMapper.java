package com.invicta.lms.dto.mapper;

import com.invicta.lms.dto.DesignationDto;
import com.invicta.lms.entity.Designation;

public class DesignationSaveDtoMapper {
public static Designation mapDesignationSaveDtoToDesignation(DesignationDto designationDto) {
	Designation designation = new Designation();
	designation.setId(designationDto.getId());
	designation.setDesignation(designationDto.getDesignation());
	return designation;
}
}
