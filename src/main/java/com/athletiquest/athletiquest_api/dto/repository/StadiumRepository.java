package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StadiumRepository extends MongoRepository<Stadium, String> {


    @Query(value = "{}", count = true)
    int getStadiumsCount();

    @Query("""
            {
              $and: [
                  { $or: [ { name: { $regex: ?0, $options: "i" } }, { name: {$exists: false}} ]},
                  { $or: [ { description: { $regex: ?1, $options: "i" } }, { description: {$exists: false}} ] },
                  { $or: [ { freeAccess: { $regex: ?2, $options: "i" } }, { freeAccess: {$exists: false}} ] },
                  { $or: [ { city: { $regex: ?3, $options: "i" } }, { city: {$exists: false}} ] },
                  { $or: [
                    { $or: [ { postalCode: { $regex: ?4, $options: "i" } }, { postalCode: {$exists: false}} ], },
                    { $or: [ { codeInsee: { $regex: ?4, $options: "i" } }, { codeInsee: {$exists: false}} ], }
                  ]}
              ]
            }
            """)
    List<Stadium> findByCriterias(String name, String description, String freeAccess, String city, String postalCode);

    @Query("""
            {
              $and: [
                  { $or: [ { name: { $regex: ?0, $options: "i" } }, { name: {$exists: false}} ]},
                  { $or: [ { description: { $regex: ?1, $options: "i" } }, { description: {$exists: false}} ] },
                  { $or: [ { freeAccess: { $regex: ?2, $options: "i" } }, { freeAccess: {$exists: false}} ] },
                  { $or: [ { city: { $regex: ?3, $options: "i" } }, { city: {$exists: false}} ] },
                  { $or: [
                    { $or: [ { postalCode: { $regex: ?4, $options: "i" } }, { postalCode: {$exists: false}} ], },
                    { $or: [ { codeInsee: { $regex: ?4, $options: "i" } }, { codeInsee: {$exists: false}} ], }
                  ]},
                  { coordinates: { $near: { $geometry: { type: 'Point', coordinates: [?6, ?5] }, $maxDistance: ?7 } } }
              ]
            }
            """)
    List<Stadium> findByCriteriasAndCoordinates(
            String name,
            String description,
            String freeAccess,
            String city,
            String postalCode,
            double latitude,
            double longitude,
            double maxDistance
    );

}
