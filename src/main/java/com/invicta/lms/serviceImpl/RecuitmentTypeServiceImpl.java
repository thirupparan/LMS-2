package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invicta.lms.entity.RecuitmentType;
import com.invicta.lms.repository.RecuitmentTypeRepository;
import com.invicta.lms.service.RecuitmentTypeService;

@Service
public class RecuitmentTypeServiceImpl implements RecuitmentTypeService {
	@Autowired
	RecuitmentTypeRepository recuitmentTypeRepository;

	@Override
	public RecuitmentType addRecuitmentType(RecuitmentType recuitmentType) {
		if (recuitmentType != null) {
			return recuitmentTypeRepository.save(recuitmentType);
		}
		return null;
	}

	@Override
	public List<RecuitmentType> viewAllRecuitmentType() {
		return recuitmentTypeRepository.findAll();
	}

	@Override
	public Long deleteRecuitmentType(Long id) {
		if (recuitmentTypeRepository.getOne(id) != null) {
			recuitmentTypeRepository.deleteById(id);
			return id;
		}
		return null;
	}

	@Override
	public RecuitmentType updateRecuitmentType(Long id, RecuitmentType recuitmentType) {
		recuitmentType.setId(id);
		return recuitmentTypeRepository.save(recuitmentType);
	}

	@Override
	public RecuitmentType findRecuitmentTypeById(Long id) {
		return recuitmentTypeRepository.findRecuitmentTypeById(id);

	}

	@Override
	public Boolean existsByRecuitmentType(String recuitmentType) {
		return recuitmentTypeRepository.existsByRecuitmentTypeName(recuitmentType);
	}

}
