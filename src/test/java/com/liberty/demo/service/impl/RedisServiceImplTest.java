package com.liberty.demo.service.impl;

import com.liberty.demo.config.utils.RedisUtils;
import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RedisServiceImplTest {

    @InjectMocks
    private RedisSendServiceImpl redisService;

    @Mock
    private RedisUtils<String> redisUtils;

    private final String key = "1";
    private final String value = "Teste";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        redisService = new RedisSendServiceImpl();
    }

    @Test
    public void testAddRedis() {

        RedisRequest request = RedisRequest.builder()
                .key(key)
                .value(value)
                .build();

//        Mockito.doNothing().when(redisUtils).set(key, value);
//        redisService.add(request);
//        verify(redisUtils, times(1)).set(key, value);
    }

    @Test
    public void testGetValue() {

//        Mockito.doReturn(value).when(redisUtils).get(key);
//        String serviceValue = redisService.getValue(key);
//        verify(redisUtils, times(1)).get(key);
//        assertEquals(value, serviceValue);
    }

    @AfterEach
    public void tearDown() {
    }

}