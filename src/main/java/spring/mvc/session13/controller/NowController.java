package spring.mvc.session13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/now")
public class NowController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/")
	public String getNow() {
		return jdbcTemplate.queryForObject(" SELECT NOW()", String.class);
	}
	
}
