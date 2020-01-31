package com.invicta.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.invicta.lms.entity.LeaveManager;
import com.invicta.lms.enums.LeaveProcessType;

public interface LeaveManagerRepository extends JpaRepository<LeaveManager, Long> {

	LeaveManager findLeaveManagerById(Long id);
		
	@Query("SELECT ld FROM LeaveManager ld INNER JOIN ld.user u WHERE u.id=:userid")
	List<LeaveManager> findLeaveManagerByUserId(@Param("userid") Long userid);
	
	@Query("SELECT ldp FROM LeaveManager ldp INNER JOIN ldp.user u INNER JOIN ldp.leaveType lt WHERE u.id=:userid and lt.id=:leaveTypeid ")
	List<LeaveManager> findLeaveManagerByUserAndLeaveType(@Param("userid") Long userid,@Param("leaveTypeid") Long leaveTypeid);
	
	@Query("SELECT  sum(ldp.days) FROM LeaveManager ldp INNER JOIN ldp.user u INNER JOIN ldp.leaveType lt WHERE u.id=:userid and lt.id=:leaveTypeid ")
	Double sumLeaveDaysByUserAndLeaveType(@Param("userid") Long userid,@Param("leaveTypeid") Long leaveTypeid);
		
	@Query("SELECT  sum(ldp.days) FROM LeaveManager ldp INNER JOIN ldp.user u INNER JOIN ldp.leaveType lt WHERE u.id=:userid and lt.id=:leaveTypeid and ldp.leaveProcessType=:leaveProcessTypeid ")
	Double sumLeaveProcessTypesByUserandLeaveType(@Param("userid") Long userid,@Param("leaveTypeid") Long leaveTypeid, @Param("leaveProcessTypeid") LeaveProcessType leaveProcessTypeid);
		
	@Query("SELECT ldp.leaveType.leaveTypeName,sum(ldp.days) FROM LeaveManager ldp WHERE ldp.user.id=:userid GROUP BY ldp.leaveType.id")
	List<Object[]> getResultList(@Param("userid") Long userid);
}

