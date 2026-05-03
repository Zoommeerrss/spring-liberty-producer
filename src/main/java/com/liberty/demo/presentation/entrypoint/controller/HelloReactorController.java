package com.liberty.demo.presentation.entrypoint.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reactor-people")
public class HelloReactorController {

	@Operation(summary = "Welcome response", description = "Returns 200 if successful")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Successful Operation"),
					@ApiResponse(responseCode = "400", description = "Bad Request"),
					@ApiResponse(responseCode = "403", description = "Forbidden"),
					@ApiResponse(responseCode = "404", description = "Not Found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			}
	)
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public Mono<ResponseEntity<String>> monoSingleObject() {
		return Mono.just(new ResponseEntity<>("Hello, Reactor!", HttpStatus.OK));
	}

	@Operation(summary = "Get a flux stream list", description = "Returns 200 if successful")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Successful Operation"),
					@ApiResponse(responseCode = "400", description = "Bad Request"),
					@ApiResponse(responseCode = "403", description = "Forbidden"),
					@ApiResponse(responseCode = "404", description = "Not Found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			}
	)
	@GetMapping(value = "/messages", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public Flux<ResponseEntity<List<String>>> fluxStreamList() {
		List<String> response = Arrays.asList(
				"Hello, Reactor 1!\n",
				"Hello, Reactor 2!\n",
				"Hello, Reactor 3!\n",
				"Hello, Reactor 4!\n"
		);
		return Flux.just(new ResponseEntity<>(response, HttpStatus.OK))
				.delayElements(Duration.ofSeconds(1));
	}
}

