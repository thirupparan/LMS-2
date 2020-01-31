package com.invicta.lms.dto.mapper;

import com.invicta.lms.dto.RecruitmentTypeDto;
import com.invicta.lms.entity.RecuitmentType;

public class RecuitmentTypeDtoMapper {

	public static RecuitmentType mapRecuitmentTypeDtoToRecuitmentType(RecruitmentTypeDto recruitmentTypeDto) {
		RecuitmentType recuitmentType = new RecuitmentType();
		recuitmentType.setId(recruitmentTypeDto.getId());
		recuitmentType.setRecuitmentTypeName(recruitmentTypeDto.getRecuitmentTypeName());
		return recuitmentType;
	}
}
