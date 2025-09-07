package com.oscode.flightBooking.service.impl;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.*;
import com.oscode.flightBooking.pojo.response.FlightOrder;
import com.oscode.flightBooking.pojo.response.FlightOfferPrice;
import com.oscode.flightBooking.pojo.response.FlightOfferSearch;
import com.oscode.flightBooking.pojo.request.FlightCreateOrderRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferPriceRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferSearchRequest;
import com.oscode.flightBooking.pojo.request.SeatMapDisplayRequest;
import com.oscode.flightBooking.pojo.response.SeatMapDisplay;
import com.oscode.flightBooking.service.FlightBookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oscode.flightBooking.config.FlightBookingConfig.amadeus;
import static com.oscode.flightBooking.utils.ConvertUtils.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightBookingServiceImpl implements FlightBookingService {

    @Override
    public FlightOfferSearch getFlightOfferSearch(FlightOfferSearchRequest request) {
        try {
            var response = amadeus.post("/v2/shopping/flight-offers", convertObjectToJson(request));
            return convertJsonToObject(response.getBody(), FlightOfferSearch.class);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlightOfferPrice getFlightOfferPrice(FlightOfferPriceRequest request) {
        try {
            var response = amadeus.post("/v1/shopping/flight-offers/pricing", convertObjectToJson(request));
            return convertJsonToObject(response.getBody(), FlightOfferPrice.class);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlightOrder createFlightOrder(FlightCreateOrderRequest request) {
        try {
            var response = amadeus.post("/v1/booking/flight-orders", convertObjectToJson(request));
            return convertJsonToObject(response.getBody(), FlightOrder.class);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlightOrder getFlightOrder(String id) {
        try {
            var response = amadeus.get(String.format("/v1/booking/flight-orders/%s",id));
            return convertJsonToObject(response.getBody(), FlightOrder.class);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFlightOrder(String id) {
        try {
           amadeus.delete(String.format("/v1/booking/flight-orders/%s", id));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Location> getAirportCitySearch(String subType, String keyword) {
        try {
            return List.of(amadeus.referenceData.locations.get(Params.with("keyword", keyword).and("subType", subType)));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SeatMapDisplay getSeatMapDisplay(SeatMapDisplayRequest request) {
        try {
            var response = amadeus.post("/v1/shopping/seatmaps", convertObjectToJson(request));
            return convertJsonToObject(response.getBody(), SeatMapDisplay.class);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

}
