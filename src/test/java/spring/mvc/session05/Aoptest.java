package spring.mvc.session05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.mvc.session05.aop_lab.AOPConfig;
import spring.mvc.session05.aop_lab.Performance;
import spring.mvc.session05.aop_lab.Singer;

public class Aoptest {

	@Test
	public void test() {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);
		Performance dancer = ctx.getBean(Performance.class);
		try {
			dancer.perform(); // 舞者表演
		} catch (Exception e) {
			System.out.println(e); // 意外發生
			// 舞者轉換跑道
			// 改行去唱歌
			// dancer 透過 introducter(經紀人) 轉換跑道
			System.out.println("舞者轉歌者");
			Singer singer = (Singer) dancer;
			singer.sing();
		}
		
	}
	
}
