package com.athletiquest.athletiquest_api.service;

import com.athletiquest.athletiquest_api.dto.entity.Statistic;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.StatisticRepository;
import com.athletiquest.athletiquest_api.dto.service.StatisticService;
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
class StatisticServiceTest {

    @Mock
    private StatisticRepository statisticRepository;

    @InjectMocks
    private StatisticService statisticService;


    @Test
    void getAllStatistics_noStatistic() {
        List<Statistic> statistics = List.of();
        when(statisticRepository.findAll()).thenReturn(statistics);
        assertThat(statisticService.findAll()).isEmpty();
    }

    @Test
    void getAllStatistics_oneStatistic() {
        List<Statistic> statistics = List.of(new Statistic());
        when(statisticRepository.findAll()).thenReturn(statistics);
        assertThat(statisticService.findAll()).hasSize(1);
    }

    @Test
    void getStatisticById_found() {
        Statistic statistic = new Statistic();
        when(statisticRepository.findById(1L)).thenReturn(Optional.of(statistic));
        assertThat(statisticService.findById(1L)).isEqualTo(statistic);
    }

    @Test
    void getStatisticById_notFound() {
        when(statisticRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(statisticService.findById(1L)).isNull();
    }

    @Test
    void getStatisticByUsername_found(){
        Statistic statistic = new Statistic();
        User user = new User();

        when(statisticRepository.findByUser(user)).thenReturn(statistic);
        assertThat(statisticService.findByUsername(user)).isEqualTo(statistic);
    }

    @Test
    void getStatisticByUsername_notFound(){
        Statistic statistic = new Statistic();
        User user = new User();

        when(statisticRepository.findByUser(user)).thenReturn(null);
        assertThat(statisticService.findByUsername(user)).isNotEqualTo(statistic);
    }

    @Test
    void saveStatistic() {
        Statistic statistic = new Statistic();
        when(statisticRepository.save(statistic)).thenReturn(statistic);
        assertThat(statisticService.save(statistic)).isEqualTo(statistic);
        verify(statisticRepository).save(statistic);
    }

    @Test
    void deleteStatistic() {
        statisticService.delete(1L);
        verify(statisticRepository).deleteById(1L);
    }
}
