package com.skilldistillery.exercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BootWorkOutProgramApplication extends SpringBootServletInitializer{
	
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(BootWorkOutProgramApplication.class);
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(BootWorkOutProgramApplication.class, args);
	}

}

