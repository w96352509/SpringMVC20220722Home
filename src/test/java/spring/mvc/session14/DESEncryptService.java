package spring.mvc.session14;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/*
 * DES 對稱加密 (雙向)
 * DES 演算法為密碼體制中的對稱密碼體制，又被成為美國數據加密標准，是 1972 年美國 IBM 公司研制的對稱密碼體制加密演算法。 
 * 明文按 64 位進行分組, 密鑰長 64 位，密鑰事實上是 56 位參與 DES 運算
 * （第 8、16、24、32、40、48、56、64 位是校驗位， 使得每個密鑰都有奇數個 1）
 * 分組後的明文組和 56 位的密鑰按位替代或交換的方法形成密文組的加密方法。
*/
public class DESEncryptService {
	// KeyGenerator 提供對稱密鑰生成器的功能，支持各種演算法
    private KeyGenerator keygen;
    // SecretKey 負責保存對稱密鑰
    private SecretKey secretkey;
    // Cipher 負責完成加密或解密工作
    private Cipher cipher;
    
	// 生成密鑰
	public DESEncryptService(String path) throws Exception {
		// 實例化支持 DES 演算法的密鑰生成器(演算法名稱命名需按規定，否則拋出異常)
		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		
		File file  = new File(path);
		
		if(file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            secretkey = (SecretKey)ois.readObject();
        } else {
            // 生成密鑰
        	secretkey = keygen.generateKey();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(secretkey);
        }
        
		// 生成 Cipher 物件,指定其支持的 DES 演算法
		cipher = Cipher.getInstance("DES"); // Cipher 負責完成加密或解密工作
	}
	
	// 加密方法
	// @param str 要加密的明文
	public byte[] encrytor(String str) throws Exception {
        // 根據密鑰，對 Cipher 物件進行初始化，ENCRYPT_MODE 表示加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        byte[] src = str.getBytes();
        // 加密，結果保存進 cipherByte 該字節數組負責保存加密的結果
        byte[] cipherByte = cipher.doFinal(src);
        return cipherByte;
    }
	
	// 解密方法
	// @param buff 要解密的加密資料
	public byte[] decryptor(byte[] buff) throws Exception {
        // 根據密鑰，對 Cipher 物件進行初始化，DECRYPT_MODE 表示加密模式
		cipher.init(Cipher.DECRYPT_MODE, secretkey);
		// 解密，結果保存進 cipherByte 該字節數組負責保存解密的結果
		byte[] cipherByte = cipher.doFinal(buff);
        return cipherByte;
    }
}
