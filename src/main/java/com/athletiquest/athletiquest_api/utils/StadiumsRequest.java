package com.athletiquest.athletiquest_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StadiumsRequest {
    private String name = "";
    private String description = "";
    private String freeAccess = "";
    private String city = "";
    private String postalCode = "";

    private Double latitude;
    private Double longitude;
    private Double searchRadius;

    private int resultsLimit;

    public boolean validCoordinates() {
        return latitude != null && !latitude.isNaN() && longitude != null && !longitude.isNaN();
    }

    public boolean isNamedLocationSearch() {
        return !(city == null || city.isEmpty()) || !(postalCode == null || postalCode.isEmpty());
    }

    public boolean validRadius() {
        return searchRadius != null && !searchRadius.isNaN();
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
               (description == null || description.isEmpty()) &&
               (freeAccess == null || freeAccess.isEmpty()) &&
               (city == null || city.isEmpty()) &&
               (postalCode == null || postalCode.isEmpty()) &&
               !validCoordinates();
    }

    public boolean isLimited() {
        return resultsLimit > 0;
    }
}
