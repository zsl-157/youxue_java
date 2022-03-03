package com.youxue.project.shreal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class ShrealApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShrealApplication.class, args);
	}

}
