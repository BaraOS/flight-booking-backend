package com.oscode.flightBooking.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.oscode.flightBooking.pojo.common.FlightOffer;
import com.oscode.flightBooking.pojo.common.FlightOfferDictionaries;
import com.oscode.flightBooking.pojo.common.Issue;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightOfferPrice {
    private List<Issue> warnings;
    private FlightOfferPricingOutput data;
    private IncludedResourceMap included;
    private FlightOfferDictionaries dictionaries;

    @Data
    @JsonInclude(NON_NULL)
    private static class FlightOfferPricingOutput {
        private String type;
        private List<FlightOffer> flightOffers;
        private BookingRequirements bookingRequirements;

        @Data
        @JsonInclude(NON_NULL)
        private static class BookingRequirements {
            private Boolean invoiceAddressRequired;
            private Boolean mailingAddressRequired;
            private Boolean emailAddressRequired;
            private Boolean phoneCountryCodeRequired;
            private Boolean mobilePhoneNumberRequired;
            private Boolean phoneNumberRequired;
            private Boolean postalCodeRequired;
            private List<PassengerConditions> travelerRequirements;

            @Data
            @JsonInclude(NON_NULL)
            private static class PassengerConditions {
                private String travelerId;
                private Boolean genderRequired;
                private Boolean documentRequired;
                private Boolean documentIssuanceCityRequired;
                private Boolean dateOfBirthRequired;
                private Boolean redressRequiredIfAny;
                private Boolean airFranceDiscountRequired;
                private Boolean spanishResidentDiscountRequired;
                private Boolean residenceRequired;
            }
        }
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class IncludedResourceMap {

        @JsonProperty("credit-card-fees")
        @SerializedName("credit-card-fees")
        private Map<String, CreditCardFee> creditCardFees;

        private Map<String, Bags> bags;

        @JsonProperty("other-services")
        @SerializedName("other-services")
        private Map<String, Service> otherServices;

        @JsonProperty("detailed-fare-rules")
        @SerializedName("detailed-fare-rules")
        private Map<String, DetailedFareRules> detailedFareRules;

        @Data
        @JsonInclude(NON_NULL)
        private static class CreditCardFee {
            private PaymentBrand brand;
            private String amount;
            private String currency;
            private String flightOfferId;

            private enum PaymentBrand {
                VISA, AMERICAN_EXPRESS, MASTERCARD, VISA_ELECTRON,
                VISA_DEBIT, MASTERCARD_DEBIT, MAESTRO, DINERS, MASTERCARD_IXARIS,
                VISA_IXARIS, MASTERCARD_AIRPLUS, UATP_AIRPLUS}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Bags {
            private Integer quantity;
            private Integer weight;
            private String weightUnit;
            private String name;
            private ElementaryPrice price;
            private Boolean bookableByItinerary;
            private List<String> segmentIds;
            private List<String> travelerIds;

        }

        @Data
        @JsonInclude(NON_NULL)
        private static class Service {
            private ServiceName name;
            private ElementaryPrice price;
            private Boolean bookableByTraveler;
            private Boolean bookableByItinerary;
            private List<String> segmentIds;
            private List<String> travelerIds;

            private enum ServiceName {PRIORITY_BOARDING, AIRPORT_CHECKIN}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class DetailedFareRules {
            private String fareBasis;
            private String name;
            private TermAndCondition fareNotes;
            private String segmentId;

            @Data
            @JsonInclude(NON_NULL)
            private static class TermAndCondition {
                private Category category;
                private String circumstances;
                private Boolean notApplicable;
                private String maxPenaltyAmount;
                private List<Description> descriptions;

                @Data
                @JsonInclude(NON_NULL)
                private static class Description {
                    private String descriptionType;
                    private String text;
                }

                private enum Category {REFUND, EXCHANGE, REVALIDATION, REISSUE, REBOOK, CANCELLATION}
            }
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class ElementaryPrice {
            private String amount;
            private String currencyCode;
        }
    }
}


