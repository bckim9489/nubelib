package com.winitech.katechSys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.winitech.katechSys.module.web.mybatis.mapper")
public class KatechSysApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(KatechSysApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KatechSysApplication.class);
		}


}
