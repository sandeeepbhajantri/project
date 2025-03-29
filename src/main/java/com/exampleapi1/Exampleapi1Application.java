package com.exampleapi1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Exampleapi1Application {

	public static void main(String[] args) {
                   String x = "10";
		SpringApplication.run(Exampleapi1Application.class, args);
	}
     @Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
}
}
