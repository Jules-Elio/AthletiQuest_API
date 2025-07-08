package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import com.athletiquest.athletiquest_api.dto.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> searchByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Event> searchByLocation(Double longitude, Double latitude, Double radius) {
        return eventRepository.findWithinDistance(longitude, latitude, radius);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
