import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intertecintl.exception.DuplicatedUsernameException;
import com.intertecintl.exception.DuplicatedWordException;
import com.intertecintl.hibernate.data.Username;
import com.intertecintl.hibernate.data.Word;
import com.intertecintl.spring.service.UsernameService;

import junit.framework.Assert;

public class RegisterUsernameTest {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void insertUsername() {

		Username username = new Username(1L, "user10");
		UsernameService usernameService = context.getBean(UsernameService.class);
		usernameService.register(username);
		List<Username> usernames = usernameService.loadUsername();
		Assert.assertNotNull(usernames);

	}
	
	@Test(expected=DuplicatedUsernameException.class)
	public void duplicatedUsername() {

		Username username1 = new Username(1L, "user1");
		Username username2 = new Username(2L, "user2");
		Username username3 = new Username(3L, "user3");
		Username username4 = new Username(4L, "user4");
		
		List<Username> usernames = new ArrayList<>();
		usernames.add(username1);
		usernames.add(username2);
		usernames.add(username3);
		usernames.add(username4);
		
		username1.containsValueUsername("user1", usernames);
		
	}
	
	@Test(expected=DuplicatedWordException.class)
	public void duplicatedWordException() {
		
		Username username = new Username();

		Word word1 = new Word(1L, "word1");
		Word word2 = new Word(1L, "word2");
		Word word3 = new Word(1L, "word3");
		Word word4 = new Word(1L, "word4");
		
		List<Word> words = new ArrayList<>();
		words.add(word1);
		words.add(word2);
		words.add(word3);
		words.add(word4);
		
		username.containsValueWord("word2", words);
		
	}
}


