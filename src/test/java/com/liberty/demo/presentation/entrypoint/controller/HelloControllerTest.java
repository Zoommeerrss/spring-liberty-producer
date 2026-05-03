package com.liberty.demo.presentation.entrypoint.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liberty.demo.presentation.entrypoint.request.PersonRequest;
import com.liberty.demo.presentation.entrypoint.response.Data;
import com.liberty.demo.presentation.entrypoint.response.PersonResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HelloControllerTest {

    // for Spring integration test use MockMvc with @Autowired annotation
    @Autowired
    private MockMvc mockMvc;

    // for mocking a controller without any restService or repository just use @Mock annotation
    @Mock
    private HelloController controller;

    private final String endpoint = "/api/v1/people";
    private final String restService = "/calculateAge";

    private String name = "John";
    private int year = 1999;
    private String message = "You are good!";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWelcome() {

        ResponseEntity<String> responseMock = new ResponseEntity<>("Hello from Spring Boot in Open Liberty!", HttpStatus.OK);

        Mockito.when(controller.welcome()).thenReturn(responseMock);
        ResponseEntity<String> response = controller.welcome();
        Mockito.verify(controller, Mockito.times(1)).welcome();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello from Spring Boot in Open Liberty!", response.getBody());
    }

    @Test
    public void testWelcomeMock() throws Exception {
        this.mockMvc.perform(get(endpoint)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello from Spring Boot in Open Liberty!")));
    }

    @Test
    public void testCalculateAge() {

        ResponseEntity<PersonResponse> responseMock = new ResponseEntity<>(
                PersonResponse.builder()
                .name(name)
                .year(getYearFromCalendar() - year)
                .message(message)
                .build(), HttpStatus.OK);

        Mockito.when(controller.calculateAge(name, year)).thenReturn(responseMock);
        ResponseEntity<PersonResponse> response = controller.calculateAge(name, year);
        Mockito.verify(controller, Mockito.times(1)).calculateAge(name, year);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(name, response.getBody().getName());
        assertEquals(getYearFromCalendar() - year, response.getBody().getYear());
        assertEquals(message, response.getBody().getMessage());
    }

    @Test
    public void testCalculateAgeMock() throws Exception {

        MvcResult response = this.mockMvc.perform(get(endpoint + restService + "/" + name + "/" + year)).andExpect(status().isOk())
                .andReturn();
        String json = response.getResponse().getContentAsString();
        PersonResponse personResponse = new ObjectMapper().readValue(json, new TypeReference<>(){});
        assertEquals(name, personResponse.getName());
        assertEquals(getYearFromCalendar() - year, personResponse.getYear());
        assertEquals(message, personResponse.getMessage());
    }

    @Test
    public void testCalculateAgeRequest() {

        ResponseEntity<PersonResponse> responseMock = new ResponseEntity<>(
                PersonResponse.builder()
                        .name(name)
                        .year(getYearFromCalendar() - year)
                        .message(message)
                        .build(), HttpStatus.OK);

        Mockito.when(controller.calculateAgeRequest(name, year)).thenReturn(responseMock);
        ResponseEntity<PersonResponse> response = controller.calculateAgeRequest(name, year);
        Mockito.verify(controller, Mockito.times(1)).calculateAgeRequest(name, year);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(name, response.getBody().getName());
        assertEquals(getYearFromCalendar() - year, response.getBody().getYear());
        assertEquals(message, response.getBody().getMessage());
    }

    @Test
    public void testCalculateAgeRequestMock() throws Exception {

        // ?name=Emerson&year=1973
        MvcResult response = this.mockMvc.perform(
                    post(endpoint + restService + "?name=" + name + "&year=" + year)
                )
                .andExpect(status().isCreated())
                .andReturn();
        String json = response.getResponse().getContentAsString();
        PersonResponse personResponse = new ObjectMapper().readValue(json, new TypeReference<>(){});
        assertEquals(name, personResponse.getName());
        assertEquals(getYearFromCalendar() - year, personResponse.getYear());
        assertEquals(message, personResponse.getMessage());
    }

    @Test
    public void testCalculateAgeRequestBody() {

        PersonRequest request = PersonRequest.builder()
                .name(name)
                .year(year)
                .build();

        ResponseEntity<Data<PersonResponse>> responseMock = new ResponseEntity<>(
                new Data<>(
                        PersonResponse.builder()
                                .name(name)
                                .year(getYearFromCalendar() - year)
                                .message(message)
                                .build()),
                HttpStatus.OK);

        Mockito.when(controller.calculateAgeRequestBody(request)).thenReturn(responseMock);
        ResponseEntity<Data<PersonResponse>> response = controller.calculateAgeRequestBody(request);
        Mockito.verify(controller, Mockito.times(1)).calculateAgeRequestBody(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(name, response.getBody().getData().getName());
        assertEquals(getYearFromCalendar() - year, response.getBody().getData().getYear());
        assertEquals(message, response.getBody().getData().getMessage());
    }

    @Test
    public void testCalculateAgeRequestBodyMock() throws Exception {

        PersonRequest request = PersonRequest.builder()
                .name(name)
                .year(year)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        MvcResult response = mockMvc.perform(
                    post(endpoint + restService)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated()) // Assert the status code
                .andReturn();

        String json = response.getResponse().getContentAsString();
        Data<PersonResponse> personResponse = new ObjectMapper().readValue(json, new TypeReference<>(){});
        assertEquals(name, personResponse.getData().getName());
        assertEquals(getYearFromCalendar() - year, personResponse.getData().getYear());
        assertEquals(message, personResponse.getData().getMessage());
    }

    @AfterEach
    public void tearDown() {
    }

    private static int getYearFromCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }
}