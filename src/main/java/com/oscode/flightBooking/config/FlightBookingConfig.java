package com.oscode.flightBooking.config;

import com.amadeus.Amadeus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightBookingConfig {
    public static Amadeus amadeus = Amadeus
            .builder(System.getenv())
            .build();
}
