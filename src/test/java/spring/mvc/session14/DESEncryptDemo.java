package spring.mvc.session14;

import java.math.BigInteger;
import java.util.Date;

public class DESEncryptDemo {
	public static void main(String[] args) throws Exception {
		String key = "C:\\Users\\vic\\git\\SpringMVC20220722\\key\\user2.key"; // 密鑰位置
		DESEncryptService des = new DESEncryptService(key);
		
		// 明文
		String msg = "巨匠電腦 Java Web 應用程式..." + new Date();
		System.out.println("明文: " + msg);
		
		// 加密
		byte[] enMsg = des.encrytor(msg); // 將明文加密 (放進資料庫欄位中 varbinary)
		System.out.println("密文: " + String.format("%X", new BigInteger(enMsg)));
		// byte[] deMsg = des.decryptor(enMsg);
		// System.out.println("解密: " + new String(deMsg));
		
		//-----------------------------------------------------------------------------------------
		// 解密
		key = "C:\\Users\\vic\\git\\SpringMVC20220722\\key\\user2.key"; // 密鑰位置
		des = new DESEncryptService(key);
		byte[] deMsg = des.decryptor(enMsg); // 假設已經從資料庫抓到 enMsg 密文
		System.out.println("解密: " + new String(deMsg));
		
	}
}
