package com.liberty.demo.presentation.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "RedisResponse", description = "RedisResponse object")
public class RedisResponse {

    @JsonProperty("httpStatus")
    private int httpStatus;

    @JsonProperty("message")
    private String message;
}
