package com.nubelib.sbAchive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.nubelib.sbAchive.module.web.mybatis.mapper")
public class sbAchiveApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(sbAchiveApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(sbAchiveApplication.class);
		}


}
