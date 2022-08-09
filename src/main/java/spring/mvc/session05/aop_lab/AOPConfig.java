package spring.mvc.session05.aop_lab;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AOPConfig {

	@Bean(name = "dancer")
	public Performance dancer() {
		Dancer dancer = new Dancer();
		return dancer;
	}
	
	@Bean
	public Audience audience() {
		return new Audience();
	}
	
	@Bean
	public Introducter getIntroducter() {
		return new Introducter();
	}
	
}
