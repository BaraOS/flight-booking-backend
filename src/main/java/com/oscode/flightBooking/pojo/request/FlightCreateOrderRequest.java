package com.oscode.flightBooking.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oscode.flightBooking.pojo.common.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightCreateOrderRequest {

    private FlightCreateOrderData data;

    @Data
    @JsonInclude(NON_NULL)
    private static class FlightCreateOrderData {

        @JsonProperty(required = true)
        private String type;
        private String queuingOfficeId;
        private String ownerOfficeId;

        @Size(min = 1, max = 6)
        private List<FlightOffer> flightOffers;

        @Size(min = 1, max = 18)
        private List<Traveler> travelers;
        private Remarks remarks;
        private TicketingAgreement ticketingAgreement;

        @Max(31)
        private List<AutomatedProcess> automatedProcess;
        private List<Contact> contacts;
        private List<FormOfIdentification> formOfIdentifications;

        @Data
        @JsonInclude(NON_NULL)
        private static class TicketingAgreement {
            private TicketingAgreementOption option;
            private String delay;

            @Getter
            private enum TicketingAgreementOption {
                CONFIRM, DELAY_TO_QUEUE, DELAY_TO_CANCEL
            }
        }
    }
}
