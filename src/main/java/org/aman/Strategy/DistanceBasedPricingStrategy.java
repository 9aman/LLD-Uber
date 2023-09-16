package org.aman.Strategy;

import org.aman.Models.TripMetaData;

public class DistanceBasedPricingStrategy implements  PricingStrategy{
    @Override
    public double getPrice(TripMetaData tripMetaData) {
        double sourceLat = tripMetaData.getSource().getLatitude();
        double sourceLong = tripMetaData.getSource().getLongitude();
        double destinationLat = tripMetaData.getDestination().getLatitude();
        double destinationLong = tripMetaData.getDestination().getLongitude();
        return Math.sqrt(Math.pow(sourceLat-destinationLat, 2) + Math.pow(sourceLong-destinationLong, 2));
    }
}
