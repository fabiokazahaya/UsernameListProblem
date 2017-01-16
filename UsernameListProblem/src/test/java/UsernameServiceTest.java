import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intertecintl.hibernate.data.Username;
import com.intertecintl.spring.service.UsernameService;

import junit.framework.Assert;

public class UsernameServiceTest {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void insertUsername() {
		
		Username username = new Username(1L, "user1");
		UsernameService usernameService = context.getBean(UsernameService.class);
		usernameService.register(username);
		List<Username> usernames = usernameService.loadUsername();
		Assert.assertNotNull(usernames);
		
	}
	
	@Test
	public void loadUsername() {
		
		Username username1 = new Username(1L, "user1");
		UsernameService usernameService = context.getBean(UsernameService.class);
		usernameService.register(username1);
		
		Username username2 = new Username(2L, "user2");
		usernameService.register(username2);
		
		Assert.assertNotNull(usernameService.loadUsername().size());
		
	}
}
