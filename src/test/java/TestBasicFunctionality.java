import org.aman.Exceptions.DriverNotFoundException;
import org.aman.Models.*;
import org.aman.Registry.DriverManager;
import org.aman.Registry.RiderManager;
import org.aman.Registry.TripManager;
import org.aman.Strategy.DistanceBasedMatchingStrategy;
import org.aman.Strategy.DistanceBasedPricingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBasicFunctionality {
    @Test
    public void TestBasics() {
        DriverManager driverManager = new DriverManager();
        RiderManager riderManager = new RiderManager();
        addDrivers(driverManager);
        addRiders(riderManager);
        TripManager tripManager = new TripManager(driverManager, riderManager);
        TripMetaData tripMetaData = new TripMetaData(new Location(0, 0), new Location(4, 0), RideType.GO, Weather.CLEAR);
        Trip trip1 = tripManager.createTrip(tripMetaData, "1", new DistanceBasedMatchingStrategy(driverManager), new DistanceBasedPricingStrategy());
        assertEquals(trip1.getPrice(), 4);
        TripMetaData tripMetaData2 = new TripMetaData(new Location(0, 0), new Location(4, 0), RideType.GO, Weather.CLEAR);
        Trip trip2 = tripManager.createTrip(tripMetaData, "2", new DistanceBasedMatchingStrategy(driverManager), new DistanceBasedPricingStrategy());
        assertNotEquals(trip1.getDriver().getId(), trip2.getDriver().getId());
        assertNotEquals(trip1.getDriver().getId(), "3");
        assertNotEquals(trip2.getDriver().getId(), "3");
        assertThrows(DriverNotFoundException.class, () -> {
            tripManager.createTrip(tripMetaData, "3", new DistanceBasedMatchingStrategy(driverManager), new DistanceBasedPricingStrategy());
        });
    }

    private void addDrivers(DriverManager driverManager) {
        driverManager.addDriver(new DriverPartner("1", "Driver1", Status.AVAILABLE, new Location(0,0)));
        driverManager.addDriver(new DriverPartner("2", "Driver2", Status.AVAILABLE, new Location(0,1)));
        driverManager.addDriver(new DriverPartner("3", "Driver3", Status.AVAILABLE, new Location(20,0)));
    }

    private void addRiders(RiderManager riderManager) {
        riderManager.addRider(new Rider("1", "Rider1"));
        riderManager.addRider(new Rider("2", "Rider2"));
        riderManager.addRider(new Rider("3", "Rider3"));
    }
}
