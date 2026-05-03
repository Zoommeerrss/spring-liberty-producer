package com.liberty.demo.presentation.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "PersonRequest", description = "PersonRequest object")
public class PersonRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("year")
    private int year;

}
