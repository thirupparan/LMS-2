package com.invicta.lms.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invicta.lms.entity.Notification;
import com.invicta.lms.entity.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	Notification findByUser(User user);

	@Query("select n from Notification n where n.user.id=:userId AND n.isRead =0 ORDER BY n.createdAt DESC")
	List<Notification> userNotification(@Param("userId") Long userId);

	@Query("select count(n) from Notification n where n.user.id=:userId AND n.isRead =0 ORDER BY n.createdAt DESC")
	Integer userNotificationCount(@Param("userId") Long userId);
	
	ArrayList<Notification> findAll();

	Optional<Notification> findById(Long id);
}
