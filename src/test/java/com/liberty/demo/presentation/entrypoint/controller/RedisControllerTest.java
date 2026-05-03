package com.liberty.demo.presentation.entrypoint.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.RedisStrategyService;
import com.liberty.demo.service.impl.RedisSendServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RedisControllerTest {

    // for Spring integration test use MockMvc with @Autowired annotation
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RedisController controller;

    @Mock
    private RedisStrategyService redisService;

    @Mock
    private RedisTemplate<String, String> redisTemplate;


    private final String endpoint = "/api/v1/redis";
    private final String restService = "/add";

    private final String key = "1";
    private final String value = "Teste";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        redisService = mock(RedisSendServiceImpl.class);
        redisTemplate = mock(RedisTemplate.class);
    }

    @Test
    public void testAddRedisKeyValueMock() throws JsonProcessingException {

//        RedisRequest request = RedisRequest.builder()
//                .key(key)
//                .value(value)
//                .build();
//
//        ResponseEntity<Boolean> responseCreated = new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
//        Mockito.doNothing().when(redisService).add(request);
//
//        ResponseEntity<Boolean> response = controller.addRedisKeyValue(request);
//        redisService.add(request);
//
//        Mockito.verify(redisService, Mockito.times(1)).add(request);
//
//        assertEquals(response.getStatusCode(), response.getStatusCode());
//        assertEquals(response.getBody(), response.getBody());

    }

    @Test
    public void testGetFromCacheMock() throws JsonProcessingException {

//        ResponseEntity<String> responseOk = new ResponseEntity<>(value, HttpStatus.OK);
//        String responseValueOk = "Teste";
//        Mockito.doReturn(responseValueOk).when(redisService).getValue(key);
//
//        ResponseEntity<String> response = controller.getFromCache(key);
//        String responseValue = redisService.getValue(key);
//
//        Mockito.verify(redisService, Mockito.times(1)).getValue(key);
//
//        assertEquals(response.getStatusCode(), response.getStatusCode());
//        assertEquals(response.getBody(), response.getBody());
//        assertEquals(responseValueOk, responseValue);
    }

    @AfterEach
    public void tearDown() {
    }
}