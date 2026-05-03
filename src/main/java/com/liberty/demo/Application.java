package com.liberty.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.liberty.demo.config",
		"com.liberty.demo.service.impl",
		"com.liberty.demo.service.component",
		"com.liberty.demo.presentation.entrypoint.controller",
		"com.liberty.demo.presentation.entrypoint.filter",
		"com.liberty.demo.adapter.rabbitmq",
		"com.liberty.demo.service.rabbitmq",
})
@OpenAPIDefinition(
		tags = {@Tag(name = "version: openapi_3_0)")},
		info = @Info(
				title = "Spring Liberty Main API",
				description = "App modelo REST para teste de regras de negócio e validações de response JSON",
				termsOfService = "Criado por @emerzoom. Use com moderação!",
				contact = @Contact(
						name = "Emerzoom",
						email = "everybody@cris.com"
				),
				version = "1.0.0b"
		)
)
@EnableRabbit
@EnableCaching
public class Application {

	public static void main(String[] args) {

		String envvar = System.getenv("LOGGER_JSON_FORMAT");
		String argsParams = java.util.Arrays.toString(args);
		System.out.println("Application --> Arguments array: " + argsParams);
		System.out.println("Application --> LOGGER_JSON_FORMAT: " + envvar);

		SpringApplication.run(Application.class, args);
	}

}
