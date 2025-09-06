package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Remarks {
    @Max(200)
    private List<General> general;

    @Max(200)
    private List<Airline> airline;

    @Data
    @JsonInclude(NON_NULL)
    private static class General {
        @JsonProperty(required = true)
        private GeneralRemarkType subType;

        @Pattern(regexp = "[A-Z]")
        private String category;

        @JsonProperty(required = true)
        private String text;
        private List<String> travelerIds;

        @Size(min = 1, max = 6)
        private List<String> flightOfferIds;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Airline {
        @JsonProperty(required = true)
        private AirlineRemarkType subType;
        private String keyword;

        @JsonProperty(required = true)
        private String airlineCode;

        @JsonProperty(required = true)
        private String text;
        private List<String> travelerIds;

        @Size(min = 1, max = 6)
        private List<String> flightOfferIds;
    }

    @Getter
    private enum GeneralRemarkType {
        GENERAL_MISCELLANEOUS, CONFIDENTIAL, INVOICE, QUALITY_CONTROL, BACKOFFICE, FULFILLMENT, ITINERARY, TICKETING_MISCELLANEOUS, TOUR_CODE
    }

    @Getter
    private enum AirlineRemarkType {
        OTHER_SERVICE_INFORMATION, KEYWORD, OTHER_SERVICE, CLIENT_ID, ADVANCED_TICKET_TIME_LIMIT
    }
}