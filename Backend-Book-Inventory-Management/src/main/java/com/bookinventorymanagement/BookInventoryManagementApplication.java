package com.bookinventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

@SpringBootApplication
public class BookInventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookInventoryManagementApplication.class, args);
	}
	
//	@Bean
//	public Validator validator() {
//		return new LocalValidatorFactoryBean();
//	}

}
