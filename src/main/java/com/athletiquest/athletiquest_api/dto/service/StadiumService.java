package com.athletiquest.athletiquest_api.dto.service;


import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.athletiquest.athletiquest_api.dto.repository.StadiumRepository;
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

    @Value("${api.gouv.stadiums.request.path}")
    private String requestPath;

    public List<Stadium> retrieveStadiumsFromGouvAPI() throws JsonProcessingException {
        int offset = 0;
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Stadium.class, new Stadium.Deserializer());
        mapper.registerModule(module);
        List<Stadium> stadiumListResult = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        while (true) {
            String response = restTemplate.getForObject(MessageFormat.format(requestPath, String.valueOf(offset)), String.class);
            assert response != null;
            // keep only the array of stadiums
            String toRead = response.substring(0, response.length() - 1).replaceFirst("\\{\"total_count\": \\d*, \"results\": ", "");
            List<Stadium> readValues = mapper.readValue(toRead, new TypeReference<>() {
            });
            if (readValues.isEmpty()) {
                break;
            }
            stadiumListResult.addAll(readValues);
            offset += 100;
        }
        stadiumRepository.saveAll(stadiumListResult);
        return stadiumListResult;
    }

    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    public Stadium findById(String id) {
        return stadiumRepository.findById(id).orElse(null);
    }

    public List<Stadium> findByName(String name) {
        return stadiumRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Stadium> findByFreeAccess(boolean freeAccess) {
        return stadiumRepository.findByFreeAccess(freeAccess);
    }

    public List<Stadium> findByDescription(String description) {
        return stadiumRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Stadium> getStadiumsByCoordinates(double latitude, double longitude, double searchRadius) {
        return stadiumRepository.findByLocationNear(latitude, longitude, searchRadius);
    }

}
