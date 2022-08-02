package spring.mvc.session09.controller;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lotto")
public class LottoController {

	List<Set<Integer>> lottos = new CopyOnWriteArrayList<>();

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("stat" , stat());
		model.addAttribute("lotto", null);
		model.addAttribute("lottos", lottos);
		return "session09/lotto";
	}

	@RequestMapping("/get")
	public String get(Model model) {
		Set<Integer> lotto = getNumber();
		lottos.add(lotto);
		model.addAttribute("stat" , stat());
		model.addAttribute("lotto", lotto);
		model.addAttribute("lottos", lottos);
		return "session09/lotto";
	}

	@RequestMapping("/update/{index}")
	public String update(@PathVariable("index") int index, Model model) {
		lottos.set(index, getNumber());
		System.out.println("更新號碼");
		return "redirect:../";
	}

	@RequestMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		lottos.remove(index);
		System.out.println("刪除號碼");
		return "redirect:../";
	}

	private Set<Integer> getNumber() {
		Random random = new Random();
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(random.nextInt(39) + 1);
		}
		System.out.println("最新號碼:" + lotto);
		return lotto;
	}

	private Map<Integer, Long> stat() {
     
	 // 將Map資料打散並在蒐集再一起
	 List<Integer> nums = lottos.stream().flatMap(lotto->lotto.stream()).collect(Collectors.toList());
	
	 // 串流分組
	 // 將集合改成 Map -> Function.identity()
	 Map<Integer, Long> map = 
	  nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	 
	 // 排序結果 
	 Map<Integer, Long> result = new LinkedHashMap<>();
	 
	 // 將排序後放入結果
	 map.entrySet().stream()
	               .sorted(Map.Entry.<Integer , Long>comparingByValue().reversed())
	               .forEachOrdered(e-> result.put(e.getKey(), e.getValue()));
	 
	 return result;
	} 
	
}
