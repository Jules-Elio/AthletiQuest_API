package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.StadiumsUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface StadiumsUpdateRepository extends MongoRepository<StadiumsUpdate, String> {

    StadiumsUpdate findFirstByDateAfter(Date date);
}
