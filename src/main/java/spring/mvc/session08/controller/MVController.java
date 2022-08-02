package spring.mvc.session08.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mv")
public class MVController {

	// 基本配置版
	@RequestMapping("/case01")
	public ModelAndView case01() {
		String data1 = "data1";
		String view  = "show_data";
		ModelAndView mv = new ModelAndView();
		mv.addObject("data1", data1);
		mv.setViewName(view);
		return mv;
	}
	
	@RequestMapping("/case02")
	public ModelAndView case02() {
		String data1 = "data1";
		String view  = "show_data";
		return new ModelAndView(view ,"data1" , data1);
	}
	
	@RequestMapping("/case03")
	public ModelAndView case03() {
		String data1 = "data1";
		String data2 = "data2";
		String view  = "show_data";
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		map.put("data1", data1);
		map.put("data2", data2);
		mv.addAllObjects(map);
		mv.setViewName(view);
		return mv;
	}
	
	// 宣告 Model 參數 , 使用 addAttribute()
	// 回傳 view 路徑
	
	@RequestMapping("/case04")
	public String case04(Model model) {
		String data1 = "data1 Model";
		String data2 = "data2 Model";
		String view  = "show_data";
		model.addAttribute("data1" , data1);
		model.addAttribute("data2" , data2);
		return view;
	}
	
	// Map and List 參數
	@RequestMapping("/case05")
	public String case05(Model model) {
		// 固態不可新增
		List<String> names1 = Arrays.asList("Mary" , "John" , "Bob");
		model.addAttribute("data1",names1);
		List<String> name2  = new ArrayList<>(names1);
		name2.add("add");
		model.addAttribute("data2",name2);
		Map<String, String> name3 = new LinkedHashMap<>();
		name3.put("Mary", "1");
		model.addAttribute("data3",name3);
		String view  = "show_data";
		return view;
	}
	
	// redirect
	@RequestMapping("/case06")
	public String case06() {
		return "redirect:/index.jsp"; // 重定向到首頁
	}
	
	// 退後指派
	@RequestMapping("/case07")
	public String case07() {
		return "redirect:../hello/welcome"; // 重定向到首頁
	}
	
	// 外往指派
	@RequestMapping("/case08")
	public String case08() {
		return "redirect:https://www.twitch.tv/carry_game"; // 重定向到首頁
	}
	
}
