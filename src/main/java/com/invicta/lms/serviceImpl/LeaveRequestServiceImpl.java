package com.invicta.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.converter.dto.LeaveRequestWorkFlow;
import com.invicta.lms.dto.LeaveDtoRequest;
import com.invicta.lms.dto.LeaveDtoResponse;
import com.invicta.lms.dto.LeaveManagerDtoRequest;
import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.dto.mapper.LeaveRequestMapper;
import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.enums.LeaveProcessType;
import com.invicta.lms.enums.LeaveRequestAction;
import com.invicta.lms.enums.LeaveRequestStatus;
import com.invicta.lms.repository.LeaveRequestRepository;
import com.invicta.lms.repository.UserRepository;
import com.invicta.lms.service.LeaveManagerService;
import com.invicta.lms.service.LeaveRequestProcessService;
import com.invicta.lms.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	@Autowired
	LeaveRequestMapper leaveRequestMapper;

	@Autowired
	UserRepository userRepository;
	@Autowired
	LeaveRequestProcessService leaveRequestProcessService;
	@Autowired
	LeaveManagerService leaveManagerService;

	private LeaveRequest addLeaveRequest(LeaveDtoRequest leaveDtoRequest, Long requestUserId) {
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest = leaveRequestMapper.maptDtoToEnity(leaveDtoRequest, leaveRequest);

		if (leaveRequest != null) {
			leaveRequest.setLeaveRequestStatus(LeaveRequestStatus.PENDING);
			leaveRequest.setRequestedUser(userRepository.findUserById(requestUserId));

			// Set Work flow for the user
			List<LeaveRequestWorkFlow> leaveRequestWorkFlows = new ArrayList<>();
			List<LeaveRequestAction> leaveRequestActiont1 = new ArrayList<>();
			leaveRequestActiont1.add(LeaveRequestAction.APPLIED);
			List<LeaveRequestAction> leaveRequestActiont2 = new ArrayList<>();
			leaveRequestActiont2.add(LeaveRequestAction.ACCEPTED);
			leaveRequestActiont2.add(LeaveRequestAction.REJECTED);
			leaveRequestWorkFlows.add(new LeaveRequestWorkFlow(requestUserId, leaveRequestActiont1, false));
			leaveRequestWorkFlows.add(new LeaveRequestWorkFlow(1L, leaveRequestActiont2, false));
			leaveRequestWorkFlows.add(new LeaveRequestWorkFlow(3L, leaveRequestActiont2, false));

			leaveRequest.setLeaveRequestWorkFlows(leaveRequestWorkFlows);
			return leaveRequestRepository.save(leaveRequest);

		}
		return null;
	}

	@Override
	public List<LeaveDtoResponse> findAllLeaveRequest() {
		return leaveRequestMapper.mapEntityListToDtoList(leaveRequestRepository.findAll());
	}

	@Override
	public Long deleteLeaveRequest(Long id) {
		if (leaveRequestRepository.getOne(id) != null) {
			leaveRequestRepository.deleteById(id);
			return id;
		}
		return null;
	}

	@Override
	public LeaveDtoResponse findLeaveRequestById(Long id) {
		if (leaveRequestRepository.getOne(id) != null) {
			return leaveRequestMapper.mapEntityToDto(leaveRequestRepository.findLeaveRequestById(id));
		}
		return null;
	}

	@Override
	public List<LeaveDtoResponse> findLeaveRequestByUserId(Long userId) {
		if (userId != null) {
			return leaveRequestMapper.mapEntityListToDtoList(
					leaveRequestRepository.findByrequestedUser(userRepository.findUserById(userId)));
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean createInitialLeaveRequestProcess(LeaveDtoRequest leaveDtoRequest, Long requestUserId) {
		LeaveRequest leaveRequest = this.addLeaveRequest(leaveDtoRequest, requestUserId);

		LeaveManagerDtoRequest leaveManagerDtoRequest = new LeaveManagerDtoRequest();
		leaveManagerDtoRequest.setLeaveProcessType(LeaveProcessType.UTILIZED);
		leaveManagerDtoRequest.setLeaveTypeId(leaveDtoRequest.getLeaveType());

		Double numOfDays = (-1) * leaveDtoRequest.getNoOfDays();
		leaveManagerDtoRequest.setDays(numOfDays);

		LeaveProcessDtoRequest leaveProcessDtoRequest = new LeaveProcessDtoRequest(LeaveRequestAction.APPLIED,
				"Leave Request applied");
		leaveProcessDtoRequest
				.setLeaveManager(leaveManagerService.addLeaveManager(requestUserId, leaveManagerDtoRequest).getId());

		if (leaveRequestProcessService.processLeaveRequest(leaveRequest.getId(), requestUserId,
				leaveProcessDtoRequest) != null) {
			return true;
		}

		return false;
	}

	@Override
	public LeaveRequest modifyLeaveRequestWorkflow(LeaveRequest leaveRequest, Long requestUserId,
			LeaveRequestAction action) {
		List<LeaveRequestWorkFlow> lvreqtwfs = leaveRequest.getLeaveRequestWorkFlows();

		for (LeaveRequestWorkFlow leaveRequestWorkFlow : lvreqtwfs) {
			if (action == LeaveRequestAction.APPLIED) {
				if (leaveRequestWorkFlow.getUserId().equals(requestUserId)
						&& leaveRequestWorkFlow.getLeaveRequestActions().contains(LeaveRequestAction.APPLIED)) {
					leaveRequestWorkFlow.setComplete(true);
				}
			} else {
				if (leaveRequestWorkFlow.getUserId().equals(requestUserId) && (leaveRequestWorkFlow
						.getLeaveRequestActions().contains(LeaveRequestAction.ACCEPTED)
						|| leaveRequestWorkFlow.getLeaveRequestActions().contains(LeaveRequestAction.REJECTED))) {
					leaveRequestWorkFlow.setComplete(true);
				}
			}
		}
		leaveRequest.setLeaveRequestWorkFlows(lvreqtwfs);
		return leaveRequestRepository.save(leaveRequest);
	}

	@Override
	public Boolean validateLeaveRequestWorkflow(Long leaveRequestId, Long requestUserId) {
		LeaveRequest leaveRequest = leaveRequestRepository.findLeaveRequestById(leaveRequestId);
		List<LeaveRequestWorkFlow> lvreqtwfs = leaveRequest.getLeaveRequestWorkFlows();

		for (LeaveRequestWorkFlow leaveRequestWorkFlow : lvreqtwfs) {
			if (leaveRequestWorkFlow.isComplete() == true && leaveRequestWorkFlow.getUserId() == requestUserId) {
				return true;
			}
		}
		return false;
	}

}