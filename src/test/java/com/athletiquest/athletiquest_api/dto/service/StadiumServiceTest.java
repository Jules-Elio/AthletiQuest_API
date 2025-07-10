package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class StadiumServiceTest {

    @Value("${api-gouv.stadiums.request-path}")
    private String requestPath;


    @Test
    void retrieveStadiumsFromGouvAPI_validRequest_noNullValuesInSample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Stadium.class, new Stadium.Deserializer());
        mapper.registerModule(module);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(requestPath, String.class);
        assertNotNull(response);
        String toRead = response.substring(0, response.length() - 1)
                                .replaceFirst("\\{\"total_count\": \\d*, \"results\": ", "");
        List<Stadium> testList = mapper.readValue(
                toRead, new TypeReference<>() {
                });
        assertFalse(testList.isEmpty());

        testList.forEach(stadium -> {
            assertNotNull(stadium.getId());
            assertNotNull(stadium.getName());
            assertNotNull(stadium.getDescription());
            assertNotNull(stadium.getCoordinates());
        });
    }
}
