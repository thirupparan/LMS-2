package com.invicta.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.invicta.lms.dto.RecruitmentTypeDto;
import com.invicta.lms.dto.mapper.RecuitmentTypeDtoMapper;
import com.invicta.lms.entity.RecuitmentType;
import com.invicta.lms.entity.mapper.RecuitmentTypeMapper;
import com.invicta.lms.service.RecuitmentTypeService;
import com.invicta.lms.validation.RecruitmentTypeValidation;

@RestController
@RequestMapping("/recuitmentType")
public class RecuitmentTypeController {

	@Autowired
	RecuitmentTypeService recuitmentTypeService;
	
	@Autowired
	RecruitmentTypeValidation recruitmentTypeValidation;

	@GetMapping
	public ResponseEntity<List<RecruitmentTypeDto>> getRecuitmentType() {
		return new ResponseEntity<>(RecuitmentTypeMapper.mapRecuitmentTypeListToRecuitmentTypeDtoList(
				recuitmentTypeService.viewAllRecuitmentType()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRecuitmentTypeById(@PathVariable("id") Long id) {
		if (recuitmentTypeService.findRecuitmentTypeById(id) != null) {
			return new ResponseEntity<>(RecuitmentTypeMapper.mapRecuitmentTypeToRecuitmentTypeDto(
					recuitmentTypeService.findRecuitmentTypeById(id)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<?> addRecuitmentType(@RequestBody RecruitmentTypeDto recruitmentTypeDto) {
	
		recruitmentTypeValidation.validateRecruitmentType(recruitmentTypeDto);
		 if(!recruitmentTypeValidation.getErrors().isEmpty()) {
			 return new ResponseEntity<>(recruitmentTypeValidation.getErrors(),HttpStatus.BAD_REQUEST);
		 }
		
		
		return new ResponseEntity<>(
				RecuitmentTypeMapper.mapRecuitmentTypeToRecuitmentTypeDto(recuitmentTypeService.addRecuitmentType(
						RecuitmentTypeDtoMapper.mapRecuitmentTypeDtoToRecuitmentType(recruitmentTypeDto))),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRecuitmentType(@RequestBody RecruitmentTypeDto recruitmentTypeDto,
			@PathVariable Long id) {
		recruitmentTypeValidation.validateRecruitmentType(recruitmentTypeDto);
		if (!recruitmentTypeValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(recruitmentTypeValidation.getErrors(),HttpStatus.BAD_REQUEST);	
		}
		return new ResponseEntity<RecuitmentType>(
				recuitmentTypeService.updateRecuitmentType(id,
						RecuitmentTypeDtoMapper.mapRecuitmentTypeDtoToRecuitmentType(recruitmentTypeDto)),
				HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRecuitmentType(@PathVariable Long id) {
		return new ResponseEntity<>(recuitmentTypeService.deleteRecuitmentType(id), HttpStatus.OK);
	}
	
	@GetMapping("/checkAvailability")
	public ResponseEntity<?> recuitmentTypeAvailability(@RequestParam(value = "recuitmentType") String recuitmentType) {
		Map<String, String> errors = new HashMap<>();

		errors.clear();
		if (recuitmentTypeService.existsByRecuitmentType(recuitmentType)) {
			errors.put("RecuitmentType", "Recuitment Type Already exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}

		errors.put("RecuitmentType", "Not exist");

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(errors);

	}
}
