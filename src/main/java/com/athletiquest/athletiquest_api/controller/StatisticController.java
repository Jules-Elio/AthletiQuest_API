package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Statistic;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.service.StatisticService;
import com.athletiquest.athletiquest_api.dto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService service;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Statistic>> getStatistics() {
        List<Statistic> result;
        try {
            result = service.findAll();
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{statisticId}")
    public ResponseEntity<Statistic> getStatistic(@PathVariable Long statisticId) {
        Statistic result;
        try {
            result = service.findById(statisticId);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Statistic> getStatisticForUserId(@PathVariable String userId) {
        User user = userService.findById(userId);
        Statistic result;
        try {
            result = service.findByUser(user);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Statistic> saveStatistic(@RequestBody Statistic statistic) {
        Statistic result;
        try {
            result = service.save(statistic);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{statisticId}")
    public ResponseEntity<String> deleteStatistic(@PathVariable Long statisticId) {
        try {
            service.delete(statisticId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
