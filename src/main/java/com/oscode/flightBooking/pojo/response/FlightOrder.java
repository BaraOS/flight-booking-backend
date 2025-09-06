package com.oscode.flightBooking.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oscode.flightBooking.pojo.common.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class FlightOrder {
    private Meta meta;
    private List<Issue> warnings;
    private FlightOrderCreateQuery data;
    private FlightOfferDictionaries dictionaries;

    @Data
    @JsonInclude(NON_NULL)
    private static class FlightOrderCreateQuery {
        private String type;
        private String id;
        private String queuingOfficeId;
        private String ownerOfficeId;
        private List<AssociatedRecord> associatedRecords;
        private List<FlightOffer> flightOffers;
        private List<Traveler> travelers;
        private Remarks remarks;
        private List<FormOfPayment> formOfPayments;
        private TicketingAgreement ticketingAgreement;
        private List<AutomatedProcess> automatedProcess;
        private List<Contact> contacts;
        private List<AirTravelDocument> tickets;
        private List<FormOfIdentification> formOfIdentifications;

        @Data
        @JsonInclude(NON_NULL)
        private static class AssociatedRecord {
            private String reference;
            private String creationDate;
            private String originSystemCode;
            private String flightOfferId;
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class FormOfPayment {
           private B2bWallet b2bWallet;
           private CreditCard creditCard;
           private AltPayment other;

           @Data
           @JsonInclude(NON_NULL)
           private static class B2bWallet {
               private String cardId;
               private String cardUsageName;
               private String cardFriendlyName;
               private List<ReportingData> reportingData;
               private CreditCardCommon virtualCreditCardDetails;
               private List<String> flightOfferIds;

               @Data
               @JsonInclude(NON_NULL)
               private static class ReportingData {
                   private String name;
                   private String value;
               }

               @Data
               @JsonInclude(NON_NULL)
               private static class CreditCardCommon {
                   private CreditCardBrand brand;
                   private String holder;
                   private String number;
                   private String expiryDate;
                   private String amount;
                   private String currencyCode;
               }
           }

           @Data
           @JsonInclude(NON_NULL)
           private static class CreditCard {
               private CreditCardBrand brand;
               private String holder;
               private String number;
               private String expiryDate;
               private String securityCode;
               private List<String> flightOfferIds;
           }

           @Data
           @JsonInclude(NON_NULL)
           private static class AltPayment {
               private OtherPaymentMethod method;
               private List<String> flightOfferIds;

               private enum OtherPaymentMethod {ACCOUNT, CHECK, CASH, NONREFUNDABLE}
           }

            @Getter
            private enum CreditCardBrand {VISA, AMERICAN_EXPRESS, MASTERCARD, VISA_ELECTRON, VISA_DEBIT, MASTERCARD_DEBIT, MAESTRO, DINERS, EASYPAY}
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class TicketingAgreement {
            private TicketingAgreementOption option;
            private String delay;
            private String dateTime;
            private List<String> segmentIds;

            @Getter
            private enum TicketingAgreementOption {
                CONFIRM, DELAY_TO_QUEUE, DELAY_TO_CANCEL
            }
        }

        @Data
        @JsonInclude(NON_NULL)
        private static class AirTravelDocument {
            private DocumentType documentType;
            private String documentNumber;
            private DocumentStatus documentStatus;
            private String travelerId;
            private List<String> segmentIds;

            private enum DocumentType {ETICKET, PTICKET, EMD, MCO}
            private enum DocumentStatus {ISSUED, REFUNDED, VOID, ORIGINAL, EXCHANGED}
        }
    }
}