package com.oscode.flightBooking.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscode.flightBooking.pojo.response.FlightOfferSearch;
import com.oscode.flightBooking.pojo.response.SeatMapDisplay;

public class ConvertUtils {

    public static String convertObjectToJson(Object request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertJsonToObject(String request, Class<T> valueType)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(request, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
