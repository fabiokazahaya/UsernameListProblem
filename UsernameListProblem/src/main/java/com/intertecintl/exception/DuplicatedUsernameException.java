package com.intertecintl.exception;

/**
 * The Class DuplicatedUsernameException.
 */
public class DuplicatedUsernameException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8650049984830953796L;

	/**
	 * Instantiates a new duplicated username exception.
	 */
	public DuplicatedUsernameException() {
		super();
	}

	/**
	 * Instantiates a new duplicated username exception.
	 *
	 * @param message the message
	 */
	public DuplicatedUsernameException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new duplicated username exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DuplicatedUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new duplicated username exception.
	 *
	 * @param cause the cause
	 */
	public DuplicatedUsernameException(Throwable cause) {
		super(cause);
	}
}