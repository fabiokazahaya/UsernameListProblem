package com.intertecintl.hibernate.data;

import java.util.ArrayList;
import java.util.List;

import com.intertecintl.exception.DuplicatedUsernameException;
import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.interfaces.UsernameInterface;

/**
 * The Class Username.
 */
public class Username implements UsernameInterface{
	
	/** The username id. */
	private long usernameId;
	
	/** The username name. */
	private String usernameName;
	
	/**
	 * Instantiates a new username.
	 */
	public Username(){}
	
	/**
	 * Instantiates a new username.
	 *
	 * @param usernameId the username id
	 * @param usernameName the username name
	 */
	public Username(long usernameId, String usernameName){
		this.usernameId = usernameId;
		this.usernameName = usernameName;
	}
	
	/**
	 * Gets the username id.
	 *
	 * @return the username id
	 */
	public long getUsernameId() {
		return usernameId;
	}
	
	/**
	 * Sets the username id.
	 *
	 * @param usernameId the new username id
	 */
	public void setUsernameId(long usernameId) {
		this.usernameId = usernameId;
	}
	
	/**
	 * Gets the username name.
	 *
	 * @return the username name
	 */
	public String getUsernameName() {
		return usernameName;
	}
	
	/**
	 * Sets the username name.
	 *
	 * @param usernameName the new username name
	 */
	public void setUsernameName(String usernameName) {
		this.usernameName = usernameName;
	}
	
	/* (non-Javadoc)
	 * @see com.intertecintl.interfaces.UsernameInterface#containsValueUsername(java.lang.String, java.util.List)
	 */
	@Override
	public void containsValueUsername(String username, List<Username> usernames) {
		if (usernames.parallelStream().anyMatch(str -> str.getUsernameName().trim().equalsIgnoreCase(username))){
			throw new DuplicatedUsernameException("That username is taken. See the list and choose one.");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.intertecintl.interfaces.UsernameInterface#containsValueWord(java.lang.String, java.util.List)
	 */
	@Override
	public void containsValueWord(String word, List<Word> words) {
		if (words.parallelStream().anyMatch(str -> str.getWordName().trim().equalsIgnoreCase(word))) {
			throw new DuplicatedWordException("This username can't be used, it's on Dictionary. Please try again.");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.intertecintl.interfaces.UsernameInterface#createSuggestedList(java.lang.String)
	 */
	@Override
	public List<String> createSuggestedList(String username) {
		List<String> suggestedList = new ArrayList<>();
		for (int i = 0; i <= 13; i++) {
			suggestedList.add(String.valueOf(username).concat(String.valueOf(i)));
		}
		return suggestedList;
	}
}




