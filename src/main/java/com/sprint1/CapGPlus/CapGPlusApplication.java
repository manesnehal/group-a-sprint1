package com.sprint1.CapGPlus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CapGPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapGPlusApplication.class, args);
	}

}
