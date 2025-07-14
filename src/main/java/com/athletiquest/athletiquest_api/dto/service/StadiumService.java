package com.athletiquest.athletiquest_api.dto.service;


import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.athletiquest.athletiquest_api.dto.entity.StadiumsUpdate;
import com.athletiquest.athletiquest_api.dto.repository.StadiumRepository;
import com.athletiquest.athletiquest_api.dto.repository.StadiumsUpdateRepository;
import com.athletiquest.athletiquest_api.utils.StadiumsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumsUpdateRepository stadiumsUpdateRepository;

    @Value("${api-gouv.stadiums.request-path}")
    private String requestPath;

    public void retrieveStadiumsFromGouvAPI() throws JsonProcessingException {
        int offset = 0;
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Stadium.class, new Stadium.Deserializer());
        mapper.registerModule(module);
        List<Stadium> stadiumListResult = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        while (true) {
            String response = restTemplate.getForObject(
                    MessageFormat.format(requestPath, String.valueOf(offset)),
                    String.class);
            assert response != null;
            // keep only the array of stadiums
            String toRead = response.substring(0, response.length() - 1)
                                    .replaceFirst("\\{\"total_count\": \\d*, " + "\"results\": ", "");
            List<Stadium> readValues = mapper.readValue(
                    toRead, new TypeReference<>() {
                    });
            if (readValues.isEmpty()) {
                break;
            }
            stadiumListResult.addAll(readValues);
            offset += 100;
        }
        stadiumRepository.saveAll(stadiumListResult);
        stadiumsUpdateRepository.save(new StadiumsUpdate(stadiumListResult.size()));
    }

    public int getStadiumsCount() {
        return stadiumRepository.getStadiumsCount();
    }

    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    public List<Stadium> findByCriterias(StadiumsRequest request) {
        List<Stadium> stadiumListResult;
        String regexReadyPostalCode = "^" + request.getPostalCode();
        if (request.validCoordinates()) {
            stadiumListResult = stadiumRepository.findByCriteriasAndCoordinates(
                    request.getName(),
                    request.getDescription(),
                    request.getFreeAccess(),
                    request.getCity(),
                    regexReadyPostalCode,
                    request.getLatitude(),
                    request.getLongitude(),
                    request.validRadius() ? request.getSearchRadius() : 5000);
        } else {
            stadiumListResult = stadiumRepository.findByCriterias(
                    request.getName(),
                    request.getDescription(),
                    request.getFreeAccess(),
                    request.getCity(),
                    regexReadyPostalCode);
        }
        return stadiumListResult;
    }

    public Stadium findById(String id) {
        return stadiumRepository.findById(id).orElse(null);
    }

}
