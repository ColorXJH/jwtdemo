package jwtdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//1:pom引入:mybatis-spring-boot-starter
//2:启动类中注解需要扫描的接口:@MapperScan(basePackages = "jwtdemo.dao")
//3:配置文件中加入需要扫描的xml地址
	//mybatis:
	//	mapper-locations: classpath:mapper/*.xml mapper-locations: classpath:mapper/*.xml
@MapperScan(basePackages = "jwtdemo.dao")
public class JwtApp {
	public static void main(String[] args) {
		SpringApplication.run(JwtApp.class, args);
	}

}
