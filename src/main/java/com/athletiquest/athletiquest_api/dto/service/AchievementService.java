package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Achievement;
import com.athletiquest.athletiquest_api.dto.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public List<Achievement> findAll() {
        return achievementRepository.findAll();
    }

    public Achievement findById(Long id) {
        return achievementRepository.findById(id).orElse(null);
    }

    public Achievement save(Achievement event) {
        return achievementRepository.save(event);
    }

    public void delete(Long id) {
        achievementRepository.deleteById(id);
    }

}
