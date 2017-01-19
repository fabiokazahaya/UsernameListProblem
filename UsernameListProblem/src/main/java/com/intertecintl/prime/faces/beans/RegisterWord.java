package com.intertecintl.prime.faces.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.hibernate.data.Word;
import com.intertecintl.spring.service.WordService;
import com.intertecintl.utils.MessageUtils;

/**
 * The Class RegisterWord.
 */
@ManagedBean
@SessionScoped
public class RegisterWord {
	
	
	/** The Constant LOG. */
	static final Logger LOG = LoggerFactory.getLogger(RegisterWord.class);

	/** The word service. */
	@ManagedProperty("#{wordService}")
	private WordService wordService;

	/** The word. */
	private Word word = new Word();
	
	/** The words. */
	private List<Word> words;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
    public void init() {
        words = wordService.loadWord();
    }
	
	/**
	 * Gets the words.
	 *
	 * @return the words
	 */
	public List<Word> getWords(){
		return words;
	}

	/**
	 * Gets the word service.
	 *
	 * @return the word service
	 */
	public WordService getWordService() {
		return wordService;
	}

	/**
	 * Sets the word service.
	 *
	 * @param wordService the new word service
	 */
	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public Word getWord() {
		return word;
	}

	/**
	 * Sets the word.
	 *
	 * @param word the new word
	 */
	public void setWord(Word word) {
		this.word = word;
	}

	/**
	 * Register.
	 */
	public void register() {
		
		LOG.info("Registering Word");
		
		words = wordService.loadWord();
		
		try {
			
			word.containsValueWord(word.getWordName(), words);
			
			// Calling Business Service
			wordService.register(word);
			// Add message
			MessageUtils.createMessage(FacesMessage.SEVERITY_INFO, "The Word is Registered Successfully");
			LOG.info("The Word {} Is Registered Successfully",  word.getWordName());
			
		} catch (DuplicatedWordException e) {
			
			MessageUtils.createMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
			LOG.error(e.getMessage(), e);
			
		}
	}
	
	/**
	 * On tab change.
	 *
	 * @param event the event
	 */
	public void onTabChange(TabChangeEvent event) {
		words = wordService.loadWord();
    }
}


