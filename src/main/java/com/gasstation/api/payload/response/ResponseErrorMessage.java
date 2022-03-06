package com.gasstation.api.payload.response;

import java.time.Instant;

public class ResponseErrorMessage {
	private Instant timestamp = Instant.now();
	private int status;
	private String message;
	private String path;
	
	public ResponseErrorMessage() {
		
	}

	public ResponseErrorMessage(int status, String message, String path) {
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
