package com.intertecintl.exception;

/**
 * The Class DuplicatedWordException.
 */
public class DuplicatedWordException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8650049984830953796L;

	/**
	 * Instantiates a new duplicated word exception.
	 */
	public DuplicatedWordException() {
		super();
	}

	/**
	 * Instantiates a new duplicated word exception.
	 *
	 * @param message the message
	 */
	public DuplicatedWordException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new duplicated word exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DuplicatedWordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new duplicated word exception.
	 *
	 * @param cause the cause
	 */
	public DuplicatedWordException(Throwable cause) {
		super(cause);
	}
}