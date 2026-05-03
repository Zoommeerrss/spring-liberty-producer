package com.liberty.demo.presentation.entrypoint.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonResponseTest {

    private final String name = "Joao";
    private final int year = 1999;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetResponse() {

        PersonResponse response = PersonResponse
                .builder()
                .name(name)
                .year(year)
                .build();

        assertEquals(name, response.getName());
        assertEquals(year, response.getYear());
    }

    @Test
    public void testGetSet() throws JsonProcessingException {

        PersonResponse response = new PersonResponse();
        
        response.setName(name);
        response.setYear(year);

        assertEquals(name, response.getName());
        assertEquals(year, response.getYear());

    }

    @Test
    public void testBuilderType() {

        PersonResponse.PersonResponseBuilder data = PersonResponse.builder();

        assertTrue(data.toString().contains("name"));
        assertTrue(data.toString().contains("year"));
    }

    @AfterEach
    public void tearDown() {
    }
}