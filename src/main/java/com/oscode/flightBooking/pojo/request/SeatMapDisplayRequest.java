package com.oscode.flightBooking.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oscode.flightBooking.pojo.common.FlightOffer;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class SeatMapDisplayRequest {

    @JsonProperty(required = true)
    @Size(min = 1, max = 6)
    private List<FlightOffer> data;

//    private Map<String, Traveler> included;
}
