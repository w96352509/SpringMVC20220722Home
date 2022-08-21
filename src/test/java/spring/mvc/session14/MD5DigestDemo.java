package spring.mvc.session14;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

public class MD5DigestDemo {
	
	// 取得加密資料
	public static String getEncryptString(String input) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = md5.digest(input.getBytes());
		// 印出 16 進為字串格式(32個字不足補0), %X 16進位
		String output = String.format("%032X", new BigInteger(result)); 
		return output;
	}
	
	public static void main(String[] args) throws Exception {
		// 鹽
		String salt = "abc";
		// 加密
		String input = "1234"; 
		String output = getEncryptString(input + salt);
		System.out.println("資料庫密碼欄位存放: " + output);
		
		// 登入
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入密碼: ");
		String pwd = scanner.next();
		pwd = pwd + "abc";
		String pwdMD5 = getEncryptString(pwd);
		System.out.println("使用者所輸入的密碼加密後的結果: " + pwdMD5);
		
		// 比對
		if(output.equals(pwdMD5)) {
			System.out.println("登入成功");
		} else {
			System.out.println("登入失敗");
		}
	}

}
