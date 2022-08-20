package spring.mvc.session14.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;



@ControllerAdvice
public class GlobalController {

	@ExceptionHandler({RuntimeException.class})
	public String Error(Exception ex, Model model, HttpServletRequest request) {
		model.addAttribute("ex",ex);
		model.addAttribute("referer" , request.getHeader("referer"));
		System.out.println("123");
		return "session14/error";
	}
	
	@ModelAttribute(name = "message")
	public String Message() {
		return "123";
	}
	
}
