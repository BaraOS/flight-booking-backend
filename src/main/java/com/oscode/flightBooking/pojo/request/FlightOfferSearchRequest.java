package com.oscode.flightBooking.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightOfferSearchRequest {

    @Size(min=1, max=3, message = "Currency code invalid")
    private String currencyCode;

    @JsonProperty(required = true)
    @Size(min=1, max=6, message = "Number of destinations must be between 1-6")
    private List<OriginDestination> originDestinations;

    @JsonProperty(required = true)
    private List<Traveler> travelers;

    @JsonProperty(required = true)
    @Size(min=1, message = "minimum of 1 source is required")
    private List<Source> sources;

    private SearchCriteria searchCriteria;

    @Data
    @JsonInclude(NON_NULL)
    private static class OriginDestination {
        private String id;
        private String originLocationCode;
        private String destinationLocationCode;

        @Size(min=1, max=2)
        private List<String> includedConnectionPoints;

        @Size(min=1, max=3)
        private List<String> excludedConnectionPoints;
        private Integer originRadius;

        @Size(min=1, max=2)
        private List<String> alternativeOriginsCodes;
        private Integer destinationRadius;

        @Size(min=1, max=2)
        private List<String> alternativeDestinationsCodes;
        private DateTimeRange departureDateTimeRange;
        private DateTimeRange arrivalDateTimeRange;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class DateTimeRange {
        @JsonProperty(required = true)
        @DateTimeFormat(pattern = "YYYY-MM-DD")
        private String date;
        private String time;

        @Pattern(regexp = "^[MPI][1-3]D")
        private String dateWindow;

        @Pattern(regexp = "^([1-9]|10|11|12)H")
        private String timeWindow;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Traveler {
        @JsonProperty(required = true)
        private String id;

        @JsonProperty(required = true)
        private TravelerType travelerType;

        private String associatedAdultId;

        private enum TravelerType {
            ADULT, CHILD, SENIOR, YOUNG, HELD_INFANT, SEATED_INFANT, STUDENT
        }
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class SearchCriteria {
        private Boolean excludeAllotments;
        private Boolean addOneWayOffers;

        @Size(min = 1, max = 250)
        private Integer maxFlightOffers;
        private Integer maxPrice;
        private Boolean allowAlternativeFareOptions;
        private Boolean oneFlightOfferPerDay;
        private AdditionalInformation additionalInformation;
        private PricingOptions pricingOptions;
        private FlightFilters flightFilters;

    }

    @Data
    @JsonInclude(NON_NULL)
    private static class AdditionalInformation {
        private Boolean chargeableCheckedBags;
        private Boolean brandedFares;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class PricingOptions {
        private Boolean includedCheckedBagsOnly;
        private Boolean refundableFare;
        private Boolean noRestrictionFare;
        private Boolean noPenaltyFare;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class FlightFilters {
        private Boolean crossBorderAllowed;
        private Boolean moreOvernightsAllowed;
        private Boolean returnToDepartureAirport;
        private Boolean railSegmentAllowed;
        private Boolean busSegmentAllowed;
        private Integer maxFlightTime;
        private CarrierRestrictions carrierRestrictions;
        private List<CabinRestrictions> cabinRestrictions;
        private ConnectionRestriction connectionRestriction;

    }

    @Data
    @JsonInclude(NON_NULL)
    private static class CarrierRestrictions {
        private Boolean blacklistedInEUAllowed;

        @Size(min=1, max=99)
        private List<String> excludedCarrierCodes;

        @Size(min=1, max=99)
        private List<String> includedCarrierCodes;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class CabinRestrictions {
        private TravelClass cabin;
        private List<String> originDestinationIds;
        private Coverage coverage;
        private enum TravelClass {ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST}
        private enum Coverage {MOST_SEGMENTS, AT_LEAST_ONE_SEGMENT, ALL_SEGMENTS}
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class ConnectionRestriction {
        @Size(max=2)
        private Integer maxNumberOfConnections;
        private Boolean nonStopPreferred;
        private Boolean airportChangeAllowed;
        private Boolean technicalStopsAllowed;
    }

    private enum Source { GDS }
}


