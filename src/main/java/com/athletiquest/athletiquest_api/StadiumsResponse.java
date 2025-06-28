package com.athletiquest.athletiquest_api;

import com.athletiquest.athletiquest_api.dto.entity.Stadium;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StadiumsResponse {

    int resultCount = 0;
    List<Stadium> stadiums;

    public void setStadiums(List<Stadium> stadiums) {
        this.stadiums = stadiums;
        this.resultCount = stadiums.size();
    }
}
