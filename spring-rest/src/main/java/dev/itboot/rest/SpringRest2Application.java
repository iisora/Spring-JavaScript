package dev.itboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Task API", version = "1.0.0", description = "Taskアプリケーションに関するAPI"))
@SpringBootApplication
public class SpringRest2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRest2Application.class, args);
	}

}
