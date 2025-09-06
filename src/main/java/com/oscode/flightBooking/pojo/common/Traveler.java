package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Traveler {
    private String id;
    private String dateOfBirth;
    private Gender gender;
    private Name name;
    private List<Document> documents;
    private EmergencyContact emergencyContact;
    private List<LoyaltyProgram> loyaltyPrograms;
    private List<Discount> discountEligibility;
    private Contact contact;

    @Data
    @JsonInclude(NON_NULL)
    private static class Document {
        private String number;
        private String issuanceDate;
        private String expiryDate;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String issuanceCountry;
        private String issuanceLocation;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String nationality;
        private String birthPlace;
        private DocumentType documentType;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String validityCountry;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String birthCountry;
        private boolean holder;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class EmergencyContact {

        @Pattern(regexp = "[a-zA-Z -]")
        private String addresseeName;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String countryCode;

        @Pattern(regexp = "[0-9]{1,15}")
        private String number;
        private String text;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class LoyaltyProgram {
        private String programOwner;
        private String id;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Discount {
        private String subType;
        private String cityName;
        private DiscountTravelerType travelerType;

        @Pattern(regexp = "[0-9A-Z][0-9]{0,12}[A-Z]")
        private String cardNumber;

        @Pattern(regexp = "[0-9A-Z][0-9]{0,12}[A-Z]")
        private String certificateNumber;
    }

    @Getter
    private enum Gender {
        MALE, FEMALE
    }

    @Getter
    private enum DocumentType {
        VISA, PASSPORT, IDENTITY_CARD, KNOWN_TRAVELER, REDRESS
    }

    @Getter
    private enum DiscountTravelerType {
        SPANISH_CITIZEN, EUROPEAN_CITIZEN, GOVERNMENT_WORKER, MILITARY, MINOR_WITHOUT_ID
    }
}