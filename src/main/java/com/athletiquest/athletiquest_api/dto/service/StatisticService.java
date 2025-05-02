package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Statistic;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticService) {
        this.statisticRepository = statisticService;
    }

    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    public Statistic findById(Long id) {
        return statisticRepository.findById(id).orElse(null);
    }

    public Statistic findByUsername(User username) {
        return statisticRepository.findByUser(username);
    }

    public Statistic save(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    public void delete(Long id) {
        statisticRepository.deleteById(id);
    }
}
