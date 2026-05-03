package com.liberty.demo.presentation.entrypoint.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    private final String value = "Teste";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetObjectType() {


        Data<String> data = new Data<>(value);
        assertEquals(value, data.getData());
    }

    @Test
    public void testGetGenericObjectType() {

        Data<Object> data = Data
                .builder()
                .data(value)
                .build();
        assertEquals(value, data.getData());
    }

    @Test
    public void testGetSet() throws JsonProcessingException {

        Data<Object> data = new Data<>();
        data.setData(value);
        assertEquals(value, data.getData());

    }

    @Test
    public void testBuilderType() {

        Data.DataBuilder<String> obj = Data.builder();
        obj.data(value);

        String response = obj.toString();

        assertNotNull(response);
        assertTrue(obj.toString().contains("data"));
    }

    @AfterEach
    public void tearDown() {
    }
}