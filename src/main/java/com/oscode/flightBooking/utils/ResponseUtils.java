package com.oscode.flightBooking.utils;

import com.oscode.flightBooking.domain.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

import static java.time.LocalTime.now;
import static org.apache.logging.log4j.util.Strings.EMPTY;

public class ResponseUtils {
    public static <T> Response<T> getResponse(HttpServletRequest request, T data, String message, HttpStatus status) {
        return new Response<>(now().toString(), status.value(), request.getRequestURI(), HttpStatus.valueOf(status.value()), message, EMPTY, data);
    }

    public static Response<String> handleErrorResponse(String message, String exception, HttpServletRequest request, HttpStatusCode status) {
        return new Response<>(now().toString(), status.value(), request.getRequestURI(), HttpStatus.valueOf(status.value()), message, exception, "");
    }

    public static Response<Map<String,String>> handleValidationErrorResponse(Map<String,String> errors, HttpServletRequest request, HttpStatus status)
    {
        return new Response<>(now().toString(), status.value(), request.getRequestURI(), HttpStatus.valueOf(status.value()), "", "", errors);
    }
}
