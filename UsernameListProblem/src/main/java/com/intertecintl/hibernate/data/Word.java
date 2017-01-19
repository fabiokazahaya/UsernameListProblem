package com.intertecintl.hibernate.data;

import java.util.List;

import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.interfaces.WordInterface;

/**
 * The Class Word.
 */
public class Word implements WordInterface{

	/** The word id. */
	private long wordId;
	
	/** The word name. */
	private String wordName;
	
	/**
	 * Instantiates a new word.
	 */
	public Word(){}
	
	/**
	 * Instantiates a new word.
	 *
	 * @param wordId the word id
	 * @param wordName the word name
	 */
	public Word(long wordId, String wordName){
		this.wordId = wordId;
		this.wordName = wordName;
	}

	/**
	 * Gets the word id.
	 *
	 * @return the word id
	 */
	public long getWordId() {
		return wordId;
	}

	/**
	 * Sets the word id.
	 *
	 * @param wordId the new word id
	 */
	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	/**
	 * Gets the word name.
	 *
	 * @return the word name
	 */
	public String getWordName() {
		return wordName;
	}

	/**
	 * Sets the word name.
	 *
	 * @param wordName the new word name
	 */
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	/* (non-Javadoc)
	 * @see com.intertecintl.interfaces.WordInterface#containsValueWord(java.lang.String, java.util.List)
	 */
	@Override
	public void containsValueWord(String word, List<Word> words) {
		if (words.parallelStream().anyMatch(str -> str.getWordName().trim().equalsIgnoreCase(word))) {
			throw new DuplicatedWordException("That word is already in Dictionary. Please, try again.");
		}
	}
}




