package jwtdemo.utils;

import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
public class JwtUtils {
	//token过期时间30min
	public static final long EXPIRE_TIME=30*60*100;
	
	//校验token是否正确
	//用于拦截器拦截所有规则内的url
	public static boolean verify(String token,String username,String secret) {
		
		try {
			//设置加密算法
			Algorithm algorithm=Algorithm.HMAC256(secret);
			JWTVerifier verifier=JWT.require(algorithm).withClaim("username", username).build();
			//校验token
			DecodedJWT jwt=verifier.verify(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	//生成签名，30min后过期
	public static String  sign(String username,String secret) {
		Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
		Algorithm algorithm=Algorithm.HMAC256(secret);
		//附带username信息
		return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
		
	}
	
	//获取用户名
	public static String getUserNameByToken(HttpServletRequest request) {
		String token=request.getHeader("token");
		//解密为jwt
		CryiptionUtil CryiptionUtil=new CryiptionUtil("DearBearLoveXJH0");
		token=CryiptionUtil.decryption(token);
		DecodedJWT jwt=JWT.decode(token);
		return jwt.getClaim("username").asString();
	}
}
