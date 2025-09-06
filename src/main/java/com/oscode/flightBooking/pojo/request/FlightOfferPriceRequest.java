package com.oscode.flightBooking.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oscode.flightBooking.pojo.common.FlightOffer;
import com.oscode.flightBooking.pojo.common.Traveler;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightOfferPriceRequest {

    private FlightOfferPriceData data;

    @Data
    @JsonInclude(NON_NULL)
    private static class FlightOfferPriceData {

        @JsonProperty(required = true)
        private String type;

        @JsonProperty(required = true)
        @Size(min = 1, max = 6)
        private List<FlightOffer> flightOffers;

        @Max(6)
        private List<Payment> payments;

        @Max(18)
        private List<Traveler> travelers;
    }

    @Data
    @JsonInclude(NON_NULL)
    private static class Payment {
        private PaymentBrand brand;

        @Pattern(regexp = "[0-9]{6}")
        private Integer binNumber;

        @Size(min=1, max=6)
        private List<String> flightOfferIds;
    }

    @Getter
    private enum PaymentBrand {
        VISA, AMERICAN_EXPRESS, MASTERCARD, VISA_ELECTRON,
        VISA_DEBIT, MASTERCARD_DEBIT, MAESTRO, DINERS,
        MASTERCARD_IXARIS, VISA_IXARIS, MASTERCARD_AIRPLUS, UATP_AIRPLUS
    }
}
