package com.travelagency.dreamtraveler;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DreamtravelerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamtravelerApplication.class, args);
		System.out.println("Hello there");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}




}
