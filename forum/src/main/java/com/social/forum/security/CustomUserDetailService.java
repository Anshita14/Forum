package com.social.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.social.forum.entity.User;
import com.social.forum.repo.UserRepo;

public class CustomUserDetailService implements UserDetailsService{
	
    @Autowired
    private UserRepo userRepo;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		return new CustomUserDetail(user);
	}
    
}
