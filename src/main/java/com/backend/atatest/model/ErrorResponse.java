package com.backend.atatest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class ErrorResponse {
    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("description")
    private String description;
}
