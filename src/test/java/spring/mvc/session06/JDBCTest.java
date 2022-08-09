package spring.mvc.session06;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.mvc.session06.entity.Emp;
import spring.mvc.session06.template.EmpDao;

public class JDBCTest {

	@Test
	public void test() {
		
		 //ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJDBCConfig.class);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean(EmpDao.class);
		List<Emp> emps = new ArrayList<>();
		empDao.queryJobsAndEmp().stream().forEach(System.out::println);
	}
	
}
