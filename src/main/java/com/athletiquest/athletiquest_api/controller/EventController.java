package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import com.athletiquest.athletiquest_api.dto.service.EventService;
import com.athletiquest.athletiquest_api.dto.service.UserService;
import com.athletiquest.athletiquest_api.utils.EventsRequest;
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
    private final UserService userService;


    @GetMapping()
    public ResponseEntity<List<Event>> getEvents(@RequestBody EventsRequest request) {
        List<Event> result;
        try {
            if (request.isEmpty()) {
                result = service.findAll();
            } else if (request.validCoordinates()) {
                result = service.searchByLocation(
                        request.getLongitude(),
                        request.getLatitude(),
                        request.validRadius() ? request.getSearchRadius() : 5000);
            } else {
                result = new ArrayList<>();
            }
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

    @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
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

    @PostMapping("/{eventId}/signup")
    public ResponseEntity<Event> signUpToEvent(@PathVariable Long eventId) {
        Event result;
        try {
            result = service.signUp(service.findById(eventId));
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/signout")
    public ResponseEntity<Event> signOutFromEvent(@PathVariable Long eventId) {
        Event result;
        try {
            result = service.signOut(service.findById(eventId));
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Event>> getEventsOfUser(@PathVariable String userId) {
        List<Event> result;
        try {
            result = service.findAllByOwner(userService.findById(userId));
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/signedin")
    public ResponseEntity<List<Event>> getSingedInEventsOfUser(@PathVariable String userId) {
        List<Event> result;
        try {
            result = service.findAllBySignedInUser(userService.findById(userId));
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<List<Event>> getEventsCurrentUser() {
        List<Event> result;
        try {
            result = service.findAllByOwner(userService.getCurrentUser());
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/currentUser/signedin")
    public ResponseEntity<List<Event>> getSingedInEventsCurrentUser() {
        List<Event> result;
        try {
            result = service.findAllBySignedInUser(userService.getCurrentUser());
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/currentUser/{eventId}")
    public ResponseEntity<String> deleteEventsCurrentUser(@PathVariable Long eventId) {
        try {
            if (service.isFromCurrentUser(eventId)) {
                service.delete(eventId);
            } else {
                return new ResponseEntity<>("You can't delete this event", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
