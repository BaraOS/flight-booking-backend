package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Contact {

    private Name addresseeName;
    private Address address;
    private String language;
    private Purpose purpose;

    @Size(max = 3)
    private List<Phone> phones;
    private String companyName;

    @Email
    private String emailAddress;

    @Data
    @JsonInclude(NON_NULL)
    private static class Address {
        private List<String> lines;
        private String postalCode;

        @Pattern(regexp = "[a-zA-Z]{2}")
        private String countryCode;

        @Pattern(regexp = "[a-zA-Z -]{1,35}")
        private String cityName;
        private String stateName;
        private String postalBox;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Phone {
        private PhoneDeviceType deviceType;

        @Pattern(regexp = "[0-9+]{2,5}")
        private String countryCallingCode;

        @Pattern(regexp = "[0-9]{1,15}")
        private String number;
    }

    @Getter
    private enum Purpose {
        STANDARD, INVOICE, STANDARD_WITHOUT_TRANSMISSION
    }

    @Getter
    private enum PhoneDeviceType {
        MOBILE, LANDLINE, FAX
    }
}
