package com.invicta.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.entity.User;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
	LeaveRequest findLeaveRequestById(Long id);
	List<LeaveRequest> findByrequestedUser(User requestedUser);
}
