package com.athletiquest.athletiquest_api.service;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import com.athletiquest.athletiquest_api.dto.repository.EventRepository;
import com.athletiquest.athletiquest_api.dto.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;


    @Test
    void getAllEvents_noEvent() {
        List<Event> events = List.of();
        when(eventRepository.findAll()).thenReturn(events);
        assertThat(eventService.findAll()).isEmpty();
    }

    @Test
    void getAllEvents_oneEvent() {
        List<Event> events = List.of(new Event());
        when(eventRepository.findAll()).thenReturn(events);
        assertThat(eventService.findAll()).hasSize(1);
    }

    @Test
    void getEventById_found() {
        Event event = new Event();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        assertThat(eventService.findById(1L)).isEqualTo(event);
    }

    @Test
    void getEventById_notFound() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(eventService.findById(1L)).isNull();
    }

    @Test
    void getEventsByName_found() {
        Event event = new Event();
        List<Event> events = List.of(event);
        when(eventRepository.findByNameContainingIgnoreCase("Tes")).thenReturn(events);
        assertThat(eventService.searchByName("Tes")).isEqualTo(events);
    }

    @Test
    void getEventsByName_notFound() {
        Event event = new Event();
        List<Event> events = List.of(event);
        when(eventRepository.findByNameContainingIgnoreCase("NotTest")).thenReturn(List.of());
        assertThat(eventService.searchByName("NotTest")).isNotEqualTo(events);
    }

    @Test
    void saveEvent() {
        Event event = new Event();
        when(eventRepository.save(event)).thenReturn(event);
        assertThat(eventService.save(event)).isEqualTo(event);
        verify(eventRepository).save(event);
    }

    @Test
    void deleteEvent() {
        eventService.delete(1L);
        verify(eventRepository).deleteById(1L);
    }
}
