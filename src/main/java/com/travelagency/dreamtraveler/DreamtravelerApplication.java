package com.travelagency.dreamtraveler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DreamtravelerApplication {

	private static final Logger logger = LogManager.getLogger(DreamtravelerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DreamtravelerApplication.class, args);
		System.out.println("Hello there");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}




}
