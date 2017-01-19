package com.intertecintl.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intertecintl.hibernate.data.Word;

/**
 * The Class WordService.
 */
@Component
public class WordService {
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Register.
	 *
	 * @param word the word
	 */
	@Transactional
	public void register(Word word){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save word, saving behavior get done in a transactional manner
		session.save(word);		
	}
	
	/**
	 * Load word.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> loadWord(){
		Session session = sessionFactory.getCurrentSession();
		//Getting word object
		return session.createQuery("from Word").list(); 
	}
}


