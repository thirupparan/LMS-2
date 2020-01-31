package com.invicta.lms.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import com.invicta.lms.dto.LeaveManagerDtoRequest;
import com.invicta.lms.dto.LeaveManagerDtoResponse;
import com.invicta.lms.service.LeaveManagerService;


@RestController
@RequestMapping("leavemanager")
public class LeaveManagerController {

	@Autowired
	private LeaveManagerService leaveManagerService;

	@PostMapping("userid/{userid}")
	public ResponseEntity<?> createLeaveDaysProcessor(@RequestBody LeaveManagerDtoRequest leaveDaysProcessorDtoRequest,
			@PathVariable("userid") Long userId) {

		LeaveManagerDtoResponse leaveDaysProcessor = leaveManagerService.addLeaveManager(userId,
				leaveDaysProcessorDtoRequest);

		return new ResponseEntity<>(leaveDaysProcessor, HttpStatus.CREATED);
	}

	@PutMapping("/leavemanagerid/{leavemanagerid}")
	public ResponseEntity<?> updateLeaveDaysProcessor(@RequestBody LeaveManagerDtoRequest leaveDaysProcessorDtoRequest,
			@PathVariable("leavemanagerid") Long leavemanagerId) {

		LeaveManagerDtoResponse leaveDaysProcessor = leaveManagerService.updateLeaveManager(leavemanagerId,
				leaveDaysProcessorDtoRequest);
		return new ResponseEntity<>(leaveDaysProcessor, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<LeaveManagerDtoResponse>> getLeaveDaysProcessor() {
		return new ResponseEntity<>(leaveManagerService.findAllLeaveManager(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLeaveDaysProcessorById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(leaveManagerService.findLeaveManagerById(id), HttpStatus.OK);
	}

	@GetMapping("/findbyUserId/{userId}")
	public ResponseEntity<?> findLeaveDaysByUserId(@PathVariable("userId") Long userId) {
		if (leaveManagerService.findLeaveManagerByUserId(userId) != null) {
			return new ResponseEntity<>(leaveManagerService.findLeaveManagerByUserId(userId), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/findbyuserid/{userid}/leavetsype/{leavetypeid}")
	public ResponseEntity<?> findLeaveDaysByUserIdAndleaveType(@PathVariable("userid") Long userId,
			@PathVariable("leavetypeid") Long leaveTypeId) {

		if (leaveManagerService.findLeaveManagerByUserAndLeaveType(userId, leaveTypeId) != null) {
			return new ResponseEntity<>(leaveManagerService.findLeaveManagerByUserAndLeaveType(userId, leaveTypeId),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

//	@GetMapping("leavesummary/userId/{userId}/leaveType/{leaveTypeId}")
//	public ResponseEntity<?> getleaveSummary(@PathVariable("userId") Long userId,
//			@PathVariable("leaveTypeId") Long leaveTypeId) {
//
//		if (leaveDaysProcessorService.leaveSummary(userId, leaveTypeId) != null) {
//			return new ResponseEntity<>(leaveDaysProcessorService.leaveSummary(userId, leaveTypeId), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(leaveManagerService.deleteLeaveManagerById(id), HttpStatus.OK);
	}
}
