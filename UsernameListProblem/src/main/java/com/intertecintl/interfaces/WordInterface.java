package com.intertecintl.interfaces;

import java.util.List;

import com.intertecintl.hibernate.data.Word;

public interface WordInterface {
	
	public void containsValueWord(String word, List<Word> words);

}
