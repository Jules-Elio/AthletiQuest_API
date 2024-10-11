package com.athletiquest.athletiquest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {"com.athletiquest.athletiquest_api.stadiums"}
)
public class AthletiquestApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(AthletiquestApiApplication.class, args);
	}

}
