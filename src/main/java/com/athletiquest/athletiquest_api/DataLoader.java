package com.athletiquest.athletiquest_api;

import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StadiumService stadiumService;
    @Value("${retrieve-stadiums-on-start}")
    private Boolean retrieveStadiumsOnStart;
    @Value("${retrieve-stadiums-on-start.if-collection-empty}")
    private Boolean retrieveStadiumsOnStartIfCollectionEmpty;

    @Override
    public void run(String... args) throws JsonProcessingException {
        if (Boolean.TRUE.equals(retrieveStadiumsOnStart) ||
            (Boolean.TRUE.equals(retrieveStadiumsOnStartIfCollectionEmpty) && stadiumService.getStadiumsCount() == 0)) {
            log.atInfo().log("Updating stadiums into MongoDB");

            if (stadiumService.retrieveStadiumsFromGouvAPI()) {
                log.atInfo().log("Stadiums in MongoDB updated");
            } else {
                log.atError().log("Error updating stadiums into MongoDB");
            }
        }
    }
}
