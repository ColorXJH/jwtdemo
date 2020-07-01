package jwtdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.impl.PublicClaims;

import jwtdemo.interceptor.JwtInterceptor;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
//拦截器配置
@Configuration
public class IntercepterConfiguration implements WebMvcConfigurer{
 
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	//拦截器注入上下文
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(authenticationInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/login");
		
	}
	
	
	@Bean
	public JwtInterceptor authenticationInterceptor() {
		return new JwtInterceptor();
	}
	
	/* *
     * <p>跨域支持 </p>
     * @Param [registry]
     * @Return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
