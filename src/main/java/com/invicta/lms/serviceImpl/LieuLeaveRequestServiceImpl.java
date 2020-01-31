package com.invicta.lms.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LieuLeaveDtoRequest;
import com.invicta.lms.dto.LieuLeaveDtoResponse;
import com.invicta.lms.dto.mapper.LieuLeaveRequestMapper;
import com.invicta.lms.entity.LieuLeaveRequest;
import com.invicta.lms.exception.AppException;
import com.invicta.lms.exception.ResourceNotFoundException;
import com.invicta.lms.repository.LieuLeaveRequestRepository;
import com.invicta.lms.repository.UserRepository;
import com.invicta.lms.service.LieuLeaveRequestService;

@Service
public class LieuLeaveRequestServiceImpl implements LieuLeaveRequestService {

	@Autowired
	LieuLeaveRequestMapper lieuLeaveRequestMapper;

	@Autowired
	LieuLeaveRequestRepository lieuLeaveRequestRepository;

	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(LieuLeaveRequestServiceImpl.class);

	@Override
	public LieuLeaveDtoResponse applyLieuLeave(LieuLeaveDtoRequest lieuLeaveDtoRequest, Long userId) {
		LieuLeaveRequest lieuLeaveRequest = new LieuLeaveRequest();
		LieuLeaveRequest mappedlieuLeaveRequest = lieuLeaveRequestMapper.mapDtoToEntity(lieuLeaveDtoRequest,
				lieuLeaveRequest);

		try {

			mappedlieuLeaveRequest.setUser(userRepository.findUserById(userId));
			LieuLeaveRequest savedlieuLeaveRequest = lieuLeaveRequestRepository.save(lieuLeaveRequest);
			logger.info("LieuLeaveRequest creating");
			return lieuLeaveRequestMapper.mapEntityToDto(savedlieuLeaveRequest);

		} catch (Exception e) {
			logger.error("LieuLeaveRequest Not Creating at the time");

			throw new AppException("LieuLeaveRequest Not Creating");
		}

 	}

	@Override
	public List<LieuLeaveDtoResponse> viewAllLieuLeaveRequest() {
		try {
			return lieuLeaveRequestMapper.mapEntityListToDtoList(lieuLeaveRequestRepository.findAll());

		} catch (Exception e) {
			throw new AppException("LieuLeaveRequest Not Get", e.getCause());
		}
	}

	@Override
	public Long deleteLieuLeaveRequest(Long id) {
		try {
			if (lieuLeaveRequestRepository.getOne(id) != null) {
				logger.info("---------------------LieuLeaveRequest deleted-------------------");
				lieuLeaveRequestRepository.deleteById(id);
				return id;
			}

		} catch (Exception e) {
			logger.error("---------------LieuLeaveRequest id is  not found ---------------", e.fillInStackTrace());
			throw new ResourceNotFoundException("Id is", "id", id);
		}
		return null;

	}

	@Override
	public LieuLeaveDtoResponse findLieuLeaveRequestById(Long id) {
		try {
			logger.info("---------------------LieuLeaveRequest Find Lieu Leave Request By Id-------------------");
			if (lieuLeaveRequestRepository.getOne(id) != null) {
				return lieuLeaveRequestMapper.mapEntityToDto(lieuLeaveRequestRepository.findLieuLeaveRequestById(id));
			}
		} catch (Exception e) {
			logger.error("---------------------LieuLeaveRequest Find Lieu Leave Request By Id-------------------",
					e.fillInStackTrace());
			throw new ResourceNotFoundException("LieuLeaveRequest Find Lieu Leave Request By Id", "LieuLeaveRequest",
					id);
		}

		return null;
	}

	@Override
	public List<LieuLeaveDtoResponse> findLieuLeaveRequestByUserId(Long id) {
		logger.info("---------------------LieuLeaveRequest Find by User Id-------------------");
		try {
			return lieuLeaveRequestMapper.mapEntityListToDtoList(lieuLeaveRequestRepository.findLieuLeaveRequestByUserId(id));

		} catch (Exception e) {
			logger.error("---------------LieuLeaveRequest Find by User Id- ID is  not found ---------------",
					e.fillInStackTrace());
			throw new ResourceNotFoundException("LieuLeaveRequest Find by User Id", "userId", id);
		}
	}

}
