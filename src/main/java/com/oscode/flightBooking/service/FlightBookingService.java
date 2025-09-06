package com.oscode.flightBooking.service;

import com.amadeus.resources.*;

import com.oscode.flightBooking.pojo.response.FlightOrder;
import com.oscode.flightBooking.pojo.response.FlightOfferPrice;
import com.oscode.flightBooking.pojo.response.FlightOfferSearch;
import com.oscode.flightBooking.pojo.request.FlightCreateOrderRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferPriceRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferSearchRequest;
import com.oscode.flightBooking.pojo.request.SeatMapDisplayRequest;
import com.oscode.flightBooking.pojo.response.SeatMapDisplay;

import java.util.List;

public interface FlightBookingService {
    FlightOfferSearch getFlightOfferSearch(FlightOfferSearchRequest request);
    FlightOfferPrice getFlightOfferPrice(FlightOfferPriceRequest request);
    FlightOrder createFlightOrder(FlightCreateOrderRequest request);
    FlightOrder getFlightOrder(String id);
    void deleteFlightOrder(String id);
    List<Location> getAirportCitySearch(String subType, String keyword);
    SeatMapDisplay getSeatMapDisplay(SeatMapDisplayRequest request);
}
