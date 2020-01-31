package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.UserLeaveCount;

public interface UserLeaveCountRepository extends JpaRepository<UserLeaveCount, Long> {

}
