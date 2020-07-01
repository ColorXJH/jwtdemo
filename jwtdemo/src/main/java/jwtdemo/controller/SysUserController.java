package jwtdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.impl.PublicClaims;

import jwtdemo.entity.SysUser;
import jwtdemo.service.SysService;
import jwtdemo.utils.CryiptionUtil;
import jwtdemo.utils.JwtUtils;

/**
 * @author xiajinhui 2020年6月29日
 */
@RestController
public class SysUserController {
	@Autowired
	private SysService myservice;
	@PostMapping(value = "/login")
	public Map<String, Object> login(SysUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = user.getUsername();
		String password = user.getPassword();
		String pass=myservice.findByName("xiajinhui");
		String pass2=myservice.findByName2("xiajinhui");
		System.out.println(pass);
		// 省略账号密码验证，验证成功后发送token
		String token = JwtUtils.sign(username, password);
		if (token != null) {
			map.put("code", 200);
			map.put("message", "认证成功");
			CryiptionUtil CryiptionUtil=new CryiptionUtil("DearBearLoveXJH0");
			//自己再加密一次
			token=CryiptionUtil.encryption(token);
			map.put("token", token);

		} else {
			map.put("code", "403");
			map.put("message", "认证失败");
		}
		return map;
	}
	
	@GetMapping("/api/test")
	public String get(){
        return "XJH";
    }
	@GetMapping("/logout")
	public String logout(){
		//从redis中将jwt token delete掉
		//redis.delete(token)
		return "登出";
	}

}
