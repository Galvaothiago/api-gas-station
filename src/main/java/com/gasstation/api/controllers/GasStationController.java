package com.gasstation.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api" )
public class GasStationController {
	@GetMapping(path = "/")
	public String welcome() {
		String descriptionMessage = "Welcome, check our documentation to see all endpoints available";
		return descriptionMessage;
	}
	
}
