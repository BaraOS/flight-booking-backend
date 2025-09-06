package com.oscode.flightBooking.enums;

import lombok.Getter;

@Getter
public enum SubType {
    airport("AIRPORT"), city("CITY"), airportCity("AIRPORT,CITY");

    private final String value;

    SubType(String value) {
        this.value = value;
    }
}
