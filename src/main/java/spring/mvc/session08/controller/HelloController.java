package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	/* 
	 * 1.
	 * 執行路徑: /mvc/hello/welcome
	 * /mvc 在 web.xml 中有定義
	 * /hello 找到 HelloController
	 * /welcome 執行 welcome() 方法
	*/
	@RequestMapping("/welcome")
	@ResponseBody  // 直接將資料回應給前端
	public String welcome() {
		return "Welcome SpringMVC !";
	}
	
	/*
	 * 2. ?後面帶參數 @RequestParam
	 * 執行路徑: /mvc/hello/sayhi?name=John&age=18
	 * */
	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name") String name,
						@RequestParam(value = "age") Integer age) {
		
		return String.format("Hi %s %d", name, age);
	}
	
}
