package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.DesignationDto;
import com.invicta.lms.entity.Designation;

public class DesignationMapper {
	public static DesignationDto mapDesignationToDesignationDto(Designation designation) {
		DesignationDto designationDto = new DesignationDto();
		designationDto.setId(designation.getId());
		designationDto.setDesignation(designation.getDesignation());
		return designationDto;
	}
public static List<DesignationDto>mapDesignationListToDesignationDtoList(List<Designation> designationList){
	List<DesignationDto> designationDtoList = new ArrayList<>();
	if(designationDtoList != null) {
		for(Designation designation:designationList) {
			designationDtoList.add(mapDesignationToDesignationDto(designation));
		}
	}
	return designationDtoList;
}
}
