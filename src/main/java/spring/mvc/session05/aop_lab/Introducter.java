package spring.mvc.session05.aop_lab;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class Introducter {

	@DeclareParents(value = "spring.mvc.session05.aop_lab.Performance+" , defaultImpl = BackSinger.class)
	public Singer singer;
	
}
