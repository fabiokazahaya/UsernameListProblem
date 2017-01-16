package com.intertecintl.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intertecintl.hibernate.data.Word;

@Component
public class WordService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void register(Word word){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save word, saving behavior get done in a transactional manner
		session.save(word);		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> loadWord(){
		Session session = sessionFactory.getCurrentSession();
		//Getting word object
		return session.createQuery("from Word").list(); 
	}
}


