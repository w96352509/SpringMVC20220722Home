package spring.mvc.session13.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.repository.EmployeeDao;

@Controller
@RequestMapping("/jdbc/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	private int getPageCount() {
		int pageCount = (int)Math.ceil((double)employeeDao.getCount()/employeeDao.LIMIT); 
		return pageCount;
	}
	
	@GetMapping
	public String index(Model model , @ModelAttribute Employee employee) {
		model.addAttribute("_method","POST");
		model.addAttribute("pageCount" , getPageCount());
		model.addAttribute("employees" , employeeDao.query());
		return "session13/employee";
	}
	
	@GetMapping("/page/{num}")
	public String page(@PathVariable("num") int num, @ModelAttribute Employee employee ,HttpSession httpSession , Model model) {
		int offset = (num-1) * EmployeeDao.LIMIT;
		if(num >= 0) {
			httpSession.setAttribute("offset_emp", (num-1) * EmployeeDao.LIMIT); // offset 要 -1, 因為 offset 是從 0 開始
		} else {
			httpSession.removeAttribute("offset_emp");
		}
		model.addAttribute("_method","POST");
		model.addAttribute("pageCount" , getPageCount());
		model.addAttribute("employees" , employeeDao.queryPage(offset));
		return "session13/employee";
	}
	
	@GetMapping("/{eid}")
	public String get(@PathVariable("eid") Integer eid , Model model, HttpSession httpSession) {
		model.addAttribute("_method","PUT");
		model.addAttribute("employee" , employeeDao.get(eid));
		model.addAttribute("employees", employeeDao.query(httpSession.getAttribute("offset_emp")));
		model.addAttribute("pageCount" , getPageCount());
		return "session13/employee";
	}
	
	@PostMapping("/")
	public String add(@Valid Employee employee , BindingResult result ,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("employees" , employeeDao.query());
			model.addAttribute("pageCount" , getPageCount());
			return "session13/employee";
		}
		   employeeDao.add(employee);
		   return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid Employee employee , BindingResult result ,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method","PUT");
			model.addAttribute("employees" , employeeDao.query());
			model.addAttribute("pageCount" , getPageCount());
			return "session13/employee";
		}
		   employeeDao.update(employee);
		   return "redirect:./";
	}
	
	@DeleteMapping("/")
	public String delete(Employee employee,Model model) {
		try {
			employeeDao.delete(employee.getEid());
		} catch (Exception e) {
			model.addAttribute("_method","PUT");
			model.addAttribute("employees" , employeeDao.query());
			model.addAttribute("pageCount" , getPageCount());
			model.addAttribute("message" , "以員工尚有工作無法刪除");
			return "session13/employee";
		}
		return "redirect:./";
	}
	
}
