package spring.mvc.session05.aop_lab;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {

	@Pointcut(value = "execution(* spring.mvc.session05.aop_lab.Performance.perform(..))")
	public void pt() {};
	
	@Before(value = "pt()")
	public void slienceCellPhone() {
		System.out.println("觀眾-關閉手機");
	}
	
	@Before(value = "pt()")
	public void takeSeats() {
		System.out.println("觀眾-找到座位");
	}
	
	@After(value = "pt()")
	public void applause() {
		System.out.println("觀眾-拍手鼓掌");
	}
	
	@AfterReturning(value = "pt()")
	public void exit() {
		System.out.println("觀眾-離開表演會場");
	}
	
	@AfterThrowing(value = "pt()")
	public void demanRefund() {
		System.out.println("觀眾-退票 ! 退票 ! 並離場");
	}
	
}
