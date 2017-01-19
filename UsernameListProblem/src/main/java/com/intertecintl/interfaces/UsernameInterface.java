package com.intertecintl.interfaces;

import java.util.List;

import com.intertecintl.hibernate.data.Username;
import com.intertecintl.hibernate.data.Word;

/**
 * The Interface UsernameInterface.
 */
public interface UsernameInterface {
	
	/**
	 * Contains value username.
	 *
	 * @param username the username
	 * @param usernames the usernames
	 */
	public void containsValueUsername(String username, List<Username> usernames);
	
	/**
	 * Contains value word.
	 *
	 * @param string the string
	 * @param list the list
	 */
	public void containsValueWord(String string, List<Word> list);
	
	/**
	 * Creates the suggested list.
	 *
	 * @param username the username
	 * @return the list
	 */
	public List<String> createSuggestedList(String username);

}
