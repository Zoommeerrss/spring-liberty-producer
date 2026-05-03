package com.liberty.demo.config.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RedisUtilsTest {

    @InjectMocks
    private RedisUtils<String> redisUtils;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    private final String key = "1";
    private final String value = "Teste";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSet() {

        doNothing().when(valueOperations).set(key, value);
        redisUtils.set(key, value);
        verify(valueOperations, times(1)).set(key, value);
    }

    @Test
    public void testGet() {

        when(valueOperations.get(key)).thenReturn(value);
        String response = redisUtils.get(key);
        verify(valueOperations, times(1)).get(key);
        assertEquals(value, response);
    }

    @Test
    public void testExpire() {

        redisUtils.setExpire(key, 2000, TimeUnit.MINUTES);
        verify(redisTemplate, times(1)).expire(key, 2000, TimeUnit.MINUTES);
    }

    @AfterEach
    public void tearDown() {
    }

}