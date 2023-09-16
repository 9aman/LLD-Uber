package org.aman.Registry;

import org.aman.Exceptions.RiderExistsException;
import org.aman.Exceptions.RiderNotFoundException;
import org.aman.Models.Rider;

import java.util.concurrent.ConcurrentHashMap;

public class RiderManager {
    ConcurrentHashMap<String, Rider> riderIDToRiderMap;

    public RiderManager() {
        this.riderIDToRiderMap = new ConcurrentHashMap<>();
    }

    public Rider addRider(Rider rider) {
        if(riderIDToRiderMap.containsKey(rider.getId())) throw new RiderExistsException();
        riderIDToRiderMap.put(rider.getId(), rider);
        return rider;
    }
    public Rider getRider(String id) {
        if(!riderIDToRiderMap.containsKey(id)) throw new RiderNotFoundException();
        return riderIDToRiderMap.get(id);
    }
}
