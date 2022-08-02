package spring.mvc.session10.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product/rest/")
public class ProductSPAController {

	private List<Product> products = new CopyOnWriteArrayList<>();
	{
		products.add(new Product("Java01", 100, 100.0));
		products.add(new Product("Java02", 200, 200.0));
		products.add(new Product("Java03", 300, 300.0));
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products", products);
		model.addAttribute("_method" , "POST");
		return "session10/rest/product_rest";
	}

	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, Model model, Product product , 
			          @RequestParam(name = "delete" , required = false , defaultValue = "false") boolean delete) {
		model.addAttribute("index" ,index);
		model.addAttribute("product",products.get(index));
		model.addAttribute("products", products);
		if(delete == true) {
			model.addAttribute("_method" , "DELETE");	
		}else {
			model.addAttribute("_method" , "PUT");
		}
		return "session10/rest/product_rest";
	}
	
	@PostMapping("/")
	public String add(Product product) {
		products.add(product);
		System.out.println("新增成功");
		return "redirect:./";
	}

	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index , Product product) {
		products.set(index, product);
		System.out.println("修改成功");
		return "redirect:./";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index , Product product) {
		products.remove(index);
		System.out.println("刪除成功");
		return "redirect:./";
	}
	
	
}
