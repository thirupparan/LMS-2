package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.ProfileDto;
import com.invicta.lms.entity.Profile;


public class ProfileMapper {

	public static ProfileDto mapProfileToProfileDto(Profile profile) {
		ProfileDto profileDto = new ProfileDto();
		profileDto.setId(profile.getId());
		profileDto.setUserName(profile.getUser().getUserName());
		profileDto.setFirstName(profile.getFirstName());
		profileDto.setFirstName(profile.getLastName());
		profileDto.setAddress(profile.getAddress());
		profileDto.setTelephoneNo(profile.getTelephoneNo());
		
		return profileDto;
	}
	public static List<ProfileDto> mapProfileListToProfileDtoList(List<Profile> profileList){
		List<ProfileDto> profileDtoList = new ArrayList<ProfileDto>();
		if(profileList != null) {
			for(Profile profile:profileList) {
				profileDtoList.add(mapProfileToProfileDto(profile));	
			}
		}
		return profileDtoList;
		
	}
}
