package spring.mvc.javaHomeWork.session01;

import org.junit.Test;

public class StringType {

	@Test
	public void test() {
		String str = "123";
        // int num = (int) str; //必須是子父類別的關係才可以強轉
        int num = Integer.parseInt(str); //注意str中不能有數位 否則NumberFormatException
        System.out.println(num);
        String str1 = String.valueOf(num);
        String str2 = num+"";
        System.out.println(str1+" "+str2);
	}
	
}
