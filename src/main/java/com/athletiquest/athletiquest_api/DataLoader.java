package com.athletiquest.athletiquest_api;

import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StadiumService stadiumService;

    @Override
    public void run(String... args) throws JsonProcessingException {
        log.atInfo().log("Updating stadiums into MongoDB");
        stadiumService.retrieveStadiumsFromGouvAPI();
        log.atInfo().log("Stadiums in MongoDB updated");
    }
}
