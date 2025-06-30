package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import com.athletiquest.athletiquest_api.utils.StadiumsRequest;
import com.athletiquest.athletiquest_api.utils.StadiumsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService service;

    @GetMapping()
    public ResponseEntity<StadiumsResponse> getStadiums(@RequestBody StadiumsRequest stadiumsRequest) {
        StadiumsResponse response = new StadiumsResponse();
        try {
            if (stadiumsRequest.isEmpty()) {
                response.setStadiums(service.findAll());
            } else {
                response.setStadiums(service.findByCriterias(stadiumsRequest));
            }
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stadium> getStadiumsById(@PathVariable String id) {
        Stadium result;
        try {
            result = service.findById(id);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/retrieve")
    public ResponseEntity<String> retrieveStadiums() {
        String result = "Error retrieving stadiums from API";
        try {
            if (service.retrieveStadiumsFromGouvAPI()) {
                result = "Stadiums successfully retrieved into MongoDB";
            }

        } catch (Exception _) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
