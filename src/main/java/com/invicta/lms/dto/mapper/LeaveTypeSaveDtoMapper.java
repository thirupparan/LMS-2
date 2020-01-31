package com.invicta.lms.dto.mapper;

import com.invicta.lms.dto.LeaveTypeDto;
import com.invicta.lms.entity.LeaveType;

public class LeaveTypeSaveDtoMapper {
	
	public static LeaveType  mapLeaveTypesaveDtoToLeaveType(LeaveTypeDto leaveTypeDto) {
		LeaveType leaveType = new LeaveType();
		leaveType.setId(leaveTypeDto.getId());
		leaveType.setLeaveTypeName(leaveTypeDto.getLeaveTypeName());
		return leaveType;
	}

}
