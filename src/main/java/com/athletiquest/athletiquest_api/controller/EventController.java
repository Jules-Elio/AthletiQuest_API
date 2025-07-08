package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import com.athletiquest.athletiquest_api.dto.service.EventService;
import com.athletiquest.athletiquest_api.utils.LocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {


    private final EventService service;

    @GetMapping()
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> result;
        try {
            result = service.findAll();
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        Event result;
        try {
            result = service.findById(eventId);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{eventName}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable String eventName) {
        List<Event> result;
        try {
            result = service.searchByName(eventName);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/near")
    public ResponseEntity<List<Event>> getEventsByLocation(@RequestBody LocationRequest request) {
        List<Event> response = new ArrayList<>();
        try {
            if (!request.isEmpty()) {
                response.addAll(service.searchByLocation(
                        request.getLongitude(),
                        request.getLatitude(),
                        request.validRadius() ? request.getSearchRadius() : 5000));
            }
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event result;
        try {
            result = service.save(event);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        try {
            service.delete(eventId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
