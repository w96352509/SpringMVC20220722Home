package spring.mvc.session13.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.entity.Job;
import spring.mvc.session13.repository.EmployeeDao;
import spring.mvc.session13.repository.JobDao;

@Controller
@RequestMapping("/jdbc/job")
public class JobController {

	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private int getpageCount() {
		int pageCount = (int)Math.ceil((double)jobDao.getCount()/ jobDao.LIMIT);
		return pageCount;
	}
	
	@GetMapping(value = "/")
	public String index(Model model , @ModelAttribute Job job) {
		model.addAttribute("_method","POST");
		model.addAttribute("pageCount",getpageCount());
		model.addAttribute("jobs",jobDao.query());
		model.addAttribute("employees" , employeeDao.query());
		return "session13/job";
	}
	
	@GetMapping(value = "/page/{num}")
	public String page(@PathVariable("num") Integer num , @ModelAttribute  Job job , Model model , HttpSession httpSession) {
		int offset = (num-1) * jobDao.LIMIT;
		System.out.println(offset);
		if(offset>=0) {
			httpSession.setAttribute("offset_job", offset);
		}else {
			httpSession.removeAttribute("offset_job");
		}
		model.addAttribute("_method","POST");
		model.addAttribute("pageCount",getpageCount());
		model.addAttribute("jobs",jobDao.queryPage(offset));
		model.addAttribute("employees" , employeeDao.query());
		return "session13/job";
	}
	
	@GetMapping(value = "/{jid}")
	public String get(@PathVariable("jid") Integer jid , Model model , HttpSession httpSession) {
		model.addAttribute("_method","PUT");
		model.addAttribute("job",jobDao.get(jid));
		model.addAttribute("jobs",jobDao.query(httpSession.getAttribute("offset_job")));
		model.addAttribute("employees" , employeeDao.query());
		model.addAttribute("pageCount",getpageCount());
		return "session13/job";
	}
	
	@PostMapping("/")
	public String add(@Valid Job job , BindingResult result , Model model) {
		if (result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("pageCount",getpageCount());
			model.addAttribute("jobs",jobDao.query());
			model.addAttribute("employees" , employeeDao.query());
			return "session13/job";
		}
		    jobDao.add(job);
		    return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Validated Job job , BindingResult result , Model model , HttpSession httpSession) {
		if(result.hasErrors()) {
			model.addAttribute("_method","PUT");
			model.addAttribute("job",jobDao.get(job.getJid()));
			model.addAttribute("jobs",jobDao.query(httpSession.getAttribute("offset_job")));
			model.addAttribute("employees" , employeeDao.query());
			model.addAttribute("pageCount",getpageCount());
			return "session13/job";
		}
		jobDao.update(job);
		return "redirect:./";
	}
	
	@DeleteMapping("/")
	public String delete(Job job) {
		jobDao.delete(job.getJid());
		return "redirect:./";
	}
	
}
