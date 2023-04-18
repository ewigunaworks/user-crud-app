package com.sawitpro.sawitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sawitpro.sawitpro.model.User;
import com.sawitpro.sawitpro.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByPhoneNumber(username);

    if (user == null) {
      new UsernameNotFoundException("User Not Found with phone number: " + username);
    }

		return UserDetailsImpl.build(user);
	}

}
