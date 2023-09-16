package org.aman.Strategy;

import org.aman.Exceptions.DriverNotFoundException;
import org.aman.Models.DriverPartner;
import org.aman.Models.TripMetaData;

public interface DriverMatchingStrategy {

    DriverPartner getDriver(TripMetaData tripMetaData) throws DriverNotFoundException;

}
