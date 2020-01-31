package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LeaveDtoRequest;
@Service
public class LeaveRequestValidation {
	Map<String, String> errors = new HashMap<>();

	public void validationLeave(LeaveDtoRequest leaveDtoRequest) {
		errors.clear();
		if (leaveDtoRequest.getReason() == "") {
			errors.put("leaveError", "Reason cannot be Empty");
		}
		if (leaveDtoRequest.getLeaveType() == null) {
			errors.put("leaveError", "Leavetype is required");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
}
