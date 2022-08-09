package spring.mvc.javaHomeWork.session01;

import org.junit.Test;

public class StringAndStringBuilderAndStringBuffer {
	
	 @Test
	  public void test(){
		  String str = new String();      //char[] value = new char[0];
		  String str1 = new String("abc");//char[] value = new char[]={'a','b','c'};
		  
		  StringBuffer sb1 =  new StringBuffer();//char[] value = new char[16];//底層建立了一個長度為16的字串陣列
		  sb1.append('a');//value[0]='a';
		  sb1.append('b');//value[1]='b';
		  System.out.println("StringBuffer:"+sb1.toString());
		  
		  StringBuilder sb2 = new StringBuilder("abc"); //char[] value = new char["abc".length()+16];
		  sb2.append('a');
		  System.out.println("StringBuilder:"+sb2.toString());
		  
		  StringBuffer str2 = new StringBuffer("abc");
	      str2.setCharAt(0,'n');
	      System.out.println(str2);
	  }
}
