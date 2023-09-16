package org.aman.Registry;

import lombok.AllArgsConstructor;
import org.aman.Exceptions.DriverNotFoundException;
import org.aman.Exceptions.TripNotFoundException;
import org.aman.Models.*;
import org.aman.Strategy.DriverMatchingStrategy;
import org.aman.Strategy.PricingStrategy;

import java.util.concurrent.ConcurrentHashMap;



public class TripManager {
    DriverManager driverManager;
    RiderManager riderManager;

    ConcurrentHashMap<String, Trip> tripIdToTripMap;

    public TripManager(DriverManager driverManager, RiderManager riderManager) {
        this.driverManager = driverManager;
        this.riderManager = riderManager;
        tripIdToTripMap = new ConcurrentHashMap<>();
    }

    public Trip createTrip(TripMetaData tripMetaData, String riderID, DriverMatchingStrategy driverMatchingStrategy,
                           PricingStrategy pricingStrategy) throws DriverNotFoundException {
        Rider rider = riderManager.getRider(riderID);
        DriverPartner driverPartner = driverMatchingStrategy.getDriver(tripMetaData);
        double rideCost = pricingStrategy.getPrice(tripMetaData);
        return new Trip(rider, driverPartner, rideCost, tripMetaData, TripStatus.IN_PROGRESS);
    }

    public void endTrip(String id) throws TripNotFoundException {
        if(!tripIdToTripMap.containsKey(id)) throw new TripNotFoundException();
        tripIdToTripMap.get(id).setTripStatus(TripStatus.FINISHED);
        driverManager.makeDriverAvailable(tripIdToTripMap.get(id).getDriver());
    }

}
