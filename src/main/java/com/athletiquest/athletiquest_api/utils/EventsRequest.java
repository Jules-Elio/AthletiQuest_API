package com.athletiquest.athletiquest_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventsRequest {
    private String name;
    private String description;
    private Date startDate;
    private String ownerId;

    private Double latitude;
    private Double longitude;
    private Double searchRadius;

    public boolean validCoordinates() {
        return latitude != null && !latitude.isNaN() && longitude != null && !longitude.isNaN();
    }

    public boolean validRadius() {
        return searchRadius != null && !searchRadius.isNaN();
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
               (description == null || description.isEmpty()) &&
               startDate == null &&
               (ownerId == null || ownerId.isEmpty()) &&
               !validCoordinates();
    }
}
