package com.zkh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zkh.mapper")
public class ProjectTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTemplateApplication.class, args);
	}

}
