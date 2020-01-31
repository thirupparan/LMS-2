package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.entity.Profile;
import com.invicta.lms.repository.ProfileRepository;
import com.invicta.lms.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Profile addProfile(Profile profile) {
		if (profile != null) {
			return profileRepository.save(profile);
		}
		return null;
	}

	@Override
	public List<Profile> viewAllProfile() {
		return profileRepository.findAll();
	}

	@Override
	public Long deleteProfile(Long id) {
		if (profileRepository.getOne(id) != null) {
			profileRepository.deleteById(id);
			return id;
		}
		return null;
	}

	@Override
	public Profile updateProfile(Long id, Profile profile) {
		if (profileRepository.getOne(id) != null) {
			profile.setId(id);
			return profileRepository.save(profile);
		}
		return null;
	}

	@Override
	public Profile findProfileById(Long id) {
		if (profileRepository.getOne(id) != null) {
			return profileRepository.findProfileById(id);
		}
		return null;
	}

}
