package spring.mvc.session12.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session12.entity.MyStock;
import spring.mvc.session12.validator.MyStockValidator;

@Controller
@RequestMapping("/mystock")
public class MyStockController {

	private List<MyStock> stocks = new CopyOnWriteArrayList<>();
	
	@Autowired
	private MyStockValidator myStockValidator;
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute MyStock myStock) {
		model.addAttribute("stocks", stocks);
		return "session12/mystock";
	}
	
	@PostMapping("/")
	public String add(Model model , @ModelAttribute MyStock myStock , BindingResult result) {
		myStockValidator.validate(myStock, result);
		if (result.hasErrors()) {
			model.addAttribute("stocks", stocks);
			return "session12/mystock";
		}
		stocks.add(myStock);
		return "redirect:./";
	}
	
}
