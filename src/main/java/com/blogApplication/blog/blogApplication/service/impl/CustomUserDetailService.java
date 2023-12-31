package com.blogApplication.blog.blogApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogApplication.blog.blogApplication.entities.User;
import com.blogApplication.blog.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.blog.blogApplication.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepo.findByEmail(username)
				.orElseThrow(()->new ResourceNotFoundException("User","Id"+username,0));
		return user;
	}

}
