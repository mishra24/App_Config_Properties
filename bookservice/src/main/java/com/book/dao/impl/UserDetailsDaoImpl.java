package com.book.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.book.dao.UserDetailsDao;
import com.book.dto.LoggingUserDetails;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	private static final Logger LOGGER = LogManager.getLogger(UserDetailsDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public LoggingUserDetails getUserDetails(String userName) {
		LOGGER.info("Get user details by {}", userName);
		LoggingUserDetails loggingUserDetails;

		loggingUserDetails = jdbcTemplate.queryForObject("select username,password from user where username=?",
				new Object[] { userName }, new BeanPropertyRowMapper<LoggingUserDetails>(LoggingUserDetails.class));
		return loggingUserDetails;

	}

}
