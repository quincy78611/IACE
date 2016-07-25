package core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESEncrypter {

	public AESEncrypter() {
		// TODO Auto-generated constructor stub
	}

	public static String encrypt(String key, String plainText) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key.getBytes()));
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypt = cipher.doFinal(plainText.getBytes());
		String encrypted = new BASE64Encoder().encodeBuffer(encrypt);
		return URLEncoder.encode(encrypted, "UTF-8");
	}
	
	

	public static String decrypt(String key, String cipherText) throws Exception {
		String urlDecodedData=URLDecoder.decode(cipherText, "UTF-8");
		byte[] b = new BASE64Decoder().decodeBuffer(urlDecodedData);
		KeyGenerator kgen2 = KeyGenerator.getInstance("AES");
		kgen2.init(128, new SecureRandom(key.getBytes()));
		SecretKey skey2 = kgen2.generateKey();
		byte[] raw2 = skey2.getEncoded();
		SecretKeySpec skeySpec2 = new SecretKeySpec(raw2, "AES");
		Cipher cipher2 = Cipher.getInstance("AES");
		cipher2.init(Cipher.DECRYPT_MODE, skeySpec2);
		byte[] decrypt = cipher2.doFinal(b);
		return new String(decrypt);
	}
	
	public static void main(String[] args) throws Exception {
		String key = "SYSVIN";
		String text = "1";
		
		String encryptText = encrypt(key, text);
		String decryptText = decrypt(key, encryptText);
		System.out.println(encryptText);
		System.out.println(decryptText);
		
		
	}
}
