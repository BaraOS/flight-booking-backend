package com.oscode.flightBooking.controller;

import com.amadeus.resources.Location;
import com.oscode.flightBooking.domain.Response;
import com.oscode.flightBooking.enums.SubType;
import com.oscode.flightBooking.pojo.request.FlightCreateOrderRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferPriceRequest;
import com.oscode.flightBooking.pojo.request.FlightOfferSearchRequest;
import com.oscode.flightBooking.pojo.request.SeatMapDisplayRequest;
import com.oscode.flightBooking.pojo.response.FlightOrder;
import com.oscode.flightBooking.pojo.response.FlightOfferPrice;
import com.oscode.flightBooking.pojo.response.FlightOfferSearch;
import com.oscode.flightBooking.pojo.response.SeatMapDisplay;
import com.oscode.flightBooking.service.FlightBookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.oscode.flightBooking.utils.ResponseUtils.getResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = { "/flight" })
@CrossOrigin(origins = "http://localhost:5173")
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    @PostMapping("/search")
    public ResponseEntity<Response<FlightOfferSearch>> getFlightOffers(HttpServletRequest request, @RequestBody @Valid FlightOfferSearchRequest flightOfferSearchRequest) {
            var flightData = flightBookingService.getFlightOfferSearch(flightOfferSearchRequest);
            return ResponseEntity.ok().body(getResponse(request, flightData, "Data Retrieved", HttpStatus.OK));
    }

    @PostMapping("/price")
    public ResponseEntity<Response<FlightOfferPrice>> getFlightOfferPrice(HttpServletRequest request, @RequestBody @Valid FlightOfferPriceRequest flightOfferPriceRequest) {
        var flightPrice = flightBookingService.getFlightOfferPrice(flightOfferPriceRequest);
        return ResponseEntity.ok().body(getResponse(request, flightPrice, "Data Retrieved", HttpStatus.OK));
    }

    @PostMapping("/order")
    public ResponseEntity<Response<FlightOrder>> createFlightOrder(HttpServletRequest request, @RequestBody @Valid FlightCreateOrderRequest flightCreateOrderRequest) {
        var flightOrder = flightBookingService.createFlightOrder(flightCreateOrderRequest);
        return ResponseEntity.ok().body(getResponse(request, flightOrder, "Data Retrieved", HttpStatus.OK));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Response<FlightOrder>> getFlightOrder(HttpServletRequest request, @PathVariable String id) {
        var flightOrder = flightBookingService.getFlightOrder(id);
        return ResponseEntity.ok().body(getResponse(request, flightOrder, "Data Retrieved", HttpStatus.OK));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Response<String>> deleteFlightOrder(HttpServletRequest request, @PathVariable String id) {
        flightBookingService.deleteFlightOrder(id);
        return ResponseEntity.ok().body(getResponse(request, "successfully canceled", "Data Retrieved", HttpStatus.OK));
    }

    @GetMapping("/airport")
    public ResponseEntity<Response<List<Location>>> getAirportCitySearch(HttpServletRequest request, @RequestParam SubType subType, @RequestParam String keyword) {
        var airportData = flightBookingService.getAirportCitySearch(subType.getValue(), keyword);
        return ResponseEntity.ok().body(getResponse(request, airportData, "Data Retrieved", HttpStatus.OK));
    }

    @PostMapping(value = "/seatMap")
    public ResponseEntity<Response<SeatMapDisplay>> getSeatMapDisplay(HttpServletRequest request, @RequestBody @Valid SeatMapDisplayRequest seatMapDisplayRequest) {
        var seatMapDisplay = flightBookingService.getSeatMapDisplay(seatMapDisplayRequest);
        return ResponseEntity.ok().body(getResponse(request, seatMapDisplay, "Data Retrieved", HttpStatus.OK));
    }

}
