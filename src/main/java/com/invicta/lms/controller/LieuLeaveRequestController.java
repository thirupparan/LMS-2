package com.invicta.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.invicta.lms.dto.LieuLeaveDtoRequest;
import com.invicta.lms.dto.LieuLeaveDtoResponse;
import com.invicta.lms.service.LieuLeaveRequestService;
import com.invicta.lms.validation.LieuLeaveRequestValidation;

@RestController
@RequestMapping("/lieuLeaveRequest")
public class LieuLeaveRequestController {

	@Autowired
	private LieuLeaveRequestService lieuleaveRequestService;

	@Autowired
	private LieuLeaveRequestValidation lieuLeaveRequestValidation;

	@GetMapping
	public ResponseEntity<List<LieuLeaveDtoResponse>> getAllLieuLeaveRequest() {

		return new ResponseEntity<>(lieuleaveRequestService.viewAllLieuLeaveRequest(), HttpStatus.OK);

	}

	@PostMapping("/userid/{userid}")
	public ResponseEntity<?> applyLieuLeaveRequest(@RequestBody LieuLeaveDtoRequest lieuLeaveDtoRequest,@PathVariable("userid") Long userId) {
		lieuLeaveRequestValidation.validationLieuLeaveRequest(lieuLeaveDtoRequest);
		if (!lieuLeaveRequestValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(lieuLeaveRequestValidation.getErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(
				lieuleaveRequestService.applyLieuLeave(lieuLeaveDtoRequest, userId),
				HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLieuLeaveRequest(@PathVariable("id") Long id) {
		return new ResponseEntity<>(lieuleaveRequestService.deleteLieuLeaveRequest(id), HttpStatus.OK);

	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getLieuLeaveRequest(@PathVariable Long id) {
		if (lieuleaveRequestService.findLieuLeaveRequestById(id) != null) {
			return new ResponseEntity<>(
					lieuleaveRequestService.findLieuLeaveRequestById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/findbyuserid/{userid}")
	public ResponseEntity<?> getLieuLeaveRequestFindByUserId(@PathVariable("userid") Long userId) {
		if (lieuleaveRequestService.findLieuLeaveRequestByUserId(userId) != null) {
			return new ResponseEntity<>(
					lieuleaveRequestService.findLieuLeaveRequestByUserId(userId), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
}
