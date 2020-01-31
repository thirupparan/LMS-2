package com.invicta.lms.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invicta.lms.converter.dto.SubMenuDto;

public class SubMenuMapConverter implements AttributeConverter<List<SubMenuDto>, String> {

	private static ObjectMapper mapper;

	static {
		// To avoid instantiating ObjectMapper again and again.
		mapper = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(List<SubMenuDto> attribute) {
		if (null == attribute) {
			// You may return null if you prefer that style
			return "{}";
		}
		try {
			return mapper.writeValueAsString(attribute);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting map to JSON", e);
		}
	}

	@Override
	public List<SubMenuDto> convertToEntityAttribute(String dbData) {
		if (null == dbData) {
			// You may return null if you prefer that style
			return new ArrayList<SubMenuDto>();
		}
		try {
			return mapper.readValue(dbData, new TypeReference<List<SubMenuDto>>() {
			});

		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting JSON to map", e);
		}
	}

}
