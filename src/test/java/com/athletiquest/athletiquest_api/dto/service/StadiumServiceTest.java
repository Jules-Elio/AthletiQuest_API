package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StadiumServiceTest {

    @Value("${api.gouv.stadiums.request.path}")
    private String requestPath;

    private List<Stadium> testList;


    @Test
    void retrieveStadiumsFromGouvAPI_validRequest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Stadium.class, new Stadium.Deserializer());
        mapper.registerModule(module);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(MessageFormat.format(requestPath, 0), String.class);
        assert response != null;
        String toRead = response.substring(0, response.length() - 1).replaceFirst("\\{\"total_count\": \\d*, \"results\": ", "");
        testList = mapper.readValue(toRead, new TypeReference<>() {});
        assertFalse(testList.isEmpty());
    }

    @Test
    void retrieveStadiumsFromGouvAPI_noNullValuesInSample() {
        testList.forEach(stadium -> {
            assertNotNull(stadium.getId());
            assertNotNull(stadium.getName());
            assertNotNull(stadium.getDescription());
            assertNotNull(stadium.getCoordinates());
        });
    }
}