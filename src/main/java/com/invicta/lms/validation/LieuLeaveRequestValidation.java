package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LieuLeaveDtoRequest;

@Service
public class LieuLeaveRequestValidation {
	Map<String, String> errors = new HashMap<>();

	public void validationLieuLeaveRequest(LieuLeaveDtoRequest lieuLeaveDtoRequest) {
		errors.clear();

		if (lieuLeaveDtoRequest.getReason() == "") {
			errors.put("reason", "Reason Cannot Be Empty");
		}
		if (lieuLeaveDtoRequest.getReason() == null) {
			errors.put("reason", "Reason is Required");
		}
		if (lieuLeaveDtoRequest.getWorkedHours() == null) {
			errors.put("lieuLeaveRequest", "Worke hours is Required");
		}
//		if(lieuLeaveDtoRequest.getWorkedHours()>0) {
//			errors.put("lieuLeaveRequest", "Worke hours Cannot Be less than zero");
//		}
		if (lieuLeaveDtoRequest.getStartDate() == null) {
			errors.put("startDate", "Start date is Required");
		}
		if (lieuLeaveDtoRequest.getEndDate() == null) {
			errors.put("endDate", "End date is Required");
		}

	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
