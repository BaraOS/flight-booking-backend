package com.oscode.flightBooking.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Meta {
    private Integer count;
    private CollectionLinks links;

    @Data
    @JsonInclude(NON_NULL)
    private static class CollectionLinks {
        private String self;
        private String next;
        private String previous;
        private String last;
        private String first;
        private String up;
    }
}