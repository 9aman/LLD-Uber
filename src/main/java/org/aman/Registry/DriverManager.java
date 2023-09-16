package org.aman.Registry;

import lombok.Singular;
import org.aman.Exceptions.DriverExistsException;
import org.aman.Exceptions.DriverNotFoundException;
import org.aman.Models.DriverPartner;
import org.aman.Models.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DriverManager {
    ConcurrentHashMap<String, DriverPartner> driverIdToDriverMap;

    public DriverManager() {
        this.driverIdToDriverMap = new ConcurrentHashMap<>();
    }

    public void addDriver(DriverPartner driverPartner) throws DriverExistsException {
        if(driverIdToDriverMap.containsKey(driverPartner.getId())) throw new DriverExistsException();
        driverIdToDriverMap.put(driverPartner.getId(), driverPartner);
    }
    public void removeDriver(String id){
        if(!driverIdToDriverMap.containsKey(id)) throw new DriverNotFoundException();
        driverIdToDriverMap.remove(id);
    }

    public List<DriverPartner> getAvailableDrivers() {
        List<DriverPartner> availableDrivers = new ArrayList<>();
        for(DriverPartner driverPartner: driverIdToDriverMap.values().stream().toList()) {
            if(driverPartner.getStatus() == Status.AVAILABLE) availableDrivers.add(driverPartner);
        }
        return availableDrivers;
    }

    public boolean bookDriver(DriverPartner driverPartner){
        synchronized (driverPartner){
            if(driverPartner.getStatus() != Status.AVAILABLE) return false;
            driverPartner.setStatus(Status.BUSY);
        }
        return true;
    }
    public void makeDriverAvailable(DriverPartner driverPartner){
        synchronized (driverPartner) {
            driverPartner.setStatus(Status.AVAILABLE);
        }
    }
}
