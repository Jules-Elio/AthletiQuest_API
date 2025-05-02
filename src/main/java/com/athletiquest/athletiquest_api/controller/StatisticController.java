package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Statistic;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.service.StatisticService;
import com.athletiquest.athletiquest_api.dto.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService service;
    private final UserService userService;

    public StatisticController(StatisticService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }


    @GetMapping()
    public List<Statistic> getStatistics() {
        return service.findAll();
    }

    @GetMapping("/{statisticId}")
    public Statistic getStatistic(@PathVariable Long statisticId) {
        return service.findById(statisticId);
    }

    @GetMapping("/{username}")
    public Statistic getStatisticByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return service.findByUsername(user);
    }

    @PostMapping("/add")
    public Statistic addStatistic(@RequestBody Statistic statistic) {
        return service.save(statistic);
    }

    @DeleteMapping("/{statisticId}")
    public void deleteStatistic(@PathVariable Long statisticId) {
        service.delete(statisticId);
    }

}
