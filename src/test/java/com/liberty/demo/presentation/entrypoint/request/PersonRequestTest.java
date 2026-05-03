package com.liberty.demo.presentation.entrypoint.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonRequestTest {

    private final String name = "Billy";
    private final int year = 1999;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetObjectType() {

        PersonRequest data = new PersonRequest();
        data.setName(name);
        data.setYear(year);

        assertEquals(name, data.getName());
        assertEquals(year, data.getYear());
    }

    @Test
    public void testGetGenericObjectType() {

        PersonRequest data = PersonRequest.builder()
                .name(name)
                .year(year)
                .build();

        assertEquals(name, data.getName());
        assertEquals(year, data.getYear());
    }

    @Test
    public void testBuilderType() {

        PersonRequest.PersonRequestBuilder data = PersonRequest.builder();

        assertTrue(data.toString().contains("name"));
        assertTrue(data.toString().contains("year"));
    }

    @AfterEach
    public void tearDown() {
    }
}