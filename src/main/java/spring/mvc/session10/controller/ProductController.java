package spring.mvc.session10.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product/")
public class ProductController {

	private List<Product> products = new CopyOnWriteArrayList<>();
	{
	  products.add(new Product("Java01",100,100.0));
	  products.add(new Product("Java02",200,200.0));
	  products.add(new Product("Java03",300,300.0));
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products" , products);
		return "session10/product";
	}
	
	@PostMapping("/")
	public String add(Product product , RedirectAttributes attr) {
		products.add(product);
		attr.addFlashAttribute("product" , product);
		return "redirect:addOk";
	}
	
	// 防止重新整理
	@GetMapping(value = {"/addOk" , "/updateOk" , "/deleteOk"})
	public String success() {
		return "session10/success";
	}
	
	@GetMapping("/get/{index}")
	public String get(@PathVariable("index") int index , Model model , @RequestParam(name = "delete" , required = false , defaultValue = "false") boolean delete) {
	    Product product = products.get(index);
		model.addAttribute("product" , product);
		model.addAttribute("index" , index );
		if(delete == false) {
			model.addAttribute("_method" , "PUT");
		}else {
			model.addAttribute("_method" , "DELETE");
		}
		return "session10/product_update";
	
	}
	
	
	
	// jsp-> <input hidden />
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index , RedirectAttributes attr , Product product) {
		products.set(index, product);
		System.out.println("修改");
		attr.addFlashAttribute("product" , product);
		return "redirect:updateOk";
	}
	
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index , RedirectAttributes attr , Product product) {
		products.remove(index);
		System.out.println("刪除");
		attr.addFlashAttribute("product" , product);
		return "redirect:deleteOk";
	}
	
	
}