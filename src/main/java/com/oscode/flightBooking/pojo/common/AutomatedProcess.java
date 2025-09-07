package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class AutomatedProcess {
    private AutomatedProcessCode code;
    private Queue queue;
    private String text;
    private String delay;
    private String officeId;
    private String dateTime;

    @Data
    @JsonInclude(NON_NULL)
    private static class Queue {
        private String number;
        private String category;
    }

    @Getter
    private enum AutomatedProcessCode {
        IMMEDIATE, DELAYED, ERROR
    }
}