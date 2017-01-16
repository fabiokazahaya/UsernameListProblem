package com.intertecintl.interfaces;

import java.util.List;

import com.intertecintl.hibernate.data.Username;
import com.intertecintl.hibernate.data.Word;

public interface UsernameInterface {
	
	public void containsValueUsername(String username, List<Username> usernames);
	
	public void containsValueWord(String string, List<Word> list);
	
	public List<String> createSuggestedList(String username);

}
