package com.invicta.lms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.invicta.lms.entity.Role;
import com.invicta.lms.entity.User;
import com.invicta.lms.enums.UserStatus;
import com.invicta.lms.repository.RoleRepository;
import com.invicta.lms.repository.UserRepository;
import com.invicta.lms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public User addUser(User user, Role role) {
		if (user != null) {
			user.setUserStatus(UserStatus.ACTIVE_USER);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(role);
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public List<User> viewAllUser() {
		return userRepository.findAll();
	}

	@Override
	public Long deleteUser(Long id) {
		if (userRepository.getOne(id) != null) {
			userRepository.deleteById(id);
			return id;
		}
		return null;
	}

	@Override
	public User updateUser(Long id, User user, Role role) {
		if (userRepository.getOne(id) != null) {
			user.setUserStatus(UserStatus.ACTIVE_USER);
			user.setId(id);
			user.setRole(role);
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public User findUserById(Long id) {
		if (userRepository.getOne(id) != null) {
			return userRepository.findUserById(id);
		}
		return null;
	}

	@Override
	public User changedStatus(Long id, Boolean status) {

		User user = findUserById(id);
		if (user != null) {
			if (status == true) {
				user.setUserStatus(UserStatus.ACTIVE_USER);
			} else {
				user.setUserStatus(UserStatus.INNACTIVE_USER);
			}
			return userRepository.save(user);
		}

		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUserName(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Boolean existsByUsernameAndId(Long id, String username) {
		if (userRepository.checkWithLockUserId(id, username) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean existsByEmailAndId(Long id, String email) {
		if (userRepository.checkWithEmailLockUserId(id, email) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void updatePassword(String password, Long userId) {
		userRepository.updatePassword(password, userId);

	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
