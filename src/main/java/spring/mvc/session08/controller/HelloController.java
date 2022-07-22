package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;

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
	public String sayHi(@RequestParam(value = "name", required = false) String name,
						@RequestParam(value = "age", defaultValue = "10") Integer age) {
		
		return String.format("Hi %s %d", name, age);
	}
	
	/*
	 * 3. Lab 練習
	 * 執行路徑: /mvc/hello/bmi?h=170&w=60
	 * 執行結果: bmi = 20.76
	 * */
	@RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") Double h,
					  @RequestParam(value = "w") Double w) {
		double bmi = w / Math.pow(h/100, 2);
		return String.format("bmi = %.2f", bmi);
	}
	
	/*
	 * 4. 同名多參數資料
	 * 執行路徑: /mvc/hello/age?age=18&age=19&age=20 
	 * 計算出: 資料筆數,總和,平均,最大值,最小值 
	 */
	@RequestMapping(value = "/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		// Int 的 統計物件
		IntSummaryStatistics stat = ageList.stream()
										   .mapToInt(Integer::intValue)
										   .summaryStatistics();
		return String.format("%s", stat.toString());
	}
	
}


