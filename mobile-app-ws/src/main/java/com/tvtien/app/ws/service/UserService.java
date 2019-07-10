package com.tvtien.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tvtien.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto userDto);	
	//login
	UserDto getUser(String email);
	// get user after login
	UserDto getUserByUserId(String userId);
	//update user after login
	UserDto updateUser(String userId , UserDto userDto);
	//delete user
	void deleteUser(String userId);
	// get user for list
	List<UserDto> getUsers(int page , int limit);
	//verify email
	boolean verifyEmailToken(String token);
	//
	boolean requestPasswordReset(String email);
	//
	boolean resetPasswod(String token , String password);
}
