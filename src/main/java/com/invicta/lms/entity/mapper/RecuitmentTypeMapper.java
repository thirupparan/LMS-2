package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.RecruitmentTypeDto;
import com.invicta.lms.entity.RecuitmentType;

public class RecuitmentTypeMapper {
	public static RecruitmentTypeDto mapRecuitmentTypeToRecuitmentTypeDto(RecuitmentType recuitmentType) {
		RecruitmentTypeDto recruitmentTypeDto = new RecruitmentTypeDto();
		recruitmentTypeDto.setId(recuitmentType.getId());
		recruitmentTypeDto.setRecuitmentTypeName(recuitmentType.getRecuitmentTypeName());

		return recruitmentTypeDto;
	}

	public static List<RecruitmentTypeDto> mapRecuitmentTypeListToRecuitmentTypeDtoList(
			List<RecuitmentType> recuitmentTypeList) {
		List<RecruitmentTypeDto> recuitmentTypeDtoList = new ArrayList<RecruitmentTypeDto>();
		if (recuitmentTypeList != null) {
			for (RecuitmentType recuitmentType : recuitmentTypeList) {
				recuitmentTypeDtoList.add(mapRecuitmentTypeToRecuitmentTypeDto(recuitmentType));
			}
		}
		return recuitmentTypeDtoList;

	}
}
