package org.aman.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TripMetaData {

    @Getter
    Location source;

    @Getter
    Location destination;

    @Getter
    RideType rideType;

    @Getter
    Weather weather;
}
