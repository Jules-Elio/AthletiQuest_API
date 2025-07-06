package com.athletiquest.athletiquest_api.dto.service;


import com.athletiquest.athletiquest_api.dto.entity.StadiumsUpdate;
import com.athletiquest.athletiquest_api.dto.repository.StadiumsUpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class StadiumsUpdateService {

    private final StadiumsUpdateRepository stadiumsUpdateRepository;

    public boolean isLastUpdateBeforeXDays(int days) {
        Date date = Date.from(Instant.now().minus(days, ChronoUnit.DAYS));

        StadiumsUpdate lastUpdate = stadiumsUpdateRepository.findFirstByDateAfter(date);
        return lastUpdate == null;
    }

}
