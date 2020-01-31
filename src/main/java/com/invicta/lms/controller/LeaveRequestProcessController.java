package com.invicta.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.enums.LeaveRequestAction;
import com.invicta.lms.service.LeaveRequestProcessService;
import com.invicta.lms.service.LeaveRequestService;

@RestController
@RequestMapping("leaveRequestProcess")
public class LeaveRequestProcessController {
	@Autowired
	LeaveRequestProcessService leaveRequestProcessService;
	@Autowired
	LeaveRequestService leaveRequestService;

	@PostMapping("/leaverequest/{leaverequestid}/processuser/{processuserid}")
	public ResponseEntity<?> createLeaveRequestProcess(@RequestBody LeaveProcessDtoRequest leaveProcessDtoRequest,
			@PathVariable("leaverequestid") Long leaveRequestId, @PathVariable("processuserid") Long processUserId) {

		// validate whether the Leave request action had already done
		if (leaveRequestService.validateLeaveRequestWorkflow(leaveRequestId, processUserId)) {
			return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
		}

		if (leaveProcessDtoRequest != null) {
			return new ResponseEntity<>(leaveRequestProcessService.processLeaveRequest(leaveRequestId, processUserId,
					leaveProcessDtoRequest), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{process}")
	public ResponseEntity<?> findLeaveRequestProcess(@PathVariable("process") LeaveRequestAction process) {
		return new ResponseEntity<>(leaveRequestProcessService.findProcessLeaveRequestByProcessType(process),
				HttpStatus.OK);
	}
}
