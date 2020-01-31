package com.invicta.lms.service;

import java.util.List;

import com.invicta.lms.entity.Profile;

public interface ProfileService {
	Profile addProfile(Profile profile);
	List<Profile> viewAllProfile();
	Long deleteProfile(Long id);
	Profile updateProfile(Long id,Profile profile);
	Profile findProfileById(Long id);
}
