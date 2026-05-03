package com.liberty.demo.presentation.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "Data", description = "Data generic object")
public class Data<T> {

    @JsonProperty("data")
    private T data;

}
