package com.liberty.demo.presentation.entrypoint.controller;

import com.liberty.demo.presentation.entrypoint.request.PersonRequest;
import com.liberty.demo.presentation.entrypoint.response.Data;
import com.liberty.demo.presentation.entrypoint.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/people")
public class HelloController {

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
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<>("Hello from Spring Boot in Open Liberty!", HttpStatus.OK);
	}

	@Operation(summary = "Get calculateAge response by path variable", description = "Returns 200 if successful")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Successful Operation"),
					@ApiResponse(responseCode = "400", description = "Bad Request"),
					@ApiResponse(responseCode = "403", description = "Forbidden"),
					@ApiResponse(responseCode = "404", description = "Not Found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			}
	)
	@GetMapping(value = "/calculateAge/{name}/{year}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<PersonResponse> calculateAge(@PathVariable(name = "name") String name, @PathVariable(name = "year") int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		return new ResponseEntity<>(
				PersonResponse.builder()
						.name(name)
						.year(calendar.get(Calendar.YEAR) - year)
						.message("You are good!")
						.build(),
				HttpStatus.OK);
	}

	@Operation(summary = "Get calculateAge response by POST using request params", description = "Returns 201 if successful")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "201", description = "Successful Operation"),
					@ApiResponse(responseCode = "400", description = "Bad Request"),
					@ApiResponse(responseCode = "403", description = "Forbidden"),
					@ApiResponse(responseCode = "404", description = "Not Found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			}
	)
	@PostMapping(value = "/calculateAge", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<PersonResponse> calculateAgeRequest(@RequestParam(name = "name") String name, @RequestParam(name = "year") int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		return new ResponseEntity<>(
				PersonResponse.builder()
						.name(name)
						.year(calendar.get(Calendar.YEAR) - year)
						.message("You are good!")
						.build(),
				HttpStatus.CREATED);
	}

	@Operation(summary = "Get calculateAge response by POST using request body", description = "Returns 201 if successful")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "201", description = "Successful Operation"),
					@ApiResponse(responseCode = "400", description = "Bad Request"),
					@ApiResponse(responseCode = "403", description = "Forbidden"),
					@ApiResponse(responseCode = "404", description = "Not Found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			}
	)
	@PostMapping(value = "/calculateAge",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<Data<PersonResponse>> calculateAgeRequestBody(@RequestBody PersonRequest person) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		return new ResponseEntity<>(
				new Data<>(PersonResponse.builder()
						.name(person.getName())
						.year(calendar.get(Calendar.YEAR) - person.getYear())
						.message("You are good!")
						.build())
                , HttpStatus.CREATED
        );
	}

}

