package com.gasstation.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GasStationNotFoundException extends GasStationException{
	private static final long serialVersionUID = 1L;

	public GasStationNotFoundException(String message) {
		super(message);
	}
}
