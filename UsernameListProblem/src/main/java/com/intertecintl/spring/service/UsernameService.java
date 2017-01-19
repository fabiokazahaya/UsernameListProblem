package com.intertecintl.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intertecintl.hibernate.data.Username;

/**
 * The Class UsernameService.
 */
@Component
public class UsernameService {
	
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
	 * @param username the username
	 */
	@Transactional
	public void register(Username username){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		session.save(username);		
	}
	
	/**
	 * Load username.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Username> loadUsername(){
		Session session = sessionFactory.getCurrentSession();
		//Getting Username object
		return session.createQuery("from Username").list(); 
	}
}


