package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.dto.LieuLeaveDtoRequest;
import com.invicta.lms.dto.LieuLeaveDtoResponse;


public interface LieuLeaveRequestService {

	LieuLeaveDtoResponse applyLieuLeave(LieuLeaveDtoRequest lieuLeaveDtoRequest, Long userId);

	List<LieuLeaveDtoResponse> viewAllLieuLeaveRequest();

	Long deleteLieuLeaveRequest(Long id);

	LieuLeaveDtoResponse findLieuLeaveRequestById(Long id);
	
	List<LieuLeaveDtoResponse> findLieuLeaveRequestByUserId(Long id);


}
