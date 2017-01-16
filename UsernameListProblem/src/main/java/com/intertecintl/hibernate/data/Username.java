package com.intertecintl.hibernate.data;

import java.util.ArrayList;
import java.util.List;

import com.intertecintl.exception.DuplicatedUsernameException;
import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.interfaces.UsernameInterface;

public class Username implements UsernameInterface{
	
	private long usernameId;
	private String usernameName;
	
	public Username(){}
	
	public Username(long usernameId, String usernameName){
		this.usernameId = usernameId;
		this.usernameName = usernameName;
	}
	
	public long getUsernameId() {
		return usernameId;
	}
	public void setUsernameId(long usernameId) {
		this.usernameId = usernameId;
	}
	public String getUsernameName() {
		return usernameName;
	}
	public void setUsernameName(String usernameName) {
		this.usernameName = usernameName;
	}
	
	@Override
	public void containsValueUsername(String username, List<Username> usernames) {
		if (usernames.parallelStream().anyMatch(str -> str.getUsernameName().trim().equalsIgnoreCase(username))){
			throw new DuplicatedUsernameException("That username is taken. See the list and choose one.");
		}
	}
	
	@Override
	public void containsValueWord(String word, List<Word> words) {
		if (words.parallelStream().anyMatch(str -> str.getWordName().trim().equalsIgnoreCase(word))) {
			throw new DuplicatedWordException("This username can't be used, it's on Dictionary. Please try again.");
		}
	}
	
	@Override
	public List<String> createSuggestedList(String username) {
		List<String> suggestedList = new ArrayList<>();
		for (int i = 0; i <= 13; i++) {
			suggestedList.add(String.valueOf(username).concat(String.valueOf(i)));
		}
		return suggestedList;
	}
}




