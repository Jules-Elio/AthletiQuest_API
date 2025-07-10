package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM Location WHERE ST_DWithin(coordinates, ST_SetSRID(ST_MakePoint(?1, ?2), 4326), ?3)",
           nativeQuery = true)
    List<Event> findWithinDistance(double longitude, double latitude, double radius);
}
