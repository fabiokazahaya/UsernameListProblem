package com.intertecintl.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intertecintl.hibernate.data.Username;

@Component
public class UsernameService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void register(Username username){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		session.save(username);		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Username> loadUsername(){
		Session session = sessionFactory.getCurrentSession();
		//Getting Username object
		return session.createQuery("from Username").list(); 
	}
}


