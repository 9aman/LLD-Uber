package org.aman.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class DriverPartner {

    @Getter
    private String id;
    @Getter
    private String name;

    @Getter @Setter
    private Status status;

    @Getter @Setter
    private Location location;
}
