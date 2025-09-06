package com.oscode.flightBooking.pojo.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.oscode.flightBooking.pojo.common.Issue;
import com.oscode.flightBooking.pojo.common.LocationValue;
import com.oscode.flightBooking.pojo.common.Meta;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class SeatMapDisplay {

    private Meta meta;
    private List<SeatMap> data;
    private List<Issue> warnings;
    //@JsonIgnore()
    private Dictionaries dictionaries;

    @Data
    @JsonInclude(NON_NULL)
    private static class SeatMap {
        private String type;
        private String id;
        private Link self;
        private FlightEndPoint departure;
        private FlightEndPoint arrival;
        private String carrierCode;
        private String number;
        private OperatingFlight operating;
        private AircraftEquipment aircraft;
        @JsonProperty("class")
        @SerializedName("class")
        private String classTest;
        private String flightOfferId;
        private String segmentId;
        private List<Deck> decks;
        private AircraftCabinAmenities aircraftCabinAmenities;
        private List<AvailableSeatsCounter> availableSeatsCounters;

        @Data
        @JsonInclude(NON_NULL)
        private static class OperatingFlight {
            private String carrierCode;
            private String number;
            private String suffix;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Link {
            private String href;
            private Methods methods;
            private Integer count;

            private enum Methods {GET, PUT, DELETE, POST, PATCH}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class QualifiedFreeText {
            private String text;
            private String lang;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Media {
            private String title;
            private String href;
            private QualifiedFreeText description;
            private MediaType mediaType;

            private enum MediaType {application, audio, font, example, image, message, model, multipart, text, video}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AvailableSeatsCounter {
            private String travelerId;
            private Integer value;
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class DeckConfiguration {
            private Integer width;
            private Integer length;
            private Integer startSeatRow;
            private Integer endSeatRow;
            private Integer startWingsX;
            private Integer endWingsX;
            private Integer startWingsRow;
            private Integer endWingsRow;
            private List<Integer> exitRowsX;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AmenityEntertainment {
            private Boolean isChargeable;
            private EntertainmentType entertainmentType;

            private enum EntertainmentType { LIVE_TV, MOVIES, AUDIO_VIDEO_ON_DEMAND, TV_SHOWS, IP_TV }
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AmenityFood {
            private Boolean isChargeable;
            private FoodType foodType;

            private enum FoodType {MEAL, FRESH_MEAL, SNACK, FRESH_SNACK}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AmenityBeverage {
            private Boolean isChargeable;
            private BeverageType beverageType;

            private enum BeverageType {ALCOHOLIC, NON_ALCOHOLIC, ALCOHOLIC_AND_NON_ALCOHOLIC}
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class AmenityWifi {
            private Boolean isChargeable;
            private WifiCoverage wifiCoverage;

            private enum WifiCoverage {FULL, PARTIAL}
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class AmenityPower {
            private Boolean isChargeable;
            private PowerType powerType;
            private USBType usbType;

            private enum PowerType {PLUG, USB_PORT, ADAPTOR, PLUG_OR_USB_PORT}
            private enum USBType {USB_A, USB_C, USB_A_AND_USB_C}
        }

        @Data
        @JsonInclude(NON_NULL)
        public static class AmenitySeat {
            private Integer legSpace;
            private SpaceUnit spaceUnit;
            private Tilt tilt;
            private AmenityType amenityType;
            private List<Media> medias;

            private enum SpaceUnit {INCHES, CENTIMENTERS}
            private enum Tilt {FULL_FLAT, ANGLE_FLAT, NORMAL}
            private enum AmenityType {SEAT}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AircraftCabinAmenities {
            private AmenityPower power;
            private AmenitySeat seat;
            private AmenityWifi wifi;
            private List<AmenityEntertainment> entertainment;
            private AmenityFood food;
            private AmenityBeverage beverage;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Deck {
            private DeckType deckType;
            private DeckConfiguration deckConfiguration;
            private List<Facility> facilities;
            private List<Seat> seats;

            @Data
            @JsonInclude(NON_NULL)
            private static class Facility {
                private String code;
                private String column;
                private String row;
                private Position position;
                private Coordinates coordinates;

                private enum Position {FRONT, REAR, SEAT}

            }

            @Data
            @JsonInclude(NON_NULL)
            private static class Seat {
                private String cabin;
                private String number;
                private List<String> characteristicsCodes;
                private List<SeatMapTravelerPricing> travelerPricing;
                private Coordinates coordinates;

                @Data
                @JsonInclude(NON_NULL)
                private static class SeatMapTravelerPricing {
                    private String travelerId;
                    private SeatAvailabilityStatus seatAvailabilityStatus;
                    private Price price;

                    @Data
                    @JsonInclude(NON_NULL)
                    private static class Price {
                        private String currency;
                        private String total;
                        private String base;
                        private List<Fee> fees;
                        private List<Tax> taxes;

                        @Data
                        @JsonInclude(NON_NULL)
                        private static class Fee {
                            private String amount;
                            private FeeType type;

                            private enum FeeType {TICKETING, FORM_OF_PAYMENT, SUPPLIER }
                        }

                        @Data
                        @JsonInclude(NON_NULL)
                        private static class Tax {
                            private String amount;
                            private String code;
                        }
                    }

                    private enum SeatAvailabilityStatus {AVAILABLE, BLOCKED, OCCUPIED}
                }
            }

           private enum DeckType {UPPER, MAIN, LOWER}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class FlightEndPoint {
            private String iataCode;
            private String terminal;
            private String at;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AircraftEquipment {
            private String code;
        }
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Dictionaries {
        private Map<String, LocationValue> locations;
        private Map<String, String> facilities;
        private Map<String, String> seatCharacteristics;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Coordinates {
        private Integer x;
        private Integer y;
    }
}