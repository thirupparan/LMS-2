package com.invicta.lms.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invicta.lms.dto.LeaveManagerDtoResponse;
import com.invicta.lms.dto.LeaveManagerDtoRequest;
import com.invicta.lms.dto.LeaveTypeDto;
import com.invicta.lms.entity.LeaveManager;
import com.invicta.lms.repository.LeaveTypeRepository;


@Service
public class LeaveManagerMapper {
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	
	public LeaveManager mapDtoToEntity(LeaveManagerDtoRequest leaveManagerDtoRequest,LeaveManager leaveManager) {
		leaveManager.setLeaveProcessType(leaveManagerDtoRequest.getLeaveProcessType());
		leaveManager.setDays(leaveManagerDtoRequest.getDays());
		leaveManager.setLeaveType(leaveTypeRepository.findLeaveTypeById(leaveManagerDtoRequest.getLeaveTypeId()));
		return leaveManager;
	}
	
	public LeaveManagerDtoResponse mapEntityToDto(LeaveManager leaveManager) {
		LeaveManagerDtoResponse leaveManagerDtoResponse =new LeaveManagerDtoResponse();
		leaveManagerDtoResponse.setId(leaveManager.getId());
		leaveManagerDtoResponse.setDays(leaveManager.getDays());
		leaveManagerDtoResponse.setLeaveProcessType(leaveManager.getLeaveProcessType());
		
		LeaveTypeDto leaveTypeDto=new LeaveTypeDto();
		leaveTypeDto.setId(leaveManager.getLeaveType().getId());
		leaveTypeDto.setLeaveTypeName(leaveManager.getLeaveType().getLeaveTypeName());
		leaveManagerDtoResponse.setLeaveType(leaveTypeDto);
		
		return leaveManagerDtoResponse;
		
	}	
	
	public  List<LeaveManagerDtoResponse> mapEntityToDtoList(List<LeaveManager> leaveManagerList){
		List<LeaveManagerDtoResponse> leaveManagerDtoList = new ArrayList<LeaveManagerDtoResponse>();
		if(leaveManagerList != null) {
			for(LeaveManager leaveManager:leaveManagerList) {
				leaveManagerDtoList.add(mapEntityToDto(leaveManager));	
			}
		}
		return leaveManagerDtoList;
		
	}
}
