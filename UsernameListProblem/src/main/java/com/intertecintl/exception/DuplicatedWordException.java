package com.intertecintl.exception;

public class DuplicatedWordException extends RuntimeException {

	private static final long serialVersionUID = 8650049984830953796L;

	public DuplicatedWordException() {
		super();
	}

	public DuplicatedWordException(String message) {
		super(message);
	}

	public DuplicatedWordException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedWordException(Throwable cause) {
		super(cause);
	}
}