package org.aman.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Trip {
    @Getter
    private Rider rider;
    @Getter
    private DriverPartner driver;
    @Getter
    @Setter
    private Double price;
    @Getter
    @Setter
    TripMetaData tripMetaData;
    @Setter
    TripStatus tripStatus;
}
