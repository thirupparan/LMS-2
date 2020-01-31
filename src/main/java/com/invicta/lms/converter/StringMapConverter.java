package com.invicta.lms.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringMapConverter implements AttributeConverter<Map<String, String>, String> {
	private static ObjectMapper mapper;

	static {
		// To avoid instantiating ObjectMapper again and again.
		mapper = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(Map<String, String> data) {
		if (null == data) {
			// You may return null if you prefer that style
			return "{}";
		}
		try {
			return mapper.writeValueAsString(data);

		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting map to JSON", e);
		}
	}

	@Override
	public Map<String, String> convertToEntityAttribute(String dbData) {
		if (null == dbData) {
			// You may return null if you prefer that style
			return new HashMap<>();
		}
		try {
			return mapper.readValue(dbData, new TypeReference<Map<String, String>>() {
			});

		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting JSON to map", e);
		}
	}

}
