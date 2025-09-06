package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FormOfIdentification {
    private IdentificationType identificationType;

    @Size(min = 1, max = 2)
    private String carrierCode;
    private String number;
    private List<String> travelerIds;

    @Size(min = 1, max = 6)
    private List<String> flightOfferIds;

    @Getter
    private enum IdentificationType {
        DRIVERS_LICENSE, PASSPORT, NATIONAL_IDENTITY_CARD, BOOKING_CONFIRMATION, TICKET, OTHER_ID
    }
}
