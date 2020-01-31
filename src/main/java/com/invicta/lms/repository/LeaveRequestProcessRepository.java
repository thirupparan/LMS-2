package com.invicta.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.LeaveRequestProcess;
import com.invicta.lms.enums.LeaveRequestAction;

public interface LeaveRequestProcessRepository extends JpaRepository<LeaveRequestProcess, Long> {
	List<LeaveRequestProcess> findByLeaveRequestAction(LeaveRequestAction leaveRequestAction);
}
