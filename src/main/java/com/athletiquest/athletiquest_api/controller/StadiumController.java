package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService service;

    @GetMapping()
    public ResponseEntity<List<Stadium>> getStadiums() {
        List<Stadium> result;
        try {
            result = service.findAll();
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Stadium> getStadiumsById(@PathVariable String id) {
        Stadium result;
        try {
            result = service.findById(id);
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Stadium>> getStadiumsByName(@PathVariable String name) {
        List<Stadium> result;
        try {
            result = service.findByName(name);
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/desc/{desc}")
    public ResponseEntity<List<Stadium>> getStadiumsByDesc(@PathVariable String desc) {
        List<Stadium> result;
        try {
            result = service.findByDescription(desc);
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/freeAccess/{freeAccess}")
    public ResponseEntity<List<Stadium>> getStadiumsByFreeAccess(@PathVariable boolean freeAccess) {
        List<Stadium> result;
        try {
            result = service.findByFreeAccess(freeAccess);
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = {"/coordinates/{lat},{lon}/{rad}", "/coordinates/{lat},{lon}"})
    public ResponseEntity<List<Stadium>> getStadiumsByCoordinates(@PathVariable double lat, @PathVariable double lon, @PathVariable Optional<Double> rad) {
        double radius = rad.isPresent() ? rad.get() : 5000;

        List<Stadium> result;
        try {
            result = service.getStadiumsByCoordinates(lat, lon, radius);
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<Stadium>> retrieveStadiums() {
        List<Stadium> result;
        try {
            result = service.retrieveStadiumsFromGouvAPI();
        } catch (Exception _) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
