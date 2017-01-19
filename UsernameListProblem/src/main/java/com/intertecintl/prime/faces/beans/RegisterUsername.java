package com.intertecintl.prime.faces.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intertecintl.exception.DuplicatedUsernameException;
import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.hibernate.data.Username;
import com.intertecintl.hibernate.data.Word;
import com.intertecintl.spring.service.UsernameService;
import com.intertecintl.spring.service.WordService;
import com.intertecintl.utils.MessageUtils;

/**
 * The Class RegisterUsername.
 */
@ManagedBean
@SessionScoped
public class RegisterUsername {
	
	/** The Constant LOG. */
	static final Logger LOG = LoggerFactory.getLogger(RegisterUsername.class);
	
	/** The username service. */
	@ManagedProperty("#{usernameService}")
	private UsernameService usernameService;
	
	/** The word service. */
	@ManagedProperty("#{wordService}")
	private WordService wordService;
	
	/** The show suggested panel. */
	private boolean showSuggestedPanel = false;
	
	/** The username panel. */
	private boolean usernamePanel = true;
	
	/** The word panel. */
	private boolean wordPanel = false;
	
	/** The username. */
	private Username username = new Username();
	
	/** The usernames. */
	private List<Username> usernames;
	
	/** The suggested list. */
	private List<String> suggestedList;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
    public void init() {
		usernames = usernameService.loadUsername();
    }
	
	/**
	 * Gets the usernames.
	 *
	 * @return the usernames
	 */
	public List<Username> getUsernames(){
		return usernames;
	}
	
	/**
	 * Gets the username service.
	 *
	 * @return the username service
	 */
	public UsernameService getUsernameService() {
		return usernameService;
	}

	/**
	 * Sets the username service.
	 *
	 * @param usernameService the new username service
	 */
	public void setUsernameService(UsernameService usernameService) {
		this.usernameService = usernameService;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public Username getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(Username username) {
		this.username = username;
	}

	/**
	 * Register.
	 */
	public void register() {
		
		LOG.info("Registering Username");
		
		List<Username> userNames = loadUsername();
		List<Word> words = wordService.loadWord();
		
		try {
			
			showSuggestedPanel = false;
			
			username.containsValueUsername(username.getUsernameName(), userNames);
			username.containsValueWord(username.getUsernameName(), words);
			
			// Calling Business Service
			usernameService.register(username);
			
			// Add message
			MessageUtils.createMessage(FacesMessage.SEVERITY_INFO, "The Username is Registered Successfully");
			
			LOG.info("The Username is Registered Successfully");
			
		} catch (DuplicatedUsernameException e) {
			
			List<String> createdList = username.createSuggestedList(username.getUsernameName());
			
			suggestedList = updateSuggestedList(createdList);
			
			showSuggestedPanel = true;
			
			MessageUtils.createMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
			LOG.error(e.getMessage(), e);
			
		} catch (DuplicatedWordException e) {
			
			MessageUtils.createMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
			LOG.error(e.getMessage(), e);
			
		}
	}

	/**
	 * Update suggested list.
	 *
	 * @param createdList the created list
	 * @return the list
	 */
	private List<String> updateSuggestedList(List<String> createdList) {
		
		List<String> listOfWord = createWordList();
		List<String> listOfUsername = createUsernameList();
		
		combineWordAndUsernameLists(listOfWord, listOfUsername);
		
		suggestedList = removeCommonValues(createdList, listOfWord);
		
		return suggestedList;
		
	}

	/**
	 * Removes the common values.
	 *
	 * @param createdList the created list
	 * @param listOfWord the list of word
	 * @return the list
	 */
	private List<String> removeCommonValues(List<String> createdList, List<String> listOfWord) {
		createdList.removeAll(getCommonValues(listOfWord, createdList));
		return createdList;
	}

	/**
	 * Gets the common values.
	 *
	 * @param listOfWord the list of word
	 * @param createdList the created list
	 * @return the common values
	 */
	private List<String> getCommonValues(List<String> listOfWord, List<String> createdList) {
		return createdList.stream().filter(listOfWord::contains).collect(Collectors.toList());
	}

	/**
	 * Combine word and username lists.
	 *
	 * @param words the words
	 * @param usernames the usernames
	 */
	private void combineWordAndUsernameLists(List<String> words, List<String> usernames) {
		words.addAll(usernames);
	}

	/**
	 * Creates the username list.
	 *
	 * @return the list
	 */
	private List<String> createUsernameList() {
		return usernameService.loadUsername().stream().map(s->s.getUsernameName()).collect(Collectors.toList());
	}

	/**
	 * Creates the word list.
	 *
	 * @return the list
	 */
	private List<String> createWordList() {
		return wordService.loadWord().stream().map(s->s.getWordName()).collect(Collectors.toList());
	}
		
	/**
	 * Show word panel.
	 */
	public void showWordPanel() {
		usernamePanel = false;
		wordPanel = true;
		showSuggestedPanel= false;
	}
	
	/**
	 * Show username panel.
	 */
	public void showUsernamePanel() {
		usernamePanel = true;
		wordPanel = false;
	}
	
	/**
	 * On tab change.
	 *
	 * @param event the event
	 */
	public void onTabChange(TabChangeEvent event) {
		usernames = usernameService.loadUsername();
    }
	
	/**
	 * Load username.
	 *
	 * @return the list
	 */
	public List<Username> loadUsername(){
		return usernameService.loadUsername();
	}

	/**
	 * Checks if is show suggested panel.
	 *
	 * @return true, if is show suggested panel
	 */
	public boolean isShowSuggestedPanel() {
		return showSuggestedPanel;
	}

	/**
	 * Sets the show suggested panel.
	 *
	 * @param showSuggestedPanel the new show suggested panel
	 */
	public void setShowSuggestedPanel(boolean showSuggestedPanel) {
		this.showSuggestedPanel = showSuggestedPanel;
	}

	/**
	 * Gets the suggested list.
	 *
	 * @return the suggested list
	 */
	public List<String> getSuggestedList() {
		return suggestedList;
	}

	/**
	 * Sets the suggested list.
	 *
	 * @param suggestedList the new suggested list
	 */
	public void setSuggestedList(List<String> suggestedList) {
		this.suggestedList = suggestedList;
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
	 * Checks if is username panel.
	 *
	 * @return true, if is username panel
	 */
	public boolean isUsernamePanel() {
		return usernamePanel;
	}

	/**
	 * Sets the username panel.
	 *
	 * @param usernamePanel the new username panel
	 */
	public void setUsernamePanel(boolean usernamePanel) {
		this.usernamePanel = usernamePanel;
	}

	/**
	 * Checks if is word panel.
	 *
	 * @return true, if is word panel
	 */
	public boolean isWordPanel() {
		return wordPanel;
	}

	/**
	 * Sets the word panel.
	 *
	 * @param wordPanel the new word panel
	 */
	public void setWordPanel(boolean wordPanel) {
		this.wordPanel = wordPanel;
	}
	
	
}



