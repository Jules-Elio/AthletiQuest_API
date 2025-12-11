package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.athletiquest.athletiquest_api.dto.entity.StadiumsUpdate;
import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import com.athletiquest.athletiquest_api.dto.service.StadiumsUpdateService;
import com.athletiquest.athletiquest_api.utils.StadiumsRequest;
import com.athletiquest.athletiquest_api.utils.StadiumsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
@CrossOrigin
public class StadiumController {

    private final StadiumService service;
    private final StadiumsUpdateService updateService;

    @PostMapping()
    public ResponseEntity<StadiumsResponse> getStadiums(@RequestBody StadiumsRequest stadiumsRequest) {
        StadiumsResponse response = new StadiumsResponse();
        try {
            if (stadiumsRequest.isEmpty()) {
                if (stadiumsRequest.isLimited()){
                    response.setStadiums(service.findLimitedTo(stadiumsRequest.getResultsLimit()));
                }
                else {
                    response.setStadiums(service.findAll());
                }
            }
            else {
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
            service.retrieveStadiumsFromGouvAPI();
            result = "Stadiums successfully retrieved into MongoDB";
        } catch (Exception e) {
            return new ResponseEntity<>(result + "\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/updates")
    public ResponseEntity<List<StadiumsUpdate>> getStadiumsUpdates() {
        List<StadiumsUpdate> result;
        try {
            result = updateService.getStadiumsUpdates();
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
