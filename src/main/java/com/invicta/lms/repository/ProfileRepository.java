package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Profile findProfileById(Long id);

}
