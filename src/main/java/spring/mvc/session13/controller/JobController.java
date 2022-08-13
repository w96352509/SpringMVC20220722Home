package spring.mvc.session13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session13.entity.Job;
import spring.mvc.session13.repository.JobDao;

@Controller
@RequestMapping("/jdbc/job")
public class JobController {

	@Autowired
	private JobDao jobDao;
	
	@GetMapping(value = "/query/json" , produces = "application/json;chartset=utf-8")
	@ResponseBody
	public List<Job> queryJson(){
		return jobDao.query();
	}
	
}
