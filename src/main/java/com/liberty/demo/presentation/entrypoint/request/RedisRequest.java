package com.liberty.demo.presentation.entrypoint.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedisRequest {

    private String key;
    private String value;

}