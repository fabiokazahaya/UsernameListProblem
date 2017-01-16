package com.intertecintl.exception;

public class DuplicatedUsernameException extends RuntimeException {

	private static final long serialVersionUID = 8650049984830953796L;

	public DuplicatedUsernameException() {
		super();
	}

	public DuplicatedUsernameException(String message) {
		super(message);
	}

	public DuplicatedUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedUsernameException(Throwable cause) {
		super(cause);
	}
}