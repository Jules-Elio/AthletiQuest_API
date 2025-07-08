package com.athletiquest.athletiquest_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationRequest {
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
        return !validCoordinates();
    }
}
