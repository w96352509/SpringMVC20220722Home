package spring.mvc.session15.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import spring.mvc.session15.entity.User;
import spring.mvc.session15.repository.UserRepository;

@RestController //會在每一個方法自動加上 @ResponseBody
@RequestMapping("/rest/user")
public class UserRestConrtoller {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/auto_add") //自動新增 user 資料
	@Transactional
	public String addAuto() {
		Faker faker = new Faker();
		User user = new User();
		user.setName(faker.name().firstName());
		String password = String.format("%04d", new Random().nextInt(1000));
		System.out.println("password: " + password);
		try {
			password = getEncryptString(password); // 將 password 進行加密
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("password: " + password);
		user.setPassword(password);
		user.setBirth(faker.date().birthday());
		userRepository.save(user);
		return user.toString();
	}
	
	@GetMapping("/")
	public List<User> query() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{name}")
	public User getByName(@PathVariable("name") String name) {
		return userRepository.getByName(name);
	}
	
	// 取得加密資料
	public static String getEncryptString(String input) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = md5.digest(input.getBytes());
		// 印出 16 進為字串格式(32個字不足補0), %X 16進位
		String output = String.format("%032X", new BigInteger(result)); 
		return output;
	}
	
}
