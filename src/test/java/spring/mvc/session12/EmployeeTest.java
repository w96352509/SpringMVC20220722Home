package spring.mvc.session12;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.mvc.session13.repository.EmployeeDao;

public class EmployeeTest {

	@Test
	public void test() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmployeeDao employeeDao = ctx.getBean(EmployeeDao.class);
		System.out.println("數量:"  +employeeDao.getCount());
		System.out.println("全筆數:"+employeeDao.query());
	}
	
}
