package com.book.service.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.book.dao.UserDetailsDao;
import com.book.dto.LoggingUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

	@Autowired
	UserDetailsDao userDetailsDao;

	@Override
	public UserDetails loadUserByUsername(String userName) {

		LOGGER.debug("retrieve the user with {}", userName);
		LoggingUserDetails user = userDetailsDao.getUserDetails(userName);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

	}

}
