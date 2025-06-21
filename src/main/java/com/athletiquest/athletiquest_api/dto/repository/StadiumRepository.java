package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StadiumRepository extends MongoRepository<Stadium, String> {

    List<Stadium> findByNameContainingIgnoreCase(String name);

    List<Stadium> findByFreeAccess(boolean freeAccess);

    List<Stadium> findByDescriptionContainingIgnoreCase(String description);

    @Query("{ 'coordinates': { $near: { $geometry: { type: 'Point', coordinates: [?1, ?0] }, $maxDistance: ?2 } } }")
    List<Stadium> findByLocationNear(double latitude, double longitude, double maxDistance);
}
