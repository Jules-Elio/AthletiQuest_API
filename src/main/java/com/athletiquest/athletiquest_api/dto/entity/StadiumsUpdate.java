package com.athletiquest.athletiquest_api.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "StadiumsUpdate")
public class StadiumsUpdate {

    private Date date = new Date();
    private int numberOfStadiums;

    public StadiumsUpdate(int numberOfStadiums) {
        this.numberOfStadiums = numberOfStadiums;
    }
}
