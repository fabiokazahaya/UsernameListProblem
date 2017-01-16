package com.intertecintl.utils;

public class StringUtils {
	
	private static final String NULL = null;
	private static final String BLANK = "";
	
	private static final Integer MAX_SIZE = 45;
	private static final Integer MIN_SIZE = 6;
	
	public static boolean isNullOrBlank(String string){
		return (string.trim() == BLANK) || (string == NULL);
	}
	
	public static boolean isSizeCorrect(String string){
		return string.trim().length() >= MIN_SIZE && string.trim().length() <= MAX_SIZE;
	}
}
