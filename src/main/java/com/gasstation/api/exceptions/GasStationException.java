package com.gasstation.api.exceptions;

public class GasStationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GasStationException(String message) {
		super(message);
	}

}
