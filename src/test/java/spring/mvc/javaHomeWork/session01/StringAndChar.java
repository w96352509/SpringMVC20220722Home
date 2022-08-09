package spring.mvc.javaHomeWork.session01;

import org.junit.Test;

public class StringAndChar {

	@Test
	public void test() {
		 String str1 = "abc123";
		 char[] charArray = str1.toCharArray();
		 for(int i=0;i<charArray.length;i++){
	          System.out.print(charArray[i]+" ");
	      }
		 char[] arr = new char[]{'h','e','l','l','o'}; 
		 String str = new String(arr);
		 System.out.println(str);
	}
	
}
