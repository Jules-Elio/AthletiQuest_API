package com.athletiquest.athletiquest_api.dto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer nbEvents;
    private Time totalTime;
    private BigDecimal totalDistance;

    @OneToOne
    @JoinColumn(name = "user_account")
    private User user;
}
