package com.oscode.flightBooking.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oscode.flightBooking.pojo.common.*;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightOfferSearch {
    private Meta meta;
    private List<Issue> warnings;
    private List<FlightOffer> data;
    private FlightOfferDictionaries dictionaries;
}
