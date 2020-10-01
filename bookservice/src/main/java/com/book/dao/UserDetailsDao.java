package com.book.dao;

import com.book.dto.LoggingUserDetails;

public interface UserDetailsDao {

	LoggingUserDetails getUserDetails(String userName);

}
