package com.invicta.lms.controller;

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

import com.invicta.lms.dto.LeaveDtoRequest;
import com.invicta.lms.dto.NotificationDtoRequest;
import com.invicta.lms.dto.mapper.NotificationRequestDtoMapper;
import com.invicta.lms.service.LeaveRequestService;
import com.invicta.lms.service.LeaveTypeService;
import com.invicta.lms.service.NotificationService;
import com.invicta.lms.service.UserService;

@RestController
@RequestMapping("leaveRequest")
public class LeaveRequestController {

	@Autowired
	private LeaveRequestService leaveRequestService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private UserService userService;
	@Autowired
	private LeaveTypeService leaveTypeService;

	@PostMapping("/requesteduser/{userid}")
	public ResponseEntity<?> addLeaveRequest(@RequestBody LeaveDtoRequest leaveDtoRequest,
			 @PathVariable("userid") Long userId) {
		NotificationDtoRequest notificationDtoRequest = new NotificationDtoRequest();
		notificationDtoRequest.setMessage((leaveTypeService.findLeaveTypeById(leaveDtoRequest.getLeaveType()).getLeaveTypeName()));
		
		notificationService.addNotification(
				NotificationRequestDtoMapper.mapNotificationRequestDtoToNotification(notificationDtoRequest),
				userService.findUserById(userId));
		return new ResponseEntity<>(leaveRequestService.createInitialLeaveRequestProcess(leaveDtoRequest, userId),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getLeaveRequest() {
		return new ResponseEntity<>(leaveRequestService.findAllLeaveRequest(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLeaveRequestById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(leaveRequestService.findLeaveRequestById(id), HttpStatus.OK);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<?> getLeaveRequestByUser(@PathVariable("userid") Long userId) {
		return new ResponseEntity<>(leaveRequestService.findLeaveRequestByUserId(userId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(leaveRequestService.deleteLeaveRequest(id), HttpStatus.OK);
	}
}
