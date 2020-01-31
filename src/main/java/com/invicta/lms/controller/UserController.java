package com.invicta.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.UserDtoRequest;
import com.invicta.lms.dto.UserDtoResponse;
import com.invicta.lms.dto.mapper.UserSaveDtoMapper;
import com.invicta.lms.entity.mapper.UserMapper;
import com.invicta.lms.service.RoleService;
import com.invicta.lms.service.UserService;
import com.invicta.lms.validation.UserValidation;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserValidation userValidation;
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody UserDtoRequest userDtoRequest) {
		userValidation.validationUser(userDtoRequest);
		if(!userValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(userValidation.getErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userValidation.getErrors(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<?> craeteUser(@RequestBody UserDtoRequest userDtoRequest) {
		return new ResponseEntity<>(UserMapper.mapUserToUserDto( 
				userService.addUser(
				UserSaveDtoMapper.mapUserSaveDtoToUser(userDtoRequest),
				roleService.findRoleById(userDtoRequest.getRole())
				)), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDtoResponse>> getUsers() {
		return new ResponseEntity<>(UserMapper.mapUserListToUserDtoList(userService.viewAllUser()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		if (userService.findUserById(id) != null) {

			return new ResponseEntity<>(UserMapper.mapUserToUserDto(userService.findUserById(id)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDtoRequest userDtoRequest, @PathVariable("id") Long id) {
	
		userValidation.validationUser(userDtoRequest);
		if(!userValidation.getErrors().isEmpty()) {
			return new ResponseEntity<>(userValidation.getErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(UserMapper.mapUserToUserDto(userService.updateUser(id, UserSaveDtoMapper.mapUserSaveDtoToUser(userDtoRequest),
				roleService.findRoleById(userDtoRequest.getRole()))), HttpStatus.OK);
	}

	@DeleteMapping("/{id}/{status}")
	public ResponseEntity<?> updateUserStatus(@PathVariable("id") Long id,@PathVariable("status") Boolean status ){

		return new ResponseEntity<>(UserMapper.mapUserToUserDto(userService.changedStatus(id, status)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}
}
