package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.entity.Designation;
import com.invicta.lms.repository.DesignationRepository;
import com.invicta.lms.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService{

	@Autowired
	DesignationRepository designationRepository;
	
	@Override
	public Designation addDesignation(Designation designation) {
		if(designation != null) {
			return designationRepository.save(designation);
		}
		return null;
	}

	@Override
	public List<Designation> viewAllDesignation() {
		return designationRepository.findAll();
	}

	@Override
	public Long deleteDesignation(Long id) {
		if(designationRepository.getOne(id)!=null) {
			designationRepository.deleteById(id);
			 return id;
			}
			return null;
	}

	@Override
	public Designation updateDesignation(Long id, Designation designation) {
		if(designationRepository.getOne(id)!= null) {
			designation.setId(id);
			return designationRepository.save(designation);
		}
		return null;
	}

	@Override
	public Designation findDesignationById(Long id) {
		if(designationRepository.getOne(id)!= null) {
			return designationRepository.findDesignationById(id);
		}
		return null;
	}

	@Override
	public Boolean existsByDesignation(String designation) {
		return designationRepository.existsByDesignation(designation);
	}
	

}
