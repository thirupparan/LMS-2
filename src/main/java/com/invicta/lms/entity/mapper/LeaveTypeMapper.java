package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.LeaveTypeDto;
import com.invicta.lms.entity.LeaveType;

public class LeaveTypeMapper {
	public static LeaveTypeDto mapLeaveTypeToLeaveTypeDto(LeaveType leaveType) {
		LeaveTypeDto leaveTypeDto =new LeaveTypeDto();
		leaveTypeDto.setId(leaveType.getId());
		leaveTypeDto.setLeaveTypeName(leaveType.getLeaveTypeName());
		return leaveTypeDto;
		
	}
	
	
	public static List<LeaveTypeDto>mapLeaveTypeListToLeaveTypeDtoList(List<LeaveType> leaveTypeList){
		List <LeaveTypeDto>leaveTypeDtoList =new ArrayList<>();
		if(leaveTypeDtoList !=null) {
			for(LeaveType leaveType:leaveTypeList) {
				leaveTypeDtoList.add(mapLeaveTypeToLeaveTypeDto(leaveType));
			}
		}
		return leaveTypeDtoList;
		
	}

}
