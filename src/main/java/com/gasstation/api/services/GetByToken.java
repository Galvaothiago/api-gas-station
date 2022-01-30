package com.gasstation.api.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasstation.api.security.jwt.JwtUtils;


@Service
public class GetByToken {
	@Autowired
	JwtUtils jwtUtils;
	
	public GetByToken() {
		
	}
	
	public String username(HttpServletRequest request) {
		String[] headerAuth = request.getHeader("Authorization").split(" ");
		  String token = headerAuth[1];
		  String username = jwtUtils.getUserNameFromJwtToken(token);
		 
		  return username;
	}
}
