package com.movie.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.movie.model.Rating;
import com.movie.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(@PathVariable("userid") String userid) {
		return restTemplate.getForObject("http://movie-rating-service/rating/user/"+userid, UserRating.class);
	}

	
	public UserRating getFallbackUserRating(@PathVariable("userid") String userid) {
		System.out.println("calling fallback info for userrating");
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(
				new Rating("0",0)));
		
		return userRating;
		
	}

}
