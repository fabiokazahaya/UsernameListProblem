package com.intertecintl.interfaces;

import java.util.List;

import com.intertecintl.hibernate.data.Word;

/**
 * The Interface WordInterface.
 */
public interface WordInterface {
	
	/**
	 * Contains value word.
	 *
	 * @param word the word
	 * @param words the words
	 */
	public void containsValueWord(String word, List<Word> words);

}
