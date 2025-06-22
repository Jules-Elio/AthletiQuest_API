package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Statistic;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    public Statistic findById(Long id) {
        return statisticRepository.findById(id).orElse(null);
    }

    public Statistic findByUser(User user) {
        return statisticRepository.findByUser(user);
    }

    public Statistic save(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    public void delete(Long id) {
        statisticRepository.deleteById(id);
    }
}
