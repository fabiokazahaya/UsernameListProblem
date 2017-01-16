package com.intertecintl.hibernate.data;

import java.util.List;

import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.interfaces.WordInterface;

public class Word implements WordInterface{

	private long wordId;
	private String wordName;
	
	public Word(){}
	
	public Word(long wordId, String wordName){
		this.wordId = wordId;
		this.wordName = wordName;
	}

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	@Override
	public void containsValueWord(String word, List<Word> words) {
		if (words.parallelStream().anyMatch(str -> str.getWordName().trim().equalsIgnoreCase(word))) {
			throw new DuplicatedWordException("That word is already in Dictionary. Please, try again.");
		}
	}
}




