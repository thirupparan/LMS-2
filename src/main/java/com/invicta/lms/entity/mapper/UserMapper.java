package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.dto.UserDtoResponse;
import com.invicta.lms.entity.User;

public class UserMapper {
	public static UserDtoResponse mapUserToUserDto(User user) {
		UserDtoResponse userDtoResponse = new UserDtoResponse();
		userDtoResponse.setId(user.getId());
		userDtoResponse.setUserName(user.getUserName());
		userDtoResponse.setEmail(user.getEmail());
		
		RoleDto role=new RoleDto();
		role.setId(user.getRole().getId());
		role.setRoleName(user.getRole().getRoleName());
		
		userDtoResponse.setRole(role);
		userDtoResponse.setPassword(user.getPassword());
		if(user.getUserStatus().name()=="ACTIVE_USER") {
			userDtoResponse.setUserStatus(true);
		}else {
			userDtoResponse.setUserStatus(false);
		}
		
		return userDtoResponse;
	}
	public static List<UserDtoResponse> mapUserListToUserDtoList(List<User> userList){
		List<UserDtoResponse> userDtoList = new ArrayList<UserDtoResponse>();
		if(userList != null) {
			for(User user:userList) {
				userDtoList.add(mapUserToUserDto(user));	
			}
		}
		return userDtoList;
		
	}
}
