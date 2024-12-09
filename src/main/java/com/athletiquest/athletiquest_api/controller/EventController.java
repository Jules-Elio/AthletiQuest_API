package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import com.athletiquest.athletiquest_api.dto.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {


    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Event> getEvents() {
        return service.findAll();
    }

    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable Long eventId) {
        return service.findById(eventId);
    }

    @GetMapping("/{eventName}")
    public List<Event> getEvents(@PathVariable String eventName) {
        return service.searchByName(eventName);
    }

    @PostMapping("/save")
    public Event addEvent(@RequestBody Event event) {
        return service.save(event);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        service.delete(eventId);
    }

}
