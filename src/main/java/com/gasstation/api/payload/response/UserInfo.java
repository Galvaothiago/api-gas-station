package com.gasstation.api.payload.response;

import java.util.List;

import com.gasstation.api.model.auth.ERole;

public class UserInfo {
	private Long id;
	private String username;
	private String email;
	private List<ERole> roles;
	
	public UserInfo() {
		
	}
	
	public UserInfo(Long id, String username, String email, List<ERole> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ERole> getRoles() {
		return roles;
	}

	public void setRoles(List<ERole> roles) {
		this.roles = roles;
	}
	
	
}
