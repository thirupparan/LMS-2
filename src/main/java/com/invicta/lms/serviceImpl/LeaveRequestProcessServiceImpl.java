package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.dto.LeaveProcessDtoResponse;
import com.invicta.lms.dto.mapper.LeaveRequestProcessMapper;
import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.entity.LeaveRequestProcess;
import com.invicta.lms.enums.LeaveRequestAction;
import com.invicta.lms.repository.LeaveRequestProcessRepository;
import com.invicta.lms.repository.LeaveRequestRepository;
import com.invicta.lms.repository.UserRepository;
import com.invicta.lms.service.LeaveRequestProcessService;
import com.invicta.lms.service.LeaveRequestService;

@Service
public class LeaveRequestProcessServiceImpl implements LeaveRequestProcessService {
	@Autowired
	private LeaveRequestProcessRepository leaveRequestProcessRepository;
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LeaveRequestProcessMapper leaveRequestProcessMapper;
	@Autowired
	LeaveRequestService leaveRequestService;

	@Override
	public LeaveProcessDtoResponse processLeaveRequest(Long leaveRequestId, Long processUserId,
			LeaveProcessDtoRequest leaveProcessDtoRequest) {
		LeaveRequestProcess leaveRequestProcess = new LeaveRequestProcess();
		leaveRequestProcess = leaveRequestProcessMapper.mapDtoToEntity(leaveProcessDtoRequest, leaveRequestProcess);
		LeaveRequest leaveRequest = leaveRequestRepository.findLeaveRequestById(leaveRequestId);

		leaveRequestProcess.setLeaveRequest(leaveRequest);
		leaveRequestProcess.setProcessUser(userRepository.findUserById(processUserId));
		leaveRequestProcess = leaveRequestProcessRepository.save(leaveRequestProcess);
		
		leaveRequestService.modifyLeaveRequestWorkflow(leaveRequest, processUserId,
				leaveProcessDtoRequest.getLeaveRequestAction());
		return leaveRequestProcessMapper.mapEntityToDto(leaveRequestProcess);
	
	}

	@Override
	public List<LeaveProcessDtoResponse> findProcessLeaveRequestByProcessType(LeaveRequestAction leaveRequestAction) {
		return leaveRequestProcessMapper
				.mapEntityToDtoList(leaveRequestProcessRepository.findByLeaveRequestAction(leaveRequestAction));
	}

}
