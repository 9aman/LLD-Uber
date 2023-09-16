package org.aman.Strategy;

import lombok.AllArgsConstructor;
import org.aman.Exceptions.DriverNotFoundException;
import org.aman.Models.DriverPartner;
import org.aman.Models.TripMetaData;
import org.aman.Registry.DriverManager;



public class DistanceBasedMatchingStrategy implements DriverMatchingStrategy{
    DriverManager driverManager;
    double MAX_DISTANCE = 10l;

    public DistanceBasedMatchingStrategy(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public DriverPartner getDriver(TripMetaData tripMetaData) throws DriverNotFoundException {
        for(DriverPartner driver: driverManager.getAvailableDrivers()) {
            double sourceLat = tripMetaData.getSource().getLatitude();
            double sourceLong = tripMetaData.getSource().getLongitude();
            double driverLat = driver.getLocation().getLatitude();
            double driverLong = driver.getLocation().getLongitude();
            if(Math.sqrt(Math.pow(sourceLat-driverLat, 2.0) + Math.pow(sourceLong-driverLong, 2.0)) < MAX_DISTANCE) {
                if(driverManager.bookDriver(driver)) {
                    return driver;
                }
            }
        }
        throw new DriverNotFoundException();
    }
}
