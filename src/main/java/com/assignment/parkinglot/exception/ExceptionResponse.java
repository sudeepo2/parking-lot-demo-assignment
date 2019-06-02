package com.assignment.parkinglot.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponse {

	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}

	public String getMessage() {
		return this.message;
	}

	public List<String> getDetails() {
		return this.details;
	}

	private final LocalDateTime timeStamp;
	private final String message;
	private final List<String> details;

	public ExceptionResponse(LocalDateTime timeStamp, String message, List<String> details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

}
