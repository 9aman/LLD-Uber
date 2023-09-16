package org.aman.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Location {

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;
}
