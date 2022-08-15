package spring.mvc.session07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookStoreTest {

	@Test
	public void test() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		
	}
	
}
