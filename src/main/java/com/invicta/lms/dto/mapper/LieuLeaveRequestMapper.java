package com.invicta.lms.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LieuLeaveDtoRequest;
import com.invicta.lms.dto.LieuLeaveDtoResponse;
import com.invicta.lms.dto.UserInfoDtoResponse;
import com.invicta.lms.entity.LieuLeaveRequest;

@Service
public class LieuLeaveRequestMapper {

	public LieuLeaveRequest mapDtoToEntity(LieuLeaveDtoRequest lieuLeaveDtoReuest,LieuLeaveRequest lieuLeaveRequest) {
		lieuLeaveRequest.setStartDate(lieuLeaveDtoReuest.getStartDate());
		lieuLeaveRequest.setEndDate(lieuLeaveDtoReuest.getEndDate());
		lieuLeaveRequest.setReason(lieuLeaveDtoReuest.getReason());
		lieuLeaveRequest.setWorkedHours(lieuLeaveDtoReuest.getWorkedHours());
		return lieuLeaveRequest;	
	}
	
	public LieuLeaveDtoResponse mapEntityToDto(LieuLeaveRequest lieuLeaveRequest) {
		LieuLeaveDtoResponse lieuLeaveDtoResponse = new LieuLeaveDtoResponse();
		lieuLeaveDtoResponse.setId(lieuLeaveRequest.getId());
		lieuLeaveDtoResponse.setStartDate(lieuLeaveRequest.getStartDate());
		lieuLeaveDtoResponse.setEndDate(lieuLeaveRequest.getEndDate());
		lieuLeaveDtoResponse.setWorkedHours(lieuLeaveRequest.getWorkedHours());
		lieuLeaveDtoResponse.setReason(lieuLeaveRequest.getReason());
		lieuLeaveDtoResponse.setAppliedOn(lieuLeaveRequest.getCreatedAt());
		
		UserInfoDtoResponse userInfoDtoResponse=new UserInfoDtoResponse();
		userInfoDtoResponse.setUserId(lieuLeaveRequest.getUser().getId());
		userInfoDtoResponse.setUserName(lieuLeaveRequest.getUser().getUserName());
		lieuLeaveDtoResponse.setUserInfo(userInfoDtoResponse);
		return lieuLeaveDtoResponse;

	}

	public List<LieuLeaveDtoResponse> mapEntityListToDtoList(
			List<LieuLeaveRequest> lieuLeaveRequestList) {
		List<LieuLeaveDtoResponse> lieuLeaveRequestDtoList = new ArrayList<LieuLeaveDtoResponse>();
		if (lieuLeaveRequestList != null) {
			for (LieuLeaveRequest lieuLeaveRequest : lieuLeaveRequestList) {
				lieuLeaveRequestDtoList.add(mapEntityToDto(lieuLeaveRequest));
			}
		}
		return lieuLeaveRequestDtoList;

	}
}
