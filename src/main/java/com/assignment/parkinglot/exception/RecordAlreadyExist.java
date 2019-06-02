package com.assignment.parkinglot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordAlreadyExist extends RuntimeException {
	public RecordAlreadyExist(String exception) {
		super(exception);
	}
}
