package spring.mvc.javaHomeWork.session01;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

public class StringAndByte {

	@Test
	public void test() throws UnsupportedEncodingException {
		  String str1 = "abc123台灣";
	      byte[] bytes = str1.getBytes();//使用預設的字元集。進行轉換
	      System.out.println(Arrays.toString(bytes));
	      
	      byte[] gbks = str1.getBytes("gbk");//使用gbk字元集進行編碼
	      System.out.println(str1);
	      System.out.println(Arrays.toString(gbks));
	      System.out.println("****************");
	      
	      String str2 = new String(bytes);//使用預設字元集進行解碼
	      System.out.println(str2);
	      String gbks1 = new String(gbks);
	      System.out.println(gbks1);//亂碼
	}
	
}
