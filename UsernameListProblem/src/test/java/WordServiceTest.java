import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intertecintl.hibernate.data.Word;
import com.intertecintl.spring.service.WordService;

import junit.framework.Assert;

public class WordServiceTest {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void insertWord() {
		
		Word word = new Word(1L, "word1");
		WordService wordService = context.getBean(WordService.class);
		wordService.register(word);
		List<Word> words = wordService.loadWord();
		Assert.assertNotNull(words);
		
	}
	
	@Test
	public void loadWords() {
		
		Word word1 = new Word(1L, "word1");
		WordService wordService = context.getBean(WordService.class);
		wordService.register(word1);
		
		Word word2 = new Word(2L, "word2");
		wordService.register(word2);
		
		Assert.assertNotNull(wordService.loadWord().size());
		
	}
}
