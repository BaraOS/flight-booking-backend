package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Issue {
    private Integer status;
    private Integer code;
    private String title;
    private String detail;
    private Issue_Source source;

    @Data
    @JsonInclude(NON_NULL)
    private static class Issue_Source {
        private String pointer;
        private String parameter;
        private String example;
    }
}