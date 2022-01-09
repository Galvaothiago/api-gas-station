package com.gasstation.api.model;

import javax.validation.constraints.NotBlank;

public class CountingCity {
	
	@NotBlank
	private String city;
	
	@NotBlank
	private Long total;
	
	public CountingCity() {
		
	}
	
	public CountingCity(String city, Long total) {
		this.city = city;
		this.total = total;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
	
}
