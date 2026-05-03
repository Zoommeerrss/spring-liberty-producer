package com.liberty.demo.presentation.entrypoint.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HelloReactorControllerTest {

    // for Spring integration test use MockMvc with @Autowired annotation
    @Autowired
    private MockMvc mockMvc;

    // for mocking a controller without any service or repository just use @Mock annotation
    @Mock
    private RedisController controller;

    private final String endpoint = "/api/v1/reactor-people";
    private final String restService = "/messages";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void monoSingleObject() throws Exception {

        MvcResult response = this.mockMvc.perform(get(endpoint)).andExpect(status().isOk())
                .andReturn();
        int status = response.getResponse().getStatus();

        Assertions.assertEquals(200, status);
    }

    @Test
    public void fluxStreamList() throws Exception {

        MvcResult response = this.mockMvc.perform(get(endpoint + restService)).andExpect(status().isOk())
                .andReturn();
        int status = response.getResponse().getStatus();

        Assertions.assertEquals(200, status);
    }

    @AfterEach
    public void tearDown() {
    }
}