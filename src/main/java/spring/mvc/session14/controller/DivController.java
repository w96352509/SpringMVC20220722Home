package spring.mvc.session14.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session14.entity.Div;

@Controller
@RequestMapping("/session14/div")
public class DivController {

	@GetMapping("/")
	public String index(@ModelAttribute Div div) {
		return "session14/div";
	}
	
	@PostMapping("/")
	public String result(Model model , @ModelAttribute Div div) {
		int result = div.getX() / div.getY();
		div.setResult(result);
		if(result % 10 == 0) {
			throw new RuntimeException("結果被10整除-犯規");
		}
		model.addAttribute("result", div.getResult());
		return "session14/div";
	}
	
	
	// @ExceptionHandler({BindException.class, ArithmeticException.class, RuntimeException.class})
	@ExceptionHandler({BindException.class, ArithmeticException.class})
	public String catchException(Model model , HttpServletRequest req , Exception ex) {
		model.addAttribute("ex",ex);
		model.addAttribute("referer" , req.getHeader("referer"));
		return "session14/error";
	}
}
