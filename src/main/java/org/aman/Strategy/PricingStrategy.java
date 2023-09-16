package org.aman.Strategy;

import org.aman.Models.TripMetaData;

public interface PricingStrategy {
    double getPrice(TripMetaData tripMetaData);
}
