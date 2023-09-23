package com.blogApplication.blog.blogApplication.service;

import java.util.List;

import com.blogApplication.blog.blogApplication.payloads.UserDto;

public interface UserService { 
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto userDto,Integer id);
	
	UserDto getUserById(Integer id);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer id);
}
