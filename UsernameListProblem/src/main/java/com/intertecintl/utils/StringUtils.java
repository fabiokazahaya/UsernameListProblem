package com.intertecintl.utils;

/**
 * The Class StringUtils.
 */
public class StringUtils {
	
	/** The Constant NULL. */
	private static final String NULL = null;
	
	/** The Constant BLANK. */
	private static final String BLANK = "";
	
	/** The Constant MAX_SIZE. */
	private static final Integer MAX_SIZE = 45;
	
	/** The Constant MIN_SIZE. */
	private static final Integer MIN_SIZE = 6;
	
	/**
	 * Checks if is null or blank.
	 *
	 * @param string the string
	 * @return true, if is null or blank
	 */
	public static boolean isNullOrBlank(String string){
		return (string.trim() == BLANK) || (string == NULL);
	}
	
	/**
	 * Checks if is size correct.
	 *
	 * @param string the string
	 * @return true, if is size correct
	 */
	public static boolean isSizeCorrect(String string){
		return string.trim().length() >= MIN_SIZE && string.trim().length() <= MAX_SIZE;
	}
}
