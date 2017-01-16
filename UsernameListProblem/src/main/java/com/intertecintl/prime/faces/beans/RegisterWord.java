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

@ManagedBean
@SessionScoped
public class RegisterWord {
	
	
	static final Logger LOG = LoggerFactory.getLogger(RegisterWord.class);

	@ManagedProperty("#{wordService}")
	private WordService wordService;

	private Word word = new Word();
	
	private List<Word> words;
	
	@PostConstruct
    public void init() {
        words = wordService.loadWord();
    }
	
	public List<Word> getWords(){
		return words;
	}

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

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
	
	public void onTabChange(TabChangeEvent event) {
		words = wordService.loadWord();
    }
}


