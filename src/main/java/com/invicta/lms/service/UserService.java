package com.invicta.lms.service;

import java.util.List;
import java.util.Optional;

import com.invicta.lms.entity.Role;
import com.invicta.lms.entity.User;

public interface UserService {
	User addUser(User user, Role role);

	List<User> viewAllUser();

	Long deleteUser(Long id);

	User updateUser(Long id, User user, Role role);

	User findUserById(Long id);

	User changedStatus(Long id, Boolean status);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Boolean existsByUsernameAndId(Long id, String username);

	Boolean existsByEmailAndId(Long id, String email);

	void updatePassword(String password, Long userId);
	
	  public User findUserByEmail(String email);

}
