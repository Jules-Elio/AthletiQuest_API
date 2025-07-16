package com.athletiquest.athletiquest_api;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class AthletiquestApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(AthletiquestApiApplication.class, args);
    }

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
