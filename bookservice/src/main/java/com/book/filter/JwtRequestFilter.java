package com.book.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.book.exceptions.JwtTokenException;
import com.book.service.impl.MyUserDetailsService;
import com.book.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LogManager.getLogger(JwtRequestFilter.class);

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	private String username = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorization = request.getHeader("Authorization");

		String jwtToken = null;
		try {
			if (authorization != null && authorization.startsWith("Bearer ")) {
				jwtToken = authorization.substring(7);
				username = getUserName(jwtToken);
			} else if (authorization == null) {
				logger.warn("Auth is missing");
			} else {
				logger.warn("JWT Token does not begin with Bearer String");
				throw new JwtTokenException("JWT Token does not begin with Bearer ");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
				// if token is valid configure Spring Security to manually set
				// authentication
				boolean isTokenValidate = jwtUtil.validateToken(jwtToken, userDetails);
				if (isTokenValidate) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		} catch (RuntimeException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(e.getMessage());
		}

	}

	public String getUserName(String token) {
		try {
			username = jwtUtil.getUsernameFromToken(token);
		} catch (IllegalArgumentException e) {
			LOGGER.error("illegal argument exception");
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT Token has expired");
			throw new JwtTokenException("JWT Token has expired");
		} catch (Exception ex) {
			LOGGER.error("error while retrieving user ");
		}
		return username;
	}
}
