package spring.mvc.session13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.repository.EmployeeDao;

@Controller
@RequestMapping("/jdbc/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping(value = "/query/json" , produces = "application/json;chartset=utf-8")
	@ResponseBody
	public List<Employee> queryJson(){
	  return employeeDao.query();	
	} 
	
}
