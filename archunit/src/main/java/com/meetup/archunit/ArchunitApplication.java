package com.meetup.archunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ArchunitApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ArchunitApplication.class, args);
	}

}
