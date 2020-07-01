package jwtdemo.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;

/**
 * @author xiajinhui
 * 2020年7月1日
 */

//加解密工具类
public class CryiptionUtil {
	//AES的秘钥规定是16位秘钥。
	private Key key;
	private static final String KEY_ALGORITHM="AES";
	private static final String CIPHER_ALGPRITHM="AES/ECB/PKCS5Padding";
	//默认构造函数
	public CryiptionUtil() {
		
	}
	//初始化key
	public CryiptionUtil(String keyStore) {
		this.initKey(keyStore);
	}
	public void initKey(String keyText) {
		SecretKey secretKey = new SecretKeySpec(keyText.getBytes() ,KEY_ALGORITHM) ;
		this.key = secretKey ;
	}
	
	//加密
	
	public  String encryption(String input) {
		String optput=null;
		
		try {
			Cipher cipher=Cipher.getInstance(CIPHER_ALGPRITHM);
			cipher.init(Cipher.ENCRYPT_MODE, this.key);
			optput=Base64Utils.encodeToString(cipher.doFinal(input.getBytes("UTF8")));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return optput;
	}
	//解密
	public String decryption(String input) {
		String output=null;
		try {
			Cipher cipher =Cipher.getInstance(CIPHER_ALGPRITHM) ;
			cipher.init(Cipher.DECRYPT_MODE, this.key) ;
			output=new String(cipher.doFinal(Base64Utils.decodeFromString(input)));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	//测试
	public static void main(String[] args) {
		//传入不同的密钥。如果密钥都是固定的，则方法都可以写成静态方法
		CryiptionUtil CryiptionUtil=new CryiptionUtil("DearBearLoveXJH0");
		String oraginal="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTM0ODA2MjQsInVzZXJuYW1lIjoieGlhamluaHVpIn0.MwRnvRDXSbcnpLzmAed5SHZleQeG2oJ5ljMwVg1xMp4";
		String encode=CryiptionUtil.encryption(oraginal);
		String decode=CryiptionUtil.decryption(encode);
		System.out.println(encode);
		System.out.println(decode);
		System.out.println(oraginal.equals(decode));
		
	}
	
}
