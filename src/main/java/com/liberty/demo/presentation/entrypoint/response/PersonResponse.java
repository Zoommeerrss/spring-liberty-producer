package com.liberty.demo.presentation.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "PersonResponse", description = "PersonResponse object")
public class PersonResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("year")
    private int year;

    @JsonProperty("message")
    private String message;
}
