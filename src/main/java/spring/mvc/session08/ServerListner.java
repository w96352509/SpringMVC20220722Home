package spring.mvc.session08;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerListner implements ServletContextListener {

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("tomcat 啟動");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Tomcat 關閉中 ...");
	}
	
}
