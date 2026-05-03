package com.liberty.demo.presentation.entrypoint.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RedisRequestTest {

    private final String key = "1";
    private final String value = "Teste";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetObjectType() {

        RedisRequest data = new RedisRequest();
        data.setKey(key);
        data.setValue(value);

        assertEquals(key, data.getKey());
        assertEquals(value, data.getValue());
    }

    @Test
    public void testGetGenericObjectType() {

        RedisRequest data = RedisRequest.builder()
                .key(key)
                .value(value)
                .build();

        assertEquals(key, data.getKey());
        assertEquals(value, data.getValue());
        assertEquals(value, data.getValue());
    }

    @Test
    public void testBuilderType() {

        RedisRequest.RedisRequestBuilder data = RedisRequest.builder();

        assertTrue(data.toString().contains("key"));
        assertTrue(data.toString().contains("value"));
    }

    @AfterEach
    public void tearDown() {
    }
}