package com.invicta.lms.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PermissionMapConverter implements AttributeConverter<List<String>, String> {
	private static ObjectMapper mapper;

	  static {
	    // To avoid instantiating ObjectMapper again and again.
	    mapper = new ObjectMapper();
	  }

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
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
	public List<String> convertToEntityAttribute(String dbData) {
		if (null == dbData) {
			// You may return null if you prefer that style
			return new ArrayList<String>();
		}
		try {
			return mapper.readValue(dbData, new TypeReference<List<String>>() {
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting JSON to map", e);
		}
	}
	
}
