package com.blogApplication.blog.blogApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.blog.blogApplication.entities.User;
import com.blogApplication.blog.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.blog.blogApplication.payloads.UserDto;
import com.blogApplication.blog.blogApplication.repository.UserRepo;
import com.blogApplication.blog.blogApplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer id) {
		User user=this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(user.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDto userToUserDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
