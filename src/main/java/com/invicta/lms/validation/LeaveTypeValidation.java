package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LeaveTypeDto;
import com.invicta.lms.service.LeaveTypeService;

@Service
public class LeaveTypeValidation {
	@Autowired
	LeaveTypeService leaveTypeService;
	
	Map<String,String> errors = new HashMap<>();
	public void validationLeaveType(LeaveTypeDto leaveTypeDto) {
		errors.clear();
		if(leaveTypeDto.getLeaveTypeName() ==null) {
			errors.put("leaveTypeName", "Leave type cannot be null");
		}
		if(leaveTypeDto.getLeaveTypeName() == "") {
			errors.put("leaveTypeName", "Leave type cannot be Empty");
		}
		if(leaveTypeDto.getId()==null && leaveTypeService.existsByleaveType(leaveTypeDto.getLeaveTypeName())) {
			errors.put("leaveTypeName", "Leave type Already exist");
		}
		if(leaveTypeDto.getId()!=null && leaveTypeService.existsByleaveTypeLockId(leaveTypeDto.getId(),leaveTypeDto.getLeaveTypeName())) {
			errors.put("leaveTypeName", "Leave type Already exist");
		}
	}
	public Map<String,String> getErrors(){
		return errors;
	}

}
