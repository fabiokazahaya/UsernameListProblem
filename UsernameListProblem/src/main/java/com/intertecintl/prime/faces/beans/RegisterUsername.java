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

@ManagedBean
@SessionScoped
public class RegisterUsername {
	
	static final Logger LOG = LoggerFactory.getLogger(RegisterUsername.class);
	
	@ManagedProperty("#{usernameService}")
	private UsernameService usernameService;
	
	@ManagedProperty("#{wordService}")
	private WordService wordService;
	
	private boolean showSuggestedPanel = false;
	
	private boolean usernamePanel = true;
	private boolean wordPanel = false;
	
	private Username username = new Username();
	
	private List<Username> usernames;
	
	private List<String> suggestedList;
	
	@PostConstruct
    public void init() {
		usernames = usernameService.loadUsername();
    }
	
	public List<Username> getUsernames(){
		return usernames;
	}
	
	public UsernameService getUsernameService() {
		return usernameService;
	}

	public void setUsernameService(UsernameService usernameService) {
		this.usernameService = usernameService;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}

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

	private List<String> updateSuggestedList(List<String> createdList) {
		
		List<String> listOfWord = createWordList();
		List<String> listOfUsername = createUsernameList();
		
		combineWordAndUsernameLists(listOfWord, listOfUsername);
		
		suggestedList = removeCommonValues(createdList, listOfWord);
		
		return suggestedList;
		
	}

	private List<String> removeCommonValues(List<String> createdList, List<String> listOfWord) {
		createdList.removeAll(getCommonValues(listOfWord, createdList));
		return createdList;
	}

	private List<String> getCommonValues(List<String> listOfWord, List<String> createdList) {
		return createdList.stream().filter(listOfWord::contains).collect(Collectors.toList());
	}

	private void combineWordAndUsernameLists(List<String> words, List<String> usernames) {
		words.addAll(usernames);
	}

	private List<String> createUsernameList() {
		return usernameService.loadUsername().stream().map(s->s.getUsernameName()).collect(Collectors.toList());
	}

	private List<String> createWordList() {
		return wordService.loadWord().stream().map(s->s.getWordName()).collect(Collectors.toList());
	}
		
	public void showWordPanel() {
		usernamePanel = false;
		wordPanel = true;
		showSuggestedPanel= false;
	}
	
	public void showUsernamePanel() {
		usernamePanel = true;
		wordPanel = false;
	}
	
	public void onTabChange(TabChangeEvent event) {
		usernames = usernameService.loadUsername();
    }
	
	public List<Username> loadUsername(){
		return usernameService.loadUsername();
	}

	public boolean isShowSuggestedPanel() {
		return showSuggestedPanel;
	}

	public void setShowSuggestedPanel(boolean showSuggestedPanel) {
		this.showSuggestedPanel = showSuggestedPanel;
	}

	public List<String> getSuggestedList() {
		return suggestedList;
	}

	public void setSuggestedList(List<String> suggestedList) {
		this.suggestedList = suggestedList;
	}

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	public boolean isUsernamePanel() {
		return usernamePanel;
	}

	public void setUsernamePanel(boolean usernamePanel) {
		this.usernamePanel = usernamePanel;
	}

	public boolean isWordPanel() {
		return wordPanel;
	}

	public void setWordPanel(boolean wordPanel) {
		this.wordPanel = wordPanel;
	}
	
	
}



