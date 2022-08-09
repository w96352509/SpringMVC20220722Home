package spring.mvc.javaHomeWork.session01;

import org.junit.Test;

public class StringTest {

	@Test
	public void test() {
		String s1 = "abc";
        String s2 = "hhh";
        String s3 = "abchhh";     //字面量
        String s4 = "abc"+"hhh"; //兩個字面量的連線 還是字面量 在常數池中
        String s5 = s1+"hhh";
        String s6 = "abc" + s2;
        String s7 = s1+s2;
        System.out.println(s3==s4); // true
        System.out.println(s3==s5); // f
        System.out.println(s3==s6); // f
        System.out.println(s3==s7); // f
        System.out.println(s5==s6); // f
        System.out.println(s5==s7); // f
        System.out.println(s6==s7); // f
        String s = s5;
        System.out.println(s.equals(s3));  // t
	}
	
}
