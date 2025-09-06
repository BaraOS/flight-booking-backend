package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightOffer {

    @JsonProperty(required = true)
    private String type;

    @JsonProperty(required = true)
    private String id;
    private FlightOfferSource source;
    private Boolean instantTicketingRequired;
    private Boolean disablePricing;
    private Boolean nonHomogeneous;
    private Boolean oneWay;
    private Boolean isUpsellOffer;
    private Boolean paymentCardRequired;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String lastTicketingDate;

    @DateTimeFormat(pattern = "YYYY-MM-ddThh:mm:ss")
    private String lastTicketingDateTime;

    @Size(min = 1, max = 9)
    private Integer numberOfBookableSeats;

    @Size(min = 1, max = 250)
    private List<Itinerary> itineraries;
    private Price price;
    private PricingOptions pricingOptions;

    @Size(min = 1, max = 9)
    private List<String> validatingAirlineCodes;

    @Size(min = 1, max = 18)
    private List<TravelerPricing> travelerPricings;

    @Data
    @JsonInclude(NON_NULL)
    private static class Itinerary {
        private String duration;

        @JsonProperty(required = true)
        @Size(min = 1, max = 9)
        private List<SearchSegment> segments;

        @Data
        @JsonInclude(NON_NULL)
        public static class SearchSegment {
            private String id;
            private Integer numberOfStops;
            private Boolean blacklistedInEU;
            private List<Co2Emissions> co2Emissions;
            private AirportInfo departure;
            private AirportInfo arrival;

            @Size(min = 1, max = 2)
            private String carrierCode;

            @Size(min = 1, max = 4)
            private String number;
            private Aircraft aircraft;
            private OperatingFlight operating;
            private String duration;
            private List<FlightStop> stops;
            private BookingStatus bookingStatus;
            private SegmentType segmentType;
            private Boolean isFlown;

            @Data
            @JsonInclude(NON_NULL)
            public static class Co2Emissions {
                private Integer weight;
                private String weightUnit;
                private TravelClass cabin;

                private enum TravelClass {ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST}
            }

            @Data
            @JsonInclude(NON_NULL)
            public static class AirportInfo {
                private String iataCode;
                private String terminal;

                @DateTimeFormat(pattern = "YYYY-MM-ddThh:mm:ss")
                private String at;
            }

            @Data
            @JsonInclude(NON_NULL)
            public static class Aircraft {
                @Pattern(regexp = "[a-zA-Z0-9]{3}")
                private String code;
            }

            @Data
            @JsonInclude(NON_NULL)
            public static class OperatingFlight {
                @Size(min = 1, max = 2)
                private String carrierCode;
            }

            @Data
            @JsonInclude(NON_NULL)
            public static class FlightStop {
                private String iataCode;
                private String duration;

                @DateTimeFormat(pattern = "YYYY-MM-ddThh:mm:ss")
                private String arrivalAt;

                @DateTimeFormat(pattern = "YYYY-MM-ddThh:mm:ss")
                private String departureAt;
            }

            @Getter
            private enum BookingStatus {CONFIRMED, WAITLISTED, CANCELLED, PENDING, DENIED}

            @Getter
            private enum SegmentType {ACTIVE, PASSIVE, GHOST, STAFF }
        }
    }

    @Getter
    private enum FlightOfferSource {GDS}

    @Data
    @JsonInclude(NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FareDetailsBySegment {

        @JsonProperty(required = true)
        private String segmentId;
        private String cabin;

        @Pattern(regexp = "[A-Z0-9]{1,18}")
        private String fareBasis;
        private String brandedFare;
        private String brandedFareLabel;


        @JsonProperty("class")
        @SerializedName("class")
        @Pattern(regexp = "[A-Z]")
        private String classTest;
        private Boolean isAllotment;
        private AllotmentDetails allotmentDetails;
        private SliceDiceIndicator sliceDiceIndicator;
        private BaggageAllowance includedCheckedBags;
        private AdditionalServiceRequest additionalServices;
        private List<Amenity> amenities;
        private List<MealService> mealServices;

        @Data
        @JsonInclude(NON_NULL)
        public static class AdditionalServiceRequest {
            private ChargeableCheckedBags chargeableCheckedBags;
            private ChargeableSeat chargeableSeat;

            @Pattern(regexp = "[1-9][0-9]{0,2}[A-Z]?")
            private String chargeableSeatNumber;
            private List<OtherServices> otherServices;

            @Data
            @JsonInclude(NON_NULL)
            private static class ChargeableCheckedBags {
                private String id;
                private Integer quantity;
                private Integer weight;
                private String weightUnit;
            }

            @Data
            @JsonInclude(NON_NULL)
            private static class ChargeableSeat {
                private String id;

                @Pattern(regexp = "[1-9][0-9]{0,2}[A-Z]?")
                private String number;
            }

            private enum OtherServices {PRIORITY_BOARDING, AIRPORT_CHECKIN}
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class BaggageAllowance {
            private Integer quantity;
            private Integer weight;
            private String weightUnit;
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class AllotmentDetails {
            private String tourName;
            private String tourReference;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Amenity{
            private String description;
            private String isChargeable;
            private String amenityType;
            private AmenityProvider amenityProvider;

            @Data
            @JsonInclude(NON_NULL)
            private static class AmenityProvider {
                private String name;
            }
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class MealService {
            private String label;
        }

        private enum SliceDiceIndicator {LOCAL_AVAILABILITY, SUB_OD_AVAILABILITY_1, SUB_OD_AVAILABILITY_2}
    }

    @Data
    @JsonInclude(NON_NULL)
    public static class TravelerPricing {

        @JsonProperty(required = true)
        private String travelerId;

        @JsonProperty(required = true)
        private FareOption fareOption;
        private TravelerType travelerType;
        private String associatedAdultId;
        private Price price;

        @Size(min = 1, max = 18)
        private List<FareDetailsBySegment> fareDetailsBySegment;

        @Getter
        private enum FareOption {
            STANDARD, INCLUSIVE_TOUR, SPANISH_MELILLA_RESIDENT, SPANISH_CEUTA_RESIDENT, SPANISH_CANARY_RESIDENT,
            SPANISH_BALEARIC_RESIDENT, AIR_FRANCE_METROPOLITAN_DISCOUNT_PASS, AIR_FRANCE_DOM_DISCOUNT_PASS,
            AIR_FRANCE_COMBINED_DISCOUNT_PASS, AIR_FRANCE_FAMILY, ADULT_WITH_COMPANION, COMPANION
        }

        @Getter
        private enum TravelerType {ADULT, CHILD, SENIOR, YOUNG, HELD_INFANT, SEATED_INFANT, STUDENT}
    }

    @Data
    @JsonInclude(NON_NULL)
    public static class PricingOptions {
        private List<PricingOptionsFareType> fareType;
        private Boolean includedCheckedBagsOnly;
        private Boolean refundableFare;
        private Boolean noRestrictionFare;
        private Boolean noPenaltyFare;

        @Getter
        private enum PricingOptionsFareType {PUBLISHED, NEGOTIATED, CORPORATE}
    }

    @Data
    @JsonInclude(NON_NULL)
    public static class Price {
        private String margin;
        private String grandTotal;
        private String billingCurrency;
        private List<AdditionalService> additionalServices;
        private String currency;
        private String total;
        private String base;
        private List<Fee> fees;
        private List<Tax> taxes;
        private String refundableTaxes;

        @Data
        @JsonInclude(NON_NULL)
        public static class AdditionalService {
            private String amount;
            private AdditionalServiceType type;

            private enum AdditionalServiceType {CHECKED_BAGS, MEALS, SEATS, OTHER_SERVICES}
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class Fee {
            private String amount;
            private FeeType type;

            private enum FeeType {TICKETING, FORM_OF_PAYMENT, SUPPLIER};
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class Tax {
            private String amount;
            private String code;
        }
    }

}
